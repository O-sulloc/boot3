package com.jh.boot3.member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@ModelAttribute("board")
	public String getBoard() {
		return "member";
	}
	
	@PostMapping("findId")
	public ModelAndView getFindId(MemberVO memberVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		memberVO=memberService.getFindId(memberVO);
		mv.addObject("idResult", memberVO);
		mv.setViewName("member/findIdResult");
		return mv;
	}
	
	@GetMapping("findId")
	public void getFindId() throws Exception{}
	
	@PostMapping("update")
	public ModelAndView setUpdate(MemberVO memberVO, HttpSession session)throws Exception{
		ModelAndView mv = new ModelAndView();
		MemberVO vo = (MemberVO)session.getAttribute("member");
		memberVO.setId(vo.getId());
		int result = memberService.setUpdate(memberVO);
		mv.setViewName("redirect:./mypage");
		return mv;
	}
	
	
	@GetMapping("update")
	public ModelAndView setUpdate(HttpSession session)throws Exception{
		ModelAndView mv = new ModelAndView();
		MemberVO memberVO =(MemberVO)session.getAttribute("member");
		memberVO = memberService.getDetail(memberVO);
		
		mv.addObject("vo", memberVO);
		mv.setViewName("member/update");
		return mv;
	}
	
	@GetMapping("mypage")
	public ModelAndView getMypage(HttpSession session)throws Exception{
		ModelAndView mv = new ModelAndView();
		MemberVO memberVO =(MemberVO)session.getAttribute("member");
		memberVO = memberService.getDetail(memberVO);
		
		mv.addObject("vo", memberVO);
		mv.setViewName("member/mypage");
		return mv;
	}
	
	@GetMapping("logout")
	public ModelAndView getLogout(HttpSession session)throws Exception{
		session.invalidate();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:../");
		return mv;
	}
	
	@PostMapping("login")
	public String getLogin(MemberVO memberVO,String remember, Model model, HttpSession session, HttpServletResponse response) throws Exception {
							//validated ??????????????? ??????. ?????? vo ?????? ?????? bindingreuslt??? ??????. ????????? ?????????.
		String path = "redirect:./login";
		// ?????? ??????. ?????? ????????? ???????????? login????????? ?????? ???????????? ??? ?????????.
		
		// cooke ????????????
		if (remember != null && remember.equals("1")) {
			// ?????? ??????
			Cookie cookie = new Cookie("remember", memberVO.getId());
			// ????????? ????????? ????????? id??? memberVO??? ?????????????????????.. ?????? ?????? id ????????????

			// cookie.setPath("/");
			cookie.setMaxAge(-1);

			// ?????? ???????????? ???????????????. ??????
			response.addCookie(cookie);
		} else {
			Cookie cookie = new Cookie("remember", "");
			cookie.setMaxAge(0);
			response.addCookie(cookie);

		}

		memberVO = memberService.getLogin(memberVO);



		if (memberVO != null) {
			session.setAttribute("member", memberVO);

			path = "redirect:../";
		}

		return path;
	}

	@GetMapping("login")
	public void getLogin(@ModelAttribute MemberVO memberVO, Model model,
			@CookieValue(value = "remember", defaultValue = "", required = false) String rememberId) throws Exception {
		
		//0502 ?????? form????????? ?????? ??? vo????????? jsp??? ????????????.
		//mv.addObject("vo", new MemberVO());
		//????????? ??????????????? ?????????????????? !modelattribute??? ?????? ???
		
	}

	@PostMapping("add")
	public ModelAndView setAdd(@Valid MemberVO memberVO,BindingResult bindingResult, MultipartFile files) throws Exception {
		ModelAndView mv = new ModelAndView();		

		/*if(bindingResult.hasErrors()) {
			mv.setViewName("member/add");
			return mv;
		}*/
		
		//????????? ????????? ????????? ?????? pw=pw ???????????????
		if(memberService.memberError(memberVO, bindingResult)) {
			mv.setViewName("member/add");
			return mv;
		}
		
		memberService.setAdd(memberVO, files);
		mv.setViewName("redirect:../");
		
		return mv;
	}

	@GetMapping("add")
	public ModelAndView setAdd(@ModelAttribute MemberVO memberVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/add");
		return mv;
	}
	
	@GetMapping("delete")
	public ModelAndView setDelete(HttpSession session)throws Exception{
		ModelAndView mv = new ModelAndView();
		MemberVO memberVO =(MemberVO)session.getAttribute("member");
		int result = memberService.setDelete(memberVO);
		
		session.invalidate();
		
		mv.setViewName("redirect:../");
		return mv;
	}
}
