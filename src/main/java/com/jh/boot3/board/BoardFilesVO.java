package com.jh.boot3.board;

import com.jh.boot3.util.FileVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BoardFilesVO extends FileVO {
	//@EqualsAndHashCode(callSuper = false)
	//상속 받으니까 lombok도 두번 상속받게 돼서 오류가 잇는데 그거 해결하려고 hashcode 적은거
	
	
	// private Long fileNum;
	private Long num;
	// private String fileName;
	// private String oriName;
	// 주석 처리한 세개 fileVO에서 상속받음
}
