package com.jh.boot3.member;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class MemberVO {

	//@NotNull //널값들어오지못하게 검증
	//@NotEmpty //비어잇지않게 검증
	@NotBlank(message = "id는 필수입니다.")
	private String id;
	
	@Size(min=3, max=8, message = "비번은 최소 6글자 이상이어야 합니다.")
	private String pw;
	
	@NotBlank(message="name은 필수입니다.")
	private String name;
	
	@NotBlank(message="email 필수입니다.")
	private String email;
	
	private String phone;
	
	private MemberFilesVO memberFilesVO;
	
	private List<RoleVO> roleVOs;
	//멤버는 여러 역할을 가질 수 있음. 멤버이면서 동시에 판매자일 수도 있고
	//아니면 관리자이면서, 멤버, 판매자일 수도 있음
	//member입장에서는 1:n관계
}
