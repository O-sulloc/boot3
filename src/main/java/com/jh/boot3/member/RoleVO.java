package com.jh.boot3.member;

import lombok.Data;

@Data
public class RoleVO {

	private Long roleId;
	//1,2,3
	
	private String roleName;
	//MEMBER_ADMIN, MEMBER_MEMBER, MEMBER_SELLER 들어감
}
