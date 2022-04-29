package com.jh.boot3.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	//파일 detail
	public MemberFilesVO getFileDetail(MemberVO memberVO)throws Exception;
	
	//회원 파일 등록
	public int setFileAdd(MemberFilesVO memberFilesVO) throws Exception;
	
	//delete탈퇴
	public int setDelete(MemberVO memberVO) throws Exception;
	
	//update
	public int setUpdate(MemberVO memberVO) throws Exception;
	
	//mypage
	public MemberVO getDetail(MemberVO memberVO) throws Exception;
	
	//로그인
	public MemberVO getLogin(MemberVO memberVO) throws Exception;
	
	//회원가입
	public int setAdd(MemberVO memberVO) throws Exception;
	
}