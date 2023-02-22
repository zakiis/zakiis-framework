package com.zakiis.security.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

import com.zakiis.core.constants.ZakiisStarterConstants;
import com.zakiis.security.aspect.RateLimitAspect;
import com.zakiis.security.service.RateLimitService;
import com.zakiis.security.service.impl.RedisRateLimitService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@AutoConfiguration
@EnableConfigurationProperties(OptimisticLockProperties.class)
@ConditionalOnProperty(prefix = ZakiisStarterConstants.SECURITY_RATE_LIMIT_PREFIX , name = "enabled" , havingValue = "true", matchIfMissing = true)
@ConditionalOnClass(name = "org.springframework.data.redis.core.RedisTemplate")
public class RateLimitAutoConfiguration {
	
	@Bean
	@ConditionalOnMissingBean
	public RateLimitAspect rateLimitAspect(RateLimitProperties rateLimitProperties,
			RateLimitService limitService) {
		log.info("Feature Rate limit aspect enabled.");
		return new RateLimitAspect(rateLimitProperties, limitService);
	}

	@Bean
	@ConditionalOnMissingBean
	public RateLimitService rateLimitService(RedisTemplate<String, Object> redisTemplate) {
		return new RedisRateLimitService(redisTemplate);
	}
}
