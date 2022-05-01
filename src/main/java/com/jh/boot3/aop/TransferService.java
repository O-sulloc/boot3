package com.jh.boot3.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransferService {

	@Autowired
	private Transfer transfer;
	
	public void go() {
		//payment.cardCheck();
		transfer.bus();
		//payment.cardCheck();
		
		//payment.cardCheck();
		transfer.subway();
		//payment.cardCheck();
		
		//payment.cardCheck();
		transfer.bus();
		//payment.cardCheck();
		
		//이렇게 carcheck 6번씩 개발자가 써주는거 너무 비효율인 것 같음..
		//그래서 나온게 관점지향AOP (aspect 관찰하다)
		//스프링이 프로그램 돌아가는걸 계속 지켜보고 있다가 필요하다 싶으면 cardcheck 메서드를 자동으로 호출해주는거임 (무섭다..)
		//물론 당근 자동은 아니고 개발자가 셋팅을 해줘야함
	}
}
