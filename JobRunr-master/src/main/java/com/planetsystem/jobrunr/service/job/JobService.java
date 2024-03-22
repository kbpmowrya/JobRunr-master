package com.planetsystem.jobrunr.service.job;

import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.jobs.context.JobContext;
import org.jobrunr.jobs.context.JobDashboardProgressBar;
import org.jobrunr.jobs.context.JobRunrDashboardLogger;
import org.jobrunr.spring.annotations.Recurring;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planetsystem.jobrunr.services.feign.Generatepdf;

@Service
public class JobService {
	@Autowired
	private Generatepdf generatepdf;
	
    /*public JobService(Generatepdf generatepdf) {
		super();
		this.generatepdf = generatepdf;
	}*/

	private static final Logger LOGGER = new JobRunrDashboardLogger(LoggerFactory.getLogger(JobService.class));
    //0 0/15 * * *
    @Recurring(id = "my-recurring-job", cron = "*/2 * * * *")
    @Job(name = "My recurring job")
    public void doRecurringJob() {
        System.out.println("Doing some work without arguments");
    }
    @Recurring(id = "my-recurring-job-generatepdf", cron = "*/2 * * * *")
    @Job(name = "Generate PDF Job")
    public void generatePdfJob() {
    	System.out.println(generatepdf.generatepdfOfEmployee("pdf"));
//    	String str = generatepdf.generatepdfOfEmployee("pdf");
    	System.out.println("Generate PDF Job started");
    }
    public void doSimpleJob(String anArgument) {
        System.out.println("Doing some work: " + anArgument);
    }

    public void doLongRunningJob(String anArgument) {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println(String.format("Doing work item %d: %s", i, anArgument));
                Thread.sleep(20000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void doLongRunningJobWithJobContext(String anArgument, JobContext jobContext) {
        try {
            LOGGER.warn("Starting long running job...");
            final JobDashboardProgressBar progressBar = jobContext.progressBar(10);
            for (int i = 0; i < 10; i++) {
                LOGGER.info(String.format("Processing item %d: %s", i, anArgument));
                System.out.println(String.format("Doing work item %d: %s", i, anArgument));
                Thread.sleep(15000);
                progressBar.increaseByOne();
            }
            LOGGER.warn("Finished long running job...");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
