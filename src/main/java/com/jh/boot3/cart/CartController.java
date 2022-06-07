package com.jh.boot3.cart;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jh.boot3.member.MemberVO;

//@Controller
@RestController //모든 메서드에 responsebody 어노테이션을 선언하고 싶을 대 선언한다.
public class CartController {
	
	@Autowired
	private CartService cartService;

	
	
	@PostMapping("/cart/{productNum}/{count}")
	//@ResponseBody //restcontroller가 선언되어 있으면 생략 가능
	public int setAdd(HttpSession session,@PathVariable Long productNum, @PathVariable Long count) throws Exception{
		System.out.println("productNum:" + productNum);
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		
		CartVO cartVO = new CartVO();
		cartVO.setProductNum(productNum);
		cartVO.setId(memberVO.getId());
		cartVO.setCount(count);
		int result =cartService.setAdd(cartVO);
		System.out.println(result);
		
		return result;
		//responsebody어노테이션 쓰먀ㅕㄴ, 
	}
	
	@DeleteMapping("/cart/{cartNum}")
	public ModelAndView setDelete(@PathVariable Long cartNum) throws Exception{
		System.out.println("carnum:"+cartNum);
		return null;
	}
	
	//cart/뒤에 페이지 번호는 계속 바뀌니까 변수명으로 줌
	@GetMapping("/cart/{pn}")
	public List<CartVO> getList(@PathVariable Long pn, HttpSession session) throws Exception{
		//pathvariable? url주
		System.out.println("pn:"+pn);
		MemberVO memberVO=(MemberVO) session.getAttribute("member");
		
		CartVO cartVO = new CartVO();
		cartVO.setId(memberVO.getId());
		
		List<CartVO> ar = cartService.getList(cartVO);
		return ar;
	}
}
