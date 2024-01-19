package com.study.www.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.www.domain.CommentVO;
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
	public List<CommentVO> getList(long bno) {
		log.info("<<<<<<get list bno>>>>>> <<<<<<>>>>>>{}",bno);
		return cdao.getList(bno);
	}
}
