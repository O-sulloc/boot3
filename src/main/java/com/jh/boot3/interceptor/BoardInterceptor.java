package com.jh.boot3.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.jh.boot3.member.MemberVO;
import com.jh.boot3.member.RoleVO;

@Component
public class BoardInterceptor implements HandlerInterceptor{

	@Value("${member.role.member}")
	private String roleName;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("boardinterpealsdjflaksdj");
		MemberVO memberVO = (MemberVO) request.getSession().getAttribute("member");
		boolean check=false;
		if(memberVO != null) {
			for(RoleVO roleVO: memberVO.getRoleVOs()) {
				if(roleVO.getRoleName().equals(roleName)) {
				check=true;
				}
			}
		}
		if(!check) {
			//입뺀당했을때 들을말
			request.setAttribute("msg", "로그인이 필요합니다..");
			request.setAttribute("path", "../member/login");
			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/getResult.jsp");
			view.forward(request, response);
		}
		return check;
	}
}
