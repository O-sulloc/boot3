package com.jh.boot3.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jh.boot3.util.FileManager;

@Service
public class MemberService {

	@Autowired
	private MemberMapper memberMapper;

	@Autowired
	private FileManager fileManager;
	// delete setDelete 탈퇴

	// update setUpdate

	// mypage getDetail

	// login
	public MemberVO getLogin(MemberVO memberVO) throws Exception {
		return memberMapper.getLogin(memberVO);
	}

	// 회원가입
	public int setAdd(MemberVO memberVO, MultipartFile file) throws Exception {
		int result = memberMapper.setAdd(memberVO);

		if (!file.isEmpty()) {
			String fileName = fileManager.fileSave(file, "resources/upload/member/");
			
			MemberFilesVO memberFilesVO = new MemberFilesVO();
			memberFilesVO.setId(memberVO.getId());
			memberFilesVO.setFileName(fileName);
			memberFilesVO.setOriName(file.getOriginalFilename());

			result = memberMapper.setFileAdd(memberFilesVO);
		}

		return result;

	}
}
