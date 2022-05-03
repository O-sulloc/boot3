package com.jh.boot3.schedule;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jh.boot3.board.BoardMapper;
import com.jh.boot3.board.BoardVO;
import com.jh.boot3.util.Pager;

@Component
public class TestSchedule {
	
	@Autowired
	private BoardMapper boardMapper;

	//fixedRate1: 초 간격으로 이 메서드를 반복해라. //initialDelayString 2초 뒤에 이 메서드를 실행해라
	//@Scheduled(fixedRate = 1000, initialDelayString = "2000")
	public void fixRatedSchedule() throws Exception{
		Calendar calendar = Calendar.getInstance();
		
		System.out.println(calendar.getTime());
		Thread.sleep(2000);
		//2초 동안 자
	}
	
	//@Scheduled(fixedDelay = 1000, initialDelayString = "2000")
	public void fixDelaySchedule() throws Exception{
		Calendar calendar = Calendar.getInstance();
		
		System.out.println(calendar.getTime());
		Thread.sleep(2000);
	}
	
	@Scheduled(cron = "50 * * * * *")
	public void cronSchedule() throws Exception{
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.getTime());
		Pager pager = new Pager();
		pager.makeRow();
		
		List<BoardVO> ar=boardMapper.getList(pager);
		System.out.println(ar);
	}
}