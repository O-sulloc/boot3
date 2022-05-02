package com.jh.boot3.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jh.boot3.interceptor.AdminInterceptor;
import com.jh.boot3.interceptor.BoardInterceptor;
import com.jh.boot3.interceptor.SellerInterceptor;
import com.jh.boot3.interceptor.WriterCheckInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{

	@Autowired
	private SellerInterceptor sellerInterceptor;
	
	@Autowired
	private AdminInterceptor adminInterceptor;
	
	@Autowired
	private BoardInterceptor boardInterceptor;
	
	@Autowired
	private WriterCheckInterceptor writerCheckInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//여기서 적용할 인터셉터를 레지스트리에 등록한다.
		
		registry.addInterceptor(sellerInterceptor)
		//인터셉터가 사용할  rul을 이어서 적어준다. 위에 세미클론 안찍고
				.addPathPatterns("/product/manage");
		//계속 이렇게 쓰면 댐 .addPathPatterns("/product/manage")
		
		//인터셉터 제외할 url 알려주고 싶으면
				//.excludePathPatterns(null) 이렇게 쓴다.
		
		registry.addInterceptor(adminInterceptor)
				.addPathPatterns("/admin/manage");
		
		registry.addInterceptor(boardInterceptor)
				.addPathPatterns("/board/*")
				.excludePathPatterns("/board/list");
				//list빼고 다 인터셉터 처리해줘
		
		registry.addInterceptor(writerCheckInterceptor)
				.addPathPatterns("/board/update")
				.addPathPatterns("/board/delete");

		
		
				WebMvcConfigurer.super.addInterceptors(registry);
	}
	
}
