package com.formacion.bosonit.block17batch.config.batch.listener;

import com.formacion.bosonit.block17batch.config.batch.util.ErrorCounterComponent;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.beans.factory.annotation.Autowired;

public class TiempoJobDecider implements JobExecutionDecider {
    @Autowired
    ErrorCounterComponent errorCounterComponent;
    private int maxErrors = 100;

    public void setMaxErrors(int maxErrors) {
        this.maxErrors = maxErrors;
    }

    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        if (errorCounterComponent.getErrorCounter().getCount() > maxErrors) {
            return new FlowExecutionStatus("FAILED");
        } else {
            return new FlowExecutionStatus("CONTINUE");
        }
    }
}
