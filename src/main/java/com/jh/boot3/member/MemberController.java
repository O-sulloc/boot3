package com.jh.boot3.member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	@ModelAttribute("member")
	public String getMember() {
		return "member";
	}

	@PostMapping("login")
	public String getLogin(HttpSession session, MemberVO memberVO, String remember, Model model,
			HttpServletResponse response) throws Exception {
		// cooke 발행하기
		if (remember != null && remember.equals("1")) {
			// 쿠키 생성
			Cookie cookie = new Cookie("remember", memberVO.getId());
			// 위에서 로그인 성공한 id가 memberVO에 들어가있으니까.. 거기 있는 id 꺼내는거

			// cookie.setPath("/");
			cookie.setMaxAge(-1);

			// 이제 응답으로 내보내야됨. 이걸
			response.addCookie(cookie);
		} else {
			Cookie cookie = new Cookie("remember", "");
			cookie.setMaxAge(0);
			response.addCookie(cookie);

		}

		memberVO = memberService.getLogin(memberVO);

		String path = "redirect:./login";
		// 경로 지정. 일단 로그인 실패하면 login폼으로 다시 돌아가는 걸 넣어둠.

		if (memberVO != null) {
			session.setAttribute("member", memberVO);

			path = "redirect:../";
		}

		return path;
	}

	@GetMapping("login")
	public void getLogin(Model model,
			@CookieValue(value = "remember", defaultValue = "", required = false) String rememberId) throws Exception {

	}

	@PostMapping("add")
	public ModelAndView setAdd(MemberVO memberVO, MultipartFile file) throws Exception {
		ModelAndView mv = new ModelAndView();
		int result = memberService.setAdd(memberVO, file);
		mv.setViewName("redirect:/");
		return mv;
	}

	@GetMapping("add")
	public ModelAndView setAdd(MemberVO memberVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/add");
		return mv;
	}
}
