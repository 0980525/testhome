package com.study.www.repository;

import java.util.List;

import com.study.www.domain.CommentVO;

public interface CommentDAO {

	int insert(CommentVO cvo);

	List<CommentVO> getList(long bno);

}
