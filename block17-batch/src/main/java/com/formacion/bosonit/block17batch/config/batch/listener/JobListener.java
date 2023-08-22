package com.formacion.bosonit.block17batch.config.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

@Slf4j
public class JobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("Job Started: " + jobExecution.getJobInstance());
        log.info("Job Status: " +  jobExecution.getStatus());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("Job Ended");
        log.info("Job Status: " + jobExecution.getStatus());
    }
}
