package com.jh.boot3.aop;

import org.springframework.stereotype.Component;

@Component
public class Transfer {

	public void bus() {
		System.out.println("bus 탐");
	}
	
	public void subway() {
		System.out.println("subway 탐");
	}
}
