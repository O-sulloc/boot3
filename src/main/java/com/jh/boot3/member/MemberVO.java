package com.jh.boot3.member;

import java.util.List;

import lombok.Data;

@Data
public class MemberVO {

	private String id;
	private String pw;
	private String name;
	private String email;
	private String phone;
	
	private MemberFilesVO memberFilesVO;
	
	private List<RoleVO> roleVOs;
	//멤버는 여러 역할을 가질 수 있음. 멤버이면서 동시에 판매자일 수도 있고
	//아니면 관리자이면서, 멤버, 판매자일 수도 있음
	//member입장에서는 1:n관계
}
