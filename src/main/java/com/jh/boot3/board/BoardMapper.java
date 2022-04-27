package com.jh.boot3.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jh.boot3.util.Pager;

@Mapper
public interface BoardMapper {

	// getFileList
	public List<BoardFilesVO> getFileList(BoardVO boardVO) throws Exception;

	// getFileDetail
	public BoardFilesVO getFileDetail(BoardFilesVO boardFilesVO) throws Exception;

	// setFileDelete delete
	public int setFileDelete(BoardFilesVO boardFilesVO) throws Exception;

	// setFileAdd insert
	// Mapper method attempted to return null from a method with a primitive return type (int).
	// 라는 오류가 떠서 구글링해보니 Integer로 바꾸래서 바꿨더니 잘 된다.
	public int setFileAdd(BoardFilesVO boardFilesVO) throws Exception;

	// getdetail
	public BoardVO getDetail(BoardVO boardVO) throws Exception;

	// GETTOTAL 4/19
	public Long getTotalCount(Pager pager) throws Exception;

	// getlist
	public List<BoardVO> getList(Pager pager) throws Exception;

	// setadd
	public int setAdd(BoardVO boardVO) throws Exception;

	// setUpdate
	public int setUpdate(BoardVO boardVO) throws Exception;

	// setdelete
	public int setDelete(BoardVO boardVO) throws Exception;
}
