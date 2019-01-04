package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.board.domain.BoardVO;
import com.board.domain.UserVO;

public interface BoardMapper {

	public void boardInsert(BoardVO board) throws Exception;

	public List<BoardVO> boardList() throws Exception;

	public BoardVO boardView(int bno) throws Exception;

	public void hitPlus(int bno) throws Exception;

	public void boardUpdate(BoardVO vo) throws Exception;

	public void boardDelete(int bno) throws Exception;
	
	public void userLogin(UserVO vo) throws Exception;
	
}
