package com.jh.boot3.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jh.boot3.util.FileManager;
import com.jh.boot3.util.Pager;

@Service
public class BoardService {

	@Autowired
	private BoardMapper boardMapper;

	@Autowired
	private FileManager fileManager;

	public BoardFilesVO getFileDetail(BoardFilesVO boardFilesVO) throws Exception{
		return boardMapper.getFileDetail(boardFilesVO);
	}
	
	public int setDelete(BoardVO boardVO) throws Exception {
		List<BoardFilesVO> ar = boardMapper.getFileList(boardVO);
		int result = boardMapper.setDelete(boardVO);
		
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

	public int setAdd(BoardVO boardVO, MultipartFile[] files) throws Exception {
		System.out.println("insert 전:" + boardVO.getNum());
		int result = boardMapper.setAdd(boardVO);
		System.out.println("insert 후:" + boardVO.getNum());

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
			boardMapper.setFileAdd(boardFilesVO);
		}

		return result;
	}

	public List<BoardVO> getList(Pager pager) throws Exception {
		pager.makeRow();
		pager.makeNum(boardMapper.getTotalCount(pager));
		return boardMapper.getList(pager);
	}
}
