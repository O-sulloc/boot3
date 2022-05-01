package com.jh.boot3.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Payment {
	//공통 로직 advice
	
	@Around("execution (* com.jh.boot3.aop.Transfer.*())")
	public Object cardCheck(ProceedingJoinPoint joinPoint) throws Throwable {
		// join point핵심 로직 메서드(bus, subway) 어쩌구
		
		System.out.println("탑승 전 카트 체크");
		
		Object object=joinPoint.proceed();
		//object 타입으로 핵심 로직의 리턴을 받아준다.
		
		System.out.println("하차 시 카드 체크");
		
		return object;
		//다시 돌려줘야돼
	}

	@Before("execution(* com.jh.boot3.board.BoardService.get*(..))")
	public void before() {
		System.out.println("---select-----");
		//보드서비스에서 get으로 가져오는 서비스가 실행되면, 실행 전 select가 찍히도록
	}

	@AfterReturning("execution(* com.jh.boot3.aop.Transfer.*())")
	public void afterReturning() {

	}
	
	@AfterThrowing("execution(* com.jh.boot3.board.BoardService.get*(..))")
	public void afterThrowing() {


	}
	
	@After("execution(* com.jh.boot3.board.BoardService.get*(..))")
	public void after() {
		//afterthrowing+afterreturning
	}
	
	//@Around("execution(* com.jh.boot3.board.BoardService.get*(..))")
	public void around() {
		//before+after
	}
}
