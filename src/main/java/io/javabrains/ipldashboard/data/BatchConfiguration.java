package io.javabrains.ipldashboard.data;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import io.javabrains.ipldashboard.modal.Match;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    public static final Logger log = LoggerFactory.getLogger(BatchConfiguration.class);

    public static final String[] FIELD_NAMES = new String[] { "id", "city", "date", "player_of_match", "venue",
            "neutral_venue", "team1", "team2", "toss_winner", "toss_decision", "winner", "result", "result_margin",
            "eliminator", "method", "umpire1", "umpire2" };

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    // Reader - Read data from csv file and convert into MatchInput object
    @Bean
    public FlatFileItemReader<MatchInput> reader() {
        log.info("Initializing Match Item Reader...");
        return new FlatFileItemReaderBuilder<MatchInput>().name("matchItemReader")
                .resource(new ClassPathResource("match-data.csv")).delimited().names(FIELD_NAMES)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<MatchInput>() {
                    {
                        setTargetType(MatchInput.class);
                    }
                }).build();
    }

    // Processor
    @Bean
    public MatchDataProcessor processor() {
        log.info("Initializing Match Date Processor...");
        return new MatchDataProcessor();
    }

    // Writer
    @Bean
    public JdbcBatchItemWriter<Match> writer(DataSource dataSource) {
        log.info("Initializing Match Item Writer...");
        return new JdbcBatchItemWriterBuilder<Match>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Match>())
                .sql("INSERT INTO match (id,city,date,player_of_match,venue,team1,team2,toss_winner,toss_decision,match_winner,result,result_margin,umpire1,umpire2) "
                        + "VALUES (:id,:city,:date,:playerOfMatch,:venue,:team1,:team2,:tossWinner,:tossDecision,:matchWinner,:result,:resultMargin,:umpire1,:umpire2)")
                .dataSource(dataSource).build();
    }

    // Job - Job is combination of steps & step is combination of reader + processor
    // + writer
    @Bean
    public Job importMatchJob(JobCompletionNotificationListener listener, Step step1) {
        log.info("Initializing Match Data Importer Job...");
        return jobBuilderFactory.get("importMatchJob").incrementer(new RunIdIncrementer()).listener(listener)
                .flow(step1).end().build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Match> writer) {
        log.info("Initializing Match Data Importer Job Step1...");
        return stepBuilderFactory.get("step1").<MatchInput, Match>chunk(10).reader(reader()).processor(processor())
                .writer(writer).build();
    }

}
