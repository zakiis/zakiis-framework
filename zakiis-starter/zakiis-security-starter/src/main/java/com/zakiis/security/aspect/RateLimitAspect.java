package com.zakiis.security.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import com.zakiis.core.exception.ZakiisRuntimeException;
import com.zakiis.security.annotation.RateLimit;
import com.zakiis.security.config.RateLimitProperties;
import com.zakiis.security.service.RateLimitService;
import com.zakiis.security.util.RateLimitUtil;

import lombok.RequiredArgsConstructor;

@Aspect
@RequiredArgsConstructor
public class RateLimitAspect {

	private final RateLimitProperties rateLimitProperties;
	private final RateLimitService limitService;
	
	/**
     * @within 方法所属类如果包含了注解则会被拦截
     * @annotation 方法上如果包含了注解则会被拦截
     */
    @Pointcut("@within(com.zakiis.security.annotation.RateLimit) || @annotation(com.zakiis.security.annotation.RateLimit)")
    public void limitPointcut() {}
    
    @Around("limitPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    	if (!rateLimitProperties.isEnabled()) {
    		return joinPoint.proceed();
    	}
    	RateLimit annotation = RateLimitUtil.getAnnotation(joinPoint, RateLimit.class);
    	if (annotation == null) {
    		throw new ZakiisRuntimeException("Can't find @Limit annotation.");
    	}
    	String limitKey;
    	String limitKeyEL = annotation.limitKeyEL();
    	MethodSignature signature = (MethodSignature)joinPoint.getSignature();
    	if (RateLimit.NULL_STRING.equals(limitKeyEL)) {
    		limitKey = RateLimitUtil.genRateLimitKey(signature.getDeclaringTypeName(), signature.getName(), joinPoint.getArgs());
    	} else {
    		limitKey = RateLimitUtil.getValBySpEL(limitKeyEL, signature, joinPoint.getArgs());
    	}
    	if (limitKey == null) {
    		throw new ZakiisRuntimeException("Can't generate limit key");
    	}
    	synchronized (limitKey.intern()) {
    		limitService.validate(limitKey, annotation.maxRequestPerDay());
    		if (annotation.ignoreFailure()) {
    			Object result = joinPoint.proceed();
    			limitService.request(limitKey, annotation.minInterval(), annotation.maxRequestPerDay());
    			return result;
    		} else {
    			limitService.request(limitKey, annotation.minInterval(), annotation.maxRequestPerDay());
    			return joinPoint.proceed();
    		}
		}
    }
}
