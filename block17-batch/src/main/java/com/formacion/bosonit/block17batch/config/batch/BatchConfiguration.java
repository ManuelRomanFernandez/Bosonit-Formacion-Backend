package com.formacion.bosonit.block17batch.config.batch;

import com.formacion.bosonit.block17batch.config.batch.listener.JobListener;
import com.formacion.bosonit.block17batch.config.batch.listener.TiempoJobDecider;
import com.formacion.bosonit.block17batch.config.batch.mapper.MedicionRowMapper;
import com.formacion.bosonit.block17batch.config.batch.mapper.TiempoRowMapper;
import com.formacion.bosonit.block17batch.config.batch.step.processor.MedicionItemProcessor;
import com.formacion.bosonit.block17batch.config.batch.step.processor.TiempoItemProcessor;
import com.formacion.bosonit.block17batch.config.batch.step.processor.TiempoRiesgoItemProcessor;
import com.formacion.bosonit.block17batch.config.batch.step.writer.MedicionItemWriter;
import com.formacion.bosonit.block17batch.config.batch.step.writer.TiempoItemWriter;
import com.formacion.bosonit.block17batch.config.batch.step.writer.TiempoRiesgoItemWriter;
import com.formacion.bosonit.block17batch.config.batch.util.BlankLineRecordSeparatorPolicy;
import com.formacion.bosonit.block17batch.config.batch.util.ErrorCounterComponent;
import com.formacion.bosonit.block17batch.entity.Medicion;
import com.formacion.bosonit.block17batch.entity.Tiempo;
import com.formacion.bosonit.block17batch.entity.TiempoRiesgo;
import com.formacion.bosonit.block17batch.infrastucture.dto.TiempoDto;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@EnableBatchProcessing
@Configuration
public class BatchConfiguration {
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private DataSource dataSource;

    //Readers

    @Bean
    public FlatFileItemReader<TiempoDto> tiempoReader() {

        FlatFileItemReader<TiempoDto> reader= new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("sample-data-file2.csv"));
        reader.setLinesToSkip(1);

        reader.setLineMapper(new DefaultLineMapper<>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setDelimiter(";");
                setNames("provincia","temperatura","fecha");
            }});

            setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                setTargetType(TiempoDto.class);
            }});
        }});

        reader.setRecordSeparatorPolicy(new BlankLineRecordSeparatorPolicy());

        return reader;
    }

    @Bean
    public JdbcCursorItemReader<Tiempo> tiempoMysqlReader(){
        JdbcCursorItemReader<Tiempo> cursorItemReader = new JdbcCursorItemReader<>();
        cursorItemReader.setDataSource(dataSource);
        cursorItemReader.setSql("SELECT * FROM tiempo");
        cursorItemReader.setRowMapper(new TiempoRowMapper());

        return cursorItemReader;
    }

    @Bean
    public JdbcCursorItemReader<Medicion> medicionMysqlReader(){
        JdbcCursorItemReader<Medicion> cursorItemReader = new JdbcCursorItemReader<>();
        cursorItemReader.setDataSource(dataSource);
        cursorItemReader.setSql( "SELECT "
                + "t.provincia AS provincia, "
                + "MONTH(tr.fecha_prediccion) AS mes, "
                + "COUNT(*) AS numero_mediciones, "
                + "YEAR(tr.fecha_prediccion) AS year, "
                + "AVG(t.temperatura) AS temperatura_media "
                + "FROM tiempo_riesgo tr, tiempo t "
                + "WHERE tr.tiempo_id = t.tiempo_id "
                + "GROUP BY provincia, mes, year "
                + "ORDER BY provincia ASC, mes ASC"
        );
        cursorItemReader.setRowMapper(new MedicionRowMapper());

        return cursorItemReader;
    }

    //Processors

    @Bean
    public ItemProcessor<TiempoDto, Tiempo> tiempoItemProcessor(){
        return new TiempoItemProcessor();
    }

    @Bean
    public ItemProcessor<Tiempo, TiempoRiesgo> tiempoRiesgoItemProcessor(){
        return new TiempoRiesgoItemProcessor();
    }

    @Bean
    public ItemProcessor<Medicion, Medicion> medicionItemProcessor(){
        return new MedicionItemProcessor();
    }

    //Writers

    @Bean
    public ItemWriter<Tiempo> tiempoItemWriter(){
        return new TiempoItemWriter();
    }

    @Bean
    public ItemWriter<TiempoRiesgo> tiempoRiesgoItemWriter() { return new TiempoRiesgoItemWriter(); }

    @Bean
    public ItemWriter<Medicion> medicionItemWriter() {
        return new MedicionItemWriter();
    }

    //Decider

    @Bean
    public JobExecutionDecider tiempoJobDecider() {
        return new TiempoJobDecider();
    }

    //Listeners
    @Bean
    public JobExecutionListener listener() {
        return new JobListener();
    }

    //Step
    @Bean
    public Step stepCsvTiempo(ErrorCounterComponent errorCounterComponent) {
        return stepBuilderFactory.get("step1")
                .<TiempoDto, Tiempo>chunk(5)
                .reader(tiempoReader())
                .processor(tiempoItemProcessor())
                .writer(tiempoItemWriter())
                .listener(errorCounterComponent)
                .build();
    }

    @Bean
    public Step stepRiesgoTiempo() {
        return stepBuilderFactory.get("step2")
                .<Tiempo, TiempoRiesgo>chunk(5)
                .reader(tiempoMysqlReader())
                .processor(tiempoRiesgoItemProcessor())
                .writer(tiempoRiesgoItemWriter())
                .build();
    }

    @Bean
    public Step stepMediciones() {
        return stepBuilderFactory.get("step3")
                .<Medicion, Medicion>chunk(5)
                .reader(medicionMysqlReader())
                .processor(medicionItemProcessor())
                .writer(medicionItemWriter())
                .build();
    }

    //Job
    @Bean
    public Job jobCsvTiempo(ErrorCounterComponent errorCounterComponent){
        return jobBuilderFactory.get("jobCsvTiempo")
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .start(stepCsvTiempo(errorCounterComponent))
                .next(tiempoJobDecider())
                .on("FAILED").fail()
                .from(tiempoJobDecider()).on("CONTINUE")
                .to(stepRiesgoTiempo())
                .next(stepMediciones())
                .end()
                .build();
    }

}

