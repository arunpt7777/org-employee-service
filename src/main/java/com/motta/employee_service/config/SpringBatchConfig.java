package com.motta.employee_service.config;

import com.motta.employee_service.entity.Employee;
import com.motta.employee_service.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import static com.motta.employee_service.util.EmployeeConstants.SPRING_BATCH_FILE_DELIMITER;

@Configuration
//@EnableBatchProcessing
@AllArgsConstructor
public class SpringBatchConfig {

    private EmployeeRepository employeeRepository;

    // FlatFileItemReader accepts the file for input
    @Bean
    public FlatFileItemReader<Employee> itemReader() {
        FlatFileItemReader<Employee> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("src/main/resources/employees.csv"));
        itemReader.setName("csvReader");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }

    // DelimitedLineTokenizer helps decide how to read the file (delimiter, heading, etc.)
    private LineMapper<Employee> lineMapper() {
        DefaultLineMapper<Employee> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(SPRING_BATCH_FILE_DELIMITER);
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("id", "employeeNumber", "age", "firstName", "lastName", "email", "phone", "gender", "salaryId");


        // BeanWrapperFieldSetMapper maps data to Java object
        BeanWrapperFieldSetMapper<Employee> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Employee.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

    // Processor interface helps process the data while read/write
    @Bean
    public EmployeeProcessor processor() {
        return new EmployeeProcessor();
    }

    // Uses Spring data JPA to persist data
    @Bean
    public RepositoryItemWriter<Employee> itemWriter() {
        RepositoryItemWriter<Employee> writer = new RepositoryItemWriter<>();
        writer.setRepository(employeeRepository);
        writer.setMethodName("save");
        return writer;
    }

    // Enables multi-threading for Spring batch jobs
    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(10);
        return asyncTaskExecutor;
    }

    @Bean
    public Step step(JobRepository repository, PlatformTransactionManager transactionManager){
        return new StepBuilder("csv-step",repository)
                .<Employee,Employee>chunk(10,transactionManager)
                .reader(itemReader())
                .processor(processor())
                .writer(itemWriter())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean(name="csvJob")
    public Job job(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return new JobBuilder("csv-job",jobRepository)
                .flow(step(jobRepository,transactionManager)).end().build();
    }

}
