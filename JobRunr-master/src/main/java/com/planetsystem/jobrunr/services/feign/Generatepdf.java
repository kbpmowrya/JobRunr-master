package com.planetsystem.jobrunr.services.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// https://stackoverflow.com/questions/74593433/consider-defining-a-bean-of-type-org-springframework-cloud-openfeign-feignconte/74607414#74607414
//@FeignClient(url="http://localhost:9090",name = "TelaReportClient")	
@FeignClient(url="${tela.report.url}",name = "TelaReportClient")
public interface Generatepdf {
	
	@GetMapping("/report/{format}")
	public String generatepdfOfEmployee(@PathVariable("format") String format);
}