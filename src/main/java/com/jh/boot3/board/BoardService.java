package com.jh.boot3.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jh.boot3.util.Pager;

@Service
public class BoardService {

	@Autowired
	private BoardMapper boardMapper;

	public void setAdd() throws Exception{
		
	}
	
	public List<BoardVO> getList(Pager pager) throws Exception {
		pager.makeRow();
		return boardMapper.getList(pager);
	}
}
