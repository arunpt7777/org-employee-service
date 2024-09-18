package com.motta.employee_service.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component; 

@Component
public class BatchScheduler { 

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	// Scheduler to run the Spring batch job daily at midnight
	@Scheduled(cron = "0 0 0 * * ?") // Schedule at midnight daily 
	public void performBatchJob() throws Exception { 
		JobParameters params = new JobParametersBuilder()
				.addString("JobID", String.valueOf(System.currentTimeMillis())) 
				.toJobParameters(); 
		jobLauncher.run(job, params); 
	} 
} 
