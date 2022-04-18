package com.jh.boot3.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jh.boot3.util.Pager;

@Mapper
public interface BoardMapper {

	// getFileList 매개변수 필요없음. 당연함. num 지정하지 않고 있는 파일 다 가져오는거니까. 나중에 페이저나 넣어라
	public List<BoardFilesVO> getFileList() throws Exception;

	// getFileDetail
	public BoardFilesVO getFileDetail(BoardFilesVO boardFilesVO) throws Exception;

	// setFileDelete delete
	public int setFileDelete(BoardFilesVO boardFilesVO) throws Exception;

	// setFileAdd insert
	public int setFileAdd(BoardFilesVO boardFilesVO) throws Exception;

	// getdetail
	public BoardVO getDetail(BoardVO boardVO) throws Exception;

	// getlist
	public List<BoardVO> getList(Pager pager) throws Exception;

	// setadd
	public int setAdd(BoardVO boardVO) throws Exception;

	// setUpdate
	public int setUpdate(BoardVO boardVO) throws Exception;

	// setdelete
	public int setDelete(BoardVO boardVO) throws Exception;
}
