package io.javabrains.ipldashboard.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import io.javabrains.ipldashboard.modal.Match;

public interface MatchRepository extends CrudRepository<Match, Long> {
    
    List<Match> findByTeam1OrTeam2OrderByDateDesc(String team1, String team2, Pageable pageable);    
    
    List<Match> findByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(
        String team1, LocalDate startDate1, LocalDate endDate1,
        String team2, LocalDate startDate2, LocalDate endDate2);

    @Query("select m from Match m where (m.team1=:teamName or m.team2=:teamName) and (m.date between :startDate and :endDate) order by m.date desc")
    List<Match> findByTeamAndDateBetween(@Param("teamName") String teamName, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    
    default List<Match> findLatestMatchesByTeam(String teamName, int count) {
        Pageable pageable = PageRequest.of(0, count);
        return findByTeam1OrTeam2OrderByDateDesc(teamName, teamName, pageable);
    }
}
