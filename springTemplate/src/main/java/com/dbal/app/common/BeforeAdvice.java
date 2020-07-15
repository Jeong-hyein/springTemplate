package com.dbal.app.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service //bean 등록하는거
@Aspect //xml파일에서 aop:aspect랑 같음
public class BeforeAdvice {

	@Pointcut("execution(* com.dbal..*Impl.*(..))")  //aop:pointcut 같음
	public void allpointcut() {}
	
	@Before("allpointcut()")  //aop:before
	public void beforeLog(JoinPoint jp) {
		String method = jp.getSignature().getName();
		String arg = jp.getArgs() != null && jp.getArgs().length >0 ? 
					jp.getArgs()[0].toString() : "";
		System.out.println("[사전 로그 annotation 실행] " +
							"method name : " + method +
							"args : " + arg);
	}
	
	
}
