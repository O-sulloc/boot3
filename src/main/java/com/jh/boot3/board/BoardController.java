package com.jh.boot3.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jh.boot3.util.FileVO;
import com.jh.boot3.util.Pager;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@ModelAttribute("board")
	public String getBoard() {
		return "board";
	}

	@GetMapping("fileDown")
	public ModelAndView getFileDown(BoardFilesVO boardFilesVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		boardFilesVO = boardService.getFileDetail(boardFilesVO);

		mv.addObject("fileVO", boardFilesVO);
		// fileDown.class 가보면 FileVO fileVO = (FileVO) model.get("fileVO")로 담아둠
		// 그거 그대로 꺼내온거

		mv.setViewName("fileDown");// Bean(=class)의 이름과 동일하게 작성
		//fileDown에 component로 만든 bean 말하는거임.

		return mv;
	}

	@GetMapping("delete")
	public ModelAndView setDelete(BoardVO boardVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		int result = boardService.setDelete(boardVO);
		
		mv.setViewName("redirect:./list");

		return mv;
	}

	@PostMapping("update")
	public ModelAndView setUpdate(BoardVO boardVO, ModelAndView mv) throws Exception {
		int result = boardService.setUpdate(boardVO);
		mv.setViewName("redirect:./list");
		return mv;
	}

	@GetMapping("update")
	public ModelAndView setUpdate(BoardVO boardVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		boardVO = boardService.getDetail(boardVO);
		mv.setViewName("board/update");
		mv.addObject("vo", boardVO);
		return mv;
	}

	@GetMapping("detail")
	public ModelAndView getDetail(BoardVO boardVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/detail");
		boardVO = boardService.getDetail(boardVO);
		mv.addObject("vo", boardVO);
		return mv;
	}

	@PostMapping("add")
	public ModelAndView setAdd(BoardVO boardVO, MultipartFile [] files) throws Exception {
		ModelAndView mv = new ModelAndView();

		int result = boardService.setAdd(boardVO, files);

		mv.setViewName("redirect:./list");
		return mv;
	}

	@GetMapping("add")
	public void setAdd() throws Exception {
	}

	@GetMapping("list")
	public ModelAndView getList(Pager pager) throws Exception {
		ModelAndView mv = new ModelAndView();
		List<BoardVO> ar = boardService.getList(pager);
		mv.setViewName("board/list");
		mv.addObject("list", ar);
		mv.addObject("pager", pager);

		return mv;
	}
}
