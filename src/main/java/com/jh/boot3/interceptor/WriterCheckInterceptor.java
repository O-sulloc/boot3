package com.jh.boot3.interceptor;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.jh.boot3.board.BoardMapper;
import com.jh.boot3.board.BoardVO;
import com.jh.boot3.member.MemberVO;

@Component
public class WriterCheckInterceptor implements HandlerInterceptor{

	@Autowired
	private BoardMapper boardMapper;
	
	/*@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		MemberVO memberVO = (MemberVO) request.getSession().getAttribute("member");
		
		Map<String, Object> map=modelAndView.getModel();
		//보드컨트롤러에 mv.addObject("vo", boardVO); 글 정보를 담아놔서 그걸로 vo.getwriter 하려고 했음
		//근데 이거 modelandview에 담겨있어서 꺼내려면 일단 getmodel로 model정보부터 꺼내와야됨. returntype이 Map<String, Object>
		BoardVO boardVO = (BoardVO) map.get("vo");
		//위에 설명했다시피 Map<String, Object>로 담겨있음. 그래서 vo의 값은 object타입임. 따라서 형변환
		
		if(!boardVO.getWriter().equals(memberVO.getId())) {
			//작성자와 로그인한 사람의 아이디가 같지 않다면
			//modelAndView.setViewName("redirect:./list");
			
			modelAndView.addObject("msg", "로그인이 필요합니다..");
			modelAndView.addObject("path", "../member/login");
			modelAndView.setViewName("common/getResult");
			//request어쩌구로 해도 됨.
		}
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}*/
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String method = request.getMethod();
		System.out.println(method);
		//똑같은 update url이라 post든 get이든 관계없이 똑같이 적용된다.
		//resques.getmethod찍어보면 get인지 post인지 알 수 있음.
		//만약에 get.post구분하고 싶으면 if(method==get)로 처리하면 되겟지
		
		Long num = Long.parseLong(request.getParameter("num")); 
		//parameter는 이름과 value 모두 string 형식이다.
		//근데 보드 글조회하려면 글번호가 필요한데, 그 글번호가 long타입이어야 해. 형변환해야함
		System.out.println(num);
		
		//그럼 이제 db로 역추적을 ㅎㅏ는거임. 이글을 쓴 사람의 아이디가 뭔지. 그리고 그 아이디랑 지금 접속한 사람의 아이디가 같은지
		//일치하면 인터셉터 통과, 불일치면 list페이지로 redirect
		//boardmapper의 getdetail(글 하나 불러오기) 사용하면 된다. 이걸로 db접속할수 있으니까.
		BoardVO boardVO = new BoardVO();
		boardVO.setNum(num);
		boardVO =boardMapper.getDetail(boardVO);
		
		System.out.println(boardVO.getWriter());
		MemberVO memberVO=(MemberVO) request.getSession().getAttribute("member");
		
		//boolean check = false;
		if(boardVO.getWriter().equals(memberVO.getId())) {
			//check=true;
			return true;
		}else {
			response.sendRedirect("./list");
			
			return false;
		}
		
	}
}
