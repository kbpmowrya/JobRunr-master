package com.planetsystem.jobrunr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.commons.httpclient.HttpClientConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@SpringBootApplication
@EnableFeignClients("com.planetsystem")
@ImportAutoConfiguration({FeignAutoConfiguration.class, HttpClientConfiguration.class})
public class JobRunrApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobRunrApplication.class, args);
	}

}
