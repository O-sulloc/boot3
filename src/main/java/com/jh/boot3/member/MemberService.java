package com.jh.boot3.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jh.boot3.util.FileManager;

@Service
public class MemberService {

	@Autowired
	private MemberMapper memberMapper;

	@Autowired
	private FileManager fileManager;
	
	//properties 파일에 개발자가 커스텀한 속성값을 반환할 수 있다.
	@Value("${member.role.member}")
	private String memberRole;
	
	// delete setDelete 탈퇴
	public int setDelete(MemberVO memberVO)throws Exception{
		MemberFilesVO memberFilesVO = memberMapper.getFileDetail(memberVO);
		
		int result = memberMapper.setDelete(memberVO);
		
		boolean fileResult = fileManager.fileDelete(memberFilesVO.getFileName(), "resources/upload/member/");
						
		
		return result;
	}
	
	// update setUpdate
	public int setUpdate(MemberVO memberVO)throws Exception{
		return memberMapper.setUpdate(memberVO);
	}
	
	// mypage getDetail
	public MemberVO getDetail(MemberVO memberVO)throws Exception{
		return memberMapper.getDetail(memberVO);
	}
	
	// login
	public MemberVO getLogin(MemberVO memberVO) throws Exception {
		return memberMapper.getLogin(memberVO);
	}

	// 회원가입
	public int setAdd(MemberVO memberVO, MultipartFile file)throws Exception{
		
		int result = memberMapper.setAdd(memberVO);
		
		Map<String, String> map = new HashMap<>();
		map.put("id", memberVO.getId());
		map.put("roleName", memberRole);
		//원래는 ROLE_MEMBER가 2번인데 번호로 하면 좀 위험한듯. 그래서 걍 ROLE_MEMBER를 키값으로 넣기로 했다.
		//근데 그렇게 하는 것도 위험함. 만약에 role_member를 role_people로 이름을 바꾸고 싶음 어케
		//그래서 properties에 커스텀해놓은걸 불러오기로 했다
		
		result = memberMapper.setMemberRole(map);
		
		if(!file.isEmpty()) {
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
