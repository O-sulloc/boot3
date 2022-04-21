package com.jh.boot3.member;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberMapperTest {

	@Autowired
	private MemberMapper memberMapper;

	@Test
	void setAdd() throws Exception {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("id3");
		memberVO.setName("id3");
		memberVO.setEmail("id3");
		memberVO.setPw("id3");
		memberVO.setPhone("id3");
		int result = memberMapper.setAdd(memberVO);
		assertEquals(1, result);
	}
}
