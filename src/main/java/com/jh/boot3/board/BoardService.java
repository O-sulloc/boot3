package com.jh.boot3.board;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jh.boot3.util.FileManager;
import com.jh.boot3.util.Pager;

@Service
@Transactional(rollbackFor = Exception.class)
public class BoardService {

	@Autowired
	private BoardMapper boardMapper;

	@Autowired
	private FileManager fileManager;

	public boolean setSummerFileDelete(String fileName) throws Exception{
		//fileName에 있는 정보로 이름도 알 수 있고 경로로 알 수 있는데
		//filemanager.filedelete 메서드는 어쨌든 이름, 경로 둘다 필요하대
		
		fileName = fileName.substring(fileName.lastIndexOf("/")+1);
		//그래서 fileName을 쪼개기로 했다. 마지막 슬래쉬 +1한 것부터 가져와 (그럼 슬래쉬 빼고 이름만 가져올 수 있음)
		System.out.println(fileName);
		
		return fileManager.fileDelete(fileName, "/resources/upload/board/");
		//true가 찍히면 hdd에서 잘 지워졌다는 의미
	}
	
	public String setSummerFileUpload(MultipartFile files) throws Exception{
		//file을 hdd에 저장하고
		//저장된 파일명을 return해보기
		String fileName=fileManager.fileSave(files, "resources/upload/board");
		fileName = "/resources/upload/board/" + fileName;
		return fileName;
	}
	
	public BoardFilesVO getFileDetail(BoardFilesVO boardFilesVO) throws Exception{
		return boardMapper.getFileDetail(boardFilesVO);
	}
	
	public int setDelete(BoardVO boardVO) throws Exception {
		List<BoardFilesVO> ar = boardMapper.getFileList(boardVO);
		int result = boardMapper.setDelete(boardVO);
		System.out.println("file size: " + ar.size());
		
		for(BoardFilesVO f : ar) {
			fileManager.fileDelete(f.getFileName(), "resources/upload/board");
		}
		
		return result;
	}

	public int setUpdate(BoardVO boardVO) throws Exception {
		return boardMapper.setUpdate(boardVO);
	}

	public BoardVO getDetail(BoardVO boardVO) throws Exception {
		return boardMapper.getDetail(boardVO);
	}

	public int setAdd(BoardVO boardVO, MultipartFile [] files) throws Exception {
		System.out.println("insert 전:" + boardVO.getNum());
		int result = boardMapper.setAdd(boardVO);
		System.out.println("insert 후:" + boardVO.getNum());

		if(files != null && result >0) {
			for (MultipartFile mf : files) {
				if (mf.isEmpty()) {
					continue;
				}
				
				// 1. file을 hdd에 저장
				String fileName = fileManager.fileSave(mf, "resources/upload/board/");
				System.out.println(fileName);
				
				// 2. 저장된 정보를 db에 저장
				BoardFilesVO boardFilesVO = new BoardFilesVO();
				boardFilesVO.setNum(boardVO.getNum());
				// filenum의 글 번호를 어디서 가져오지?
				// autoIncrement로 들어가는데 일단 setAdd 쿼리 실행이 되어야 글 번호가 생기는데..
				// 그래서 boardMapper.setAdd(boardVO)가 먼저 실행되어야 돼.
				// 근데 글 번호를 알 수 있는 방법은 없는데..?
				// 그래서 mapper에서 useGeneratedKeys 함 (legacy배울 때 keyProperty 설정한 것과 같은 방법임)
				
				boardFilesVO.setFileName(fileName);
				boardFilesVO.setOriName(mf.getOriginalFilename());
				result = boardMapper.setFileAdd(boardFilesVO);
				
				if(result < 1) {
					//만약 파일이 안들어갔다면, board에 insert 된 것도 rollback해줘야지
					throw new SQLException();
				}
			}
			
		}
		

		return result;
	}

	public List<BoardVO> getList(Pager pager) throws Exception {
		pager.makeRow();
		pager.makeNum(boardMapper.getTotalCount(pager));
		return boardMapper.getList(pager);
	}
}
