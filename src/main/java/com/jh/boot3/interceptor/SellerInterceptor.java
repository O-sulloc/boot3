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
public class SellerInterceptor implements HandlerInterceptor{
	
	@Value("${member.role.seller}")
	private String roleName;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean check = false;
		
		//로그인한 사용자의 role이 ROLE_SELLER라면 인터셉터 통과 
		//SESSION에 있어		
		HttpSession session = request.getSession();
		MemberVO memberVO=(MemberVO) session.getAttribute("member");
		
		if(memberVO != null) {
			for(RoleVO roleVO: memberVO.getRoleVOs()) {
				//membervo.getroleVOS 꺼내면 list가 나옴. 그걸 하나씩 roleVO에 넣겠다고
				if(roleVO.getRoleName().equals(roleName)) {
					//위에 roleName에 ROLE_SELLER 담아둠.
					//이렇게만 입력하면 유지보수에 좋음. 나중에 ROLE_어쩌구로 바껴도 유지보수에 좋음.
					check=true;
				}
			}
		}
		
		//check가 false이면 진입 못하게 해줘야됨. 이건 servlet 코드로 해야한다.
		if(!check) {
		//if check=false
			
			//mv.addObject("키",밸류)
			request.setAttribute("msg", "권한이 없습니다.");
			request.setAttribute("path", "../");
			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/getResult.jsp");
			//mv.setviewname이랑 같은 의미
			//Dispatcher("") 여기에 주소를 적는데, /WEB-INF/views랑 .jsp 다 적어줘야하ㅁ
			
			view.forward(request, response);
			//저 url주소를 뷰에 넣어서 포워드로 보내ㅡㄴㄴ?
		}
		
		return check; 
	}
}