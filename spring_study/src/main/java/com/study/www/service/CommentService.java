package com.study.www.service;


import com.study.www.domain.CommentVO;
import com.study.www.domain.PagingVO;
import com.study.www.handler.PagingHandler;

public interface CommentService {

	int post(CommentVO cvo);

	PagingHandler getList(long bno, PagingVO pgvo);

	int modify(CommentVO cvo);

	int remove(long cno);

}
