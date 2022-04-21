package com.jh.boot3.member;

import com.jh.boot3.util.FileVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MemberFilesVO extends FileVO{

	private String id;
}
