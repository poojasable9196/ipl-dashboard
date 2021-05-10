package io.javabrains.ipldashboard.data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.javabrains.ipldashboard.modal.Team;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
    
    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    //private final JdbcTemplate jdbcTemplate;

    /*@Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }*/

   /*@Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! MATCH DATA IMPORT JOB FINISHED! Time to verify the results");
            jdbcTemplate.query("SELECT team1, team2, date FROM match", (rs, row) -> "Team1: " + rs.getString(1) + " Team2:" + rs.getString(2) + " Date:" + rs.getString(3))
            .forEach(match -> System.out.println(match));
        }
    }*/

    private final EntityManager entityManager;

    @Autowired
    public JobCompletionNotificationListener(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! MATCH DATA IMPORT JOB FINISHED! Time to verify the results");
            Map<String, Team> map = new HashMap<>();
            
            entityManager.createQuery("select m.team1, count(*) from Match m group by m.team1", Object[].class)
                .getResultList()
                .stream()
                .map(e -> new Team((String)e[0], (Long)e[1]))
                .forEach(e -> map.put(e.getName(), e));
            
            entityManager.createQuery("select m.team2, count(*) from Match m group by m.team2", Object[].class)
                .getResultList()
                .stream()
                .forEach(e -> {
                    Team team = map.get((String)e[0]);
                    team.setTotalMatches(team.getTotalMatches() + (Long)e[1]);
                });

            entityManager.createQuery("select m.matchWinner, count(*) from Match m group by m.matchWinner", Object[].class)
                .getResultList()
                .stream()
                .forEach(e -> {
                        Team team = map.get((String)e[0]);
                        if(team != null) {
                            team.setTotalWins((Long)e[1]);
                        }
                });

            map.values().forEach(team -> entityManager.persist(team));

            map.values().forEach(team -> System.out.println(team));
        }
    }

}
