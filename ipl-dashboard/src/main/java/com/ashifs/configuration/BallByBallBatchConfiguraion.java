package com.ashifs.configuration;

import javax.sql.DataSource;

import com.ashifs.batchitemprocessor.BallByBallItemProcessor;
import com.ashifs.data.BallByBallInput;
import com.ashifs.model.BallByBall;

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

@Configuration
@EnableBatchProcessing
public class BallByBallBatchConfiguraion {

    private final String[] FIELD_NAME = new String[] { "id", "inning", "over", "ball", "batsman", "non_striker",
            "bowler", "batsman_runs", "extra_runs", "total_runs", "non_boundary", "is_wicket", "dismissal_kind",
            "player_dismissed", "fielder", "extras_type", "batting_team", "bowling_team" };

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    @Bean
    public FlatFileItemReader<BallByBallInput> ballByBallReader() {
        System.out.println("FlatFileItemReader===>");
        return new FlatFileItemReaderBuilder<BallByBallInput>().name("ballByBallItemReader")
                .resource(new ClassPathResource("IPL Ball-by-Ball 2008-2020.csv")).delimited().names(FIELD_NAME)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<BallByBallInput>() {
                    {
                        setTargetType(BallByBallInput.class);
                    }
                }).build();
    }

    @Bean
    public BallByBallItemProcessor ballByBallProcessor() {
        return new BallByBallItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<BallByBall> ballByBallWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<BallByBall>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO BALL_BY_BALL (ID,MATCH_ID,INNING,OVER,BALL,BATSMAN,NON_STRIKER,BOWLER,BATSMAN_RUNS,EXTRA_RUNS,TOTAL_RUNS,NON_BOUNDARY,IS_WICKET,DISMISSAL_KIND,PLAYER_DISMISSED, FIELDER,EXTRAS_TYPE,BATTING_TEAM,BOWLING_TEAM )"
                        + "VALUES (B_BY_B_SEQ.NEXTVAL,:matchId,:inning,:over,:ball,:batsman,:nonStriker,:bowler,:batsmanRuns,:extraRuns,:totalRuns,:nonBoundary,:isWicket,:dismissalKind,:playerDismissed,:fielder,:extrasType,:battingTeam,:bowlingTeam)")
                .dataSource(this.dataSource).build();
    }

    @Bean
    public Step ballByBallLoadDataStep(
            org.springframework.batch.item.database.JdbcBatchItemWriter<BallByBall> ballByBallWriter) {
        return stepBuilderFactory.get("BallByBallStep").<BallByBallInput, BallByBall>chunk(10)
                .reader(ballByBallReader()).processor(ballByBallProcessor()).writer(ballByBallWriter).build();
    }

    @Bean
    public Job importBallByBallataJob(Step ballByBallLoadDataStep) {
        return jobBuilderFactory.get("importBallByBallDetails")// .incrementer(new
                                                               // RunIdIncrementer()).listener(listener)
                .flow(ballByBallLoadDataStep).end().build();
    }

}
