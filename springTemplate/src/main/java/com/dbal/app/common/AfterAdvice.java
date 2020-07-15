package com.dbal.app.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AfterAdvice {

	
//	@Pointcut("execution(* com.dbal..*Impl.*(..))")  //aop:pointcut 같음
//	public void allpointcut() {}
//	
//	
//	@AfterReturning("allpointcut()") //args는 없다
//	public void afterLog(JoinPoint jp) {
//		String method = jp.getSignature().getName();
//		System.out.println("[사후 로그 기록] " +
//							"method name : " + method);
//	}
	
	@AfterReturning(pointcut = "BeforeAdvice.allpointcut()", returning="returnObj")
	public void afterLog(JoinPoint jp, Object returnObj) {
		String method = jp.getSignature().getName();
		String result = returnObj != null ? returnObj.toString() : "no return";
		System.out.println("[사후로그]" + " method name : " + method+ ", result : " + result);
	}
}
