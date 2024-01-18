package com.study.www.service;

import java.util.List;

import com.study.www.domain.BoardDTO;
import com.study.www.domain.BoardVO;
import com.study.www.domain.PagingVO;

public interface BoardService {

	int insert(BoardDTO boardDTO);

	List<BoardVO> getList(PagingVO pgvo);

	int getTotalCount(PagingVO pgvo);

	BoardDTO getDetail(long bno);

	

	void remove(BoardVO bvo);

	int removeFile(String uuid);

	int modify(BoardDTO boardDTO);

}
