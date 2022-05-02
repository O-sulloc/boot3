package com.jh.boot3.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration //legacy에서 ***-context.xml 하던거랑 같은 의미
public class MessageConfig implements WebMvcConfigurer{

	// 레거시에서 <beans class="">했던거. 여기서 하는 방법. @bean 보고 스프링이 빈인줄 알게됨.
	@Bean
	public LocaleResolver localeResolver () {
		//1. session을 이용해서 언어 구분하는 방법
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		
		sessionLocaleResolver.setDefaultLocale(Locale.KOREAN);
		//기본적으로 나오게 할 언어 뭘로 할거야? defaultlocale
		
		//2. 쿠키
		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		cookieLocaleResolver.setDefaultLocale(Locale.KOREAN);
		
		cookieLocaleResolver.setCookieName("lang");
		//나중에 쿠키 꺼낼때 어떤 이름으로 꺼내고 싶은지. (개발자맘)
		
		return cookieLocaleResolver;
		//return sessionLocaleResolver;
	}
	
	//인터셉터 객체 만드는 메서드를 만든다.
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		//리턴타입 LocaleChangeInterceptor 
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		//언어가 뭔지 어떻게 구분할거냐?
		//prarmeter가 ?lang=en 오면 영어로 바꾸ㅡㅓ죠
		
		return localeChangeInterceptor;
	}
}
