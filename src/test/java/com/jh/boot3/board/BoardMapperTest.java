package com.jh.boot3.board;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jh.boot3.util.Pager;

@SpringBootTest
class BoardMapperTest {

	@Autowired
	private BoardMapper boardMapper;

	// @Test
	void setFileAdd() throws Exception {
		BoardFilesVO boardFilesVO = new BoardFilesVO();
		boardFilesVO.setNum(5L);
		boardFilesVO.setFileName("J fn");
		boardFilesVO.setOriName("J on");
		int result = boardMapper.setFileAdd(boardFilesVO);

		// assertEquals(1, result);
	}

	// @Test
	void setUpdate() throws Exception {
		BoardVO boardVO = new BoardVO();
		boardVO.setNum(4L);
		boardVO.setTitle("update");
		boardVO.setContents("update");
		int result = boardMapper.setUpdate(boardVO);
		assertEquals(1, result);
	}

	// @Test
	void setDelete() throws Exception {
		BoardVO boardVO = new BoardVO();
		boardVO.setNum(3L);
		int result = boardMapper.setDelete(boardVO);
		assertEquals(1, result);
	}

	// @Test
	void setAdd() throws Exception {

		for (int i = 0; i < 100; i++) {
			if (i % 10 == 0) {
				Thread.sleep(1000);
			}

			BoardVO boardVO = new BoardVO();
			boardVO.setTitle("addTitle" + i);
			boardVO.setContents("addContents" + i);
			boardVO.setWriter("addWriter" + i);
			int result = boardMapper.setAdd(boardVO);

		}
		System.out.println("finish");
		// assertEquals(1, result);
	}

	@Test
	void getList() throws Exception {
		Pager pager = new Pager();
		pager.makeRow();
		List<BoardVO> ar = boardMapper.getList(pager);
		System.out.println(ar);
		assertEquals(10, ar.size());
	}

	// @Test
	void test() throws Exception {
		BoardVO boardVO = new BoardVO();
		boardVO.setNum(4L);
		boardVO = boardMapper.getDetail(boardVO);

		System.out.println(boardVO.toString());
		assertNotNull(boardVO);
	}

}
