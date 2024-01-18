package com.study.www.service;

import java.util.List;

import com.study.www.domain.CommentVO;

public interface CommentService {

	int post(CommentVO cvo);

	List<CommentVO> getList(long bno);

}
