package com.dbal.app.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dbal.app.dumy.DumyService;

@Component
public class Scheduler {
	
	@Autowired
	DumyService dumyService;
		
//	Scheduled(초 분 시 일 월 요일)
	//@Scheduled(cron = "0/10 * * * * *")  //0에서 10초 간격, 매분, 매시 , 매일, 매월
	public void crontest1() {
		System.out.println("[스케쥴 실행]");
	}
	
	// 54때에 30초와 40초,50초
	// 30,40,50 56으로 해도 됨
	//@Scheduled(cron = "30/10 56 * * * *")
	public void crontest2() {
		System.out.println(dumyService.getTime());
		
	}
	//1초 1000
	//@Scheduled(fixedDelay = 2000)
	public void crontest3() {
		System.out.println(dumyService.getTime());
	}
	
	//@Scheduled(fixedRate = 2000)
	public void crontest4() {
		System.out.println(dumyService.getTime());
	}
}
