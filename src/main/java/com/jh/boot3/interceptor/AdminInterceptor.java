package com.jh.boot3.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.jh.boot3.member.MemberVO;
import com.jh.boot3.member.RoleVO;

@Component
public class AdminInterceptor implements HandlerInterceptor{

	@Value("${member.role.admin}")
	private String roleName;
	//roleName에는 이제 ROLE-ADMIN이 들어가잇는겨
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		boolean check = false;
		//check true면 통과 false면 입뺀
		
		HttpSession session = request.getSession();
		MemberVO memberVO=(MemberVO) session.getAttribute("member");
		//멤버컨트롤러에서 login할 때 회원 정보를 session에 담아놨잖아. 이름은 멤버로. 그걸 꺼내오는거임
		//어트리뷰트는 오브젝트 타입이라 형변환 해줘야 함.
		
		if(memberVO != null) {
			for(RoleVO roleVO:memberVO.getRoleVOs()) {
			//멤버의 롤을 하나씩 꺼내서 rolevo에 넣어
				if(roleVO.getRoleName().equals(roleName)) {
				//rolename이 ROLEADMIN이라면
					check=true;
					//통과
				}
			}
		}
		if(!check) {
			//입뺀당했을때 들을말
			request.setAttribute("msg", "권한이 없습니다.");
			request.setAttribute("path", "../");
			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/getResult.jsp");
			view.forward(request, response);
		}
		
		return check;
	}
}
