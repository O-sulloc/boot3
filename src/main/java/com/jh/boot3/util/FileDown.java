package com.jh.boot3.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

//component 아무것도 안 적으면, 클래스 이름이랑 똑같이 생성됨.(첫글자만 소문자로 바꿔서)
@Component
public class FileDown extends AbstractView {

	@Autowired
	private ServletContext servletContext;

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 1. 파일이 저장된 경로 설정
		String path = "resources/upload/";
		path = path + (String) model.get("board");
		// 보드컨트롤러 getBoard 메서드에 담아둔 board를 꺼내옴
		// 그럼 결과적으로 resources/upload/notice나 resources/upload/qna 등이 되겠지
		path = servletContext.getRealPath(path);

		// 2. model에서 파일 객체 get해오기(꺼내기)
		// 형변환
		FileVO fileVO = (FileVO) model.get("fileVO");

		// 3. 위의 정보를 담는 파일 객체 생성
		File file = new File(path, fileVO.getFileName());

		// 한글 처리
		response.setCharacterEncoding("UTF-8");

		// 총 파일의 크기
		response.setContentLengthLong(file.length());

		// 다운로드시 파일 이름을 인코딩 처리
		String fileName = URLEncoder.encode(fileVO.getOriName(), "UTF-8");

		// header 설정
		response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
		response.setHeader("Content-Transfer-Encoding", "binary");

		// HDD에서 파일을 읽고
		FileInputStream fi = new FileInputStream(file);
		// Client로 전송 준비
		OutputStream os = response.getOutputStream();

		// 전송
		FileCopyUtils.copy(fi, os);

		os.close();
		fi.close();
	}
}
