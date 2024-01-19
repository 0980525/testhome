package com.study.www.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.www.repository.MemberDAO;
import com.study.www.security.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{

	private final MemberDAO mdao;
	
	@Transactional
	@Override
	public int register(MemberVO mvo) {
		int isOk =  mdao.insert(mvo);
		return mdao.insertAuthInit(mvo.getEmail());
	}
	
	@Override
	public boolean updateLastLogin(String authEmail) {
		log.info(">>>>>authEmail<<<<< {}",authEmail);
		return mdao.updateLastLogin(authEmail)>0?true:false;
	}


}
