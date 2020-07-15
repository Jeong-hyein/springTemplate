package com.dbal.app.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import org.springframework.util.StopWatch;
@Service
@Aspect
public class AroundAdvice {
	
	@Around("BeforeAdvice.allpointcut()")
	public Object execute(ProceedingJoinPoint pjp) throws Throwable{
		
		
		System.out.println("[around 전]");
		//비즈니스 수행전 처리비용
		StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		//서비스 호출
		Object result = pjp.proceed();
		stopwatch.stop();
		System.out.println("[around 후] 실행시간 : " + stopwatch.getTotalTimeMillis());
		return result;
	}
}
