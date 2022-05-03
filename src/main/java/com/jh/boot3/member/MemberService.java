package com.jh.boot3.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
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
	
	//0503 사용자 정의 검증 메서드 (비번 ,비번체크 일치하는지 확인)
	public boolean memberError(MemberVO memberVO, BindingResult bindingResult) throws Exception{
		//비번 두 개 받아와야되니까 매개변수 멤버vo
		boolean check = false;
		//false면 검증 성공(에러없음)
		//true면 에러 있음
		
		//1. 어노테이션 기본 검증 결과 받아주기. bindingresult
		check = bindingResult.hasErrors();
		
		//2. pw랑 pwcheck가 일치하는지 개발자가 수동으로 검증해줌
		if(!memberVO.getPw().equals(memberVO.getCheckPw())) {
			check=true;
			bindingResult.rejectValue("checkPw", "member.password.notEqual");
			//비번 불일치하면 checkPw에 메세지 출력해줘. 메세지는 member.password.notEqual에 담아놨어
		}
		
		//3. id 중복 검사
		MemberVO idCheck=memberMapper.getId(memberVO);
		if(idCheck != null) {
			//id에 뭐가 들어와 있으면 중복되는지 확인하는건데
			//왜 아무것도 안ㄷ르어가있는데 지 맘대로 메세지 출력하는데??
			check = true;
			bindingResult.rejectValue("id", "member.id.equal");
		}
		
		return check;
	}
	
	//findid
	public MemberVO getFindId(MemberVO memberVO) throws Exception{
		return memberMapper.getFindId(memberVO);
	}
	
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
