package com.ashifs.configuration;

import javax.sql.DataSource;

import com.ashifs.batchitemprocessor.MatchInputItemProcessor;
import com.ashifs.data.MatchInput;
import com.ashifs.listeners.JobCompletionNotificationListener;
import com.ashifs.model.Match;

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
public class MatchBatchConfiguration {

  private final String[] FIELD_NAME = new String[] { "id", "city", "date", "player_of_match", "venue", "neutral_venue",
      "team1", "team2", "toss_winner", "toss_decision", "winner", "result", "result_margin", "eliminator", "method",
      "umpire1", "umpire2" };

  @Autowired
  public JobBuilderFactory jobBuilderFactory;

  @Autowired
  public StepBuilderFactory stepBuilderFactory;

  @Autowired
  private DataSource dataSource;

  MatchBatchConfiguration() {
    System.out.println("<============== MatchBatchConfiguration ==================>");
  }

  @Bean
  public FlatFileItemReader<MatchInput> reader() {
    System.out.println("FlatFileItemReader===>");
    return new FlatFileItemReaderBuilder<MatchInput>().name("personItemReader")
        .resource(new ClassPathResource("IPL Matches 2008-2020.csv")).delimited().names(FIELD_NAME)
        .fieldSetMapper(new BeanWrapperFieldSetMapper<MatchInput>() {
          {
            setTargetType(MatchInput.class);
          }
        }).build();
  }

  @Bean
  public MatchInputItemProcessor processor() {
    return new MatchInputItemProcessor();
  }

  @Bean
  public JdbcBatchItemWriter<Match> writer(DataSource dataSource) {
    return new JdbcBatchItemWriterBuilder<Match>()
        .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
        .sql(
            "INSERT INTO MATCH (id,city,match_date,player_of_match,venue,team1,team2,toss_winner,toss_decision,winner,opponent,result,result_margin,eliminator,umpire2,umpire1)"
                + "VALUES (:id,:city,:matchDate,:playerOfMatch,:venue,:team1,:team2,:tossWinner,:tossDecision,:winner,:opponent,:result,:resultMargin,:eliminator,:umpire2,:umpire1)")
        .dataSource(this.dataSource).build();
  }

  @Bean
  public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
    return jobBuilderFactory.get("importMatchDetails").incrementer(new RunIdIncrementer()).listener(listener)
        .flow(step1).end().build();
  }

  @Bean
  public Step step1(JdbcBatchItemWriter<Match> writer) {
    return stepBuilderFactory.get("step1").<MatchInput, Match>chunk(10).reader(reader()).processor(processor())
        .writer(writer).build();
  }

}
