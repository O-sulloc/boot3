package com.jh.boot3.start;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

	//백엔드는 동기, 비동기와는 전혀 연관관계가 없음.
	//비동기, 동기는 프론트에서나 좀 이슈됐었어요.
	
	@GetMapping("/")
	public String start() {
		return "index";
	}
	
	@GetMapping("/getTest")
	public String getTest(String msg) {
		//$.get("./getTest?msg="+v 
		//여기서 msg가 parameter로 오는거니까 그걸 처리해주는 컨트롤러에도 받아주는 파라미터가 있어야겠지
		
		System.out.println("GetTest 요청이 발생했다.");
		System.out.println("msg: " + msg);
		
		return "common/getResult";
		//getTest 요청이 오면 SEB-INF/views/common/getResult로 가
		
	}
	
	@PostMapping("/postTest")
	public String postTest(String msg) {
		System.out.println("PostTest 요청이 발생했다.");
		System.out.println("msg: " + msg);
		return "common/getResult";
	}
	
	@PostMapping("/arrayTest")
	public String arrayTest(String msg, String [] numbers, String [] nums) {
		System.out.println("PostTest 요청이 발생했다.");
		System.out.println("msg: " + msg);
		
		for(String str : numbers) {
			System.out.println(str);
		}
		
		for(String num:nums) {
			System.out.println(num);
		}
		
		return "common/getResult";
	}
}
