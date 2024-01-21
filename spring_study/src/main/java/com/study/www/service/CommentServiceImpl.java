package com.study.www.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.www.domain.BoardVO;
import com.study.www.domain.CommentVO;
import com.study.www.domain.PagingVO;
import com.study.www.handler.PagingHandler;
import com.study.www.repository.CommentDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

	private final CommentDAO cdao;

	@Override
	public int post(CommentVO cvo) {
		log.info("<<<<<<post cvo>>>>>> <<<<<<>>>>>>{}",cvo);
		return cdao.insert(cvo);
	}

	@Transactional
	@Override
	public PagingHandler getList(long bno, PagingVO pgvo) {
		List<CommentVO> list = cdao.getList(bno,pgvo);
		int cmtCount = cdao.totalCount(bno);
		PagingHandler ph = new PagingHandler(pgvo, cmtCount,list);
		log.info("<<<<<<get list bno>>>>>> <<<<<<>>>>>>{}",bno);
		return ph;
	}

	@Override
	public int modify(CommentVO cvo) {
		// TODO Auto-generated method stub
		return cdao.update(cvo);
	}

	@Override
	public int remove(long cno) {
		// TODO Auto-generated method stub
		return cdao.delete(cno);
	}
}
