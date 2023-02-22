package com.zakiis.job.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.zakiis.job.service.QuartzService;

@AutoConfiguration
public class QuartzAutoConfiguration {

	@Bean
	public QuartzService quartzService() {
		return new QuartzService();
	}
}
