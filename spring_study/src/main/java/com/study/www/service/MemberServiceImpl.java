package com.study.www.service;

import java.util.List;

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

	@Transactional
	@Override
	public List<MemberVO> getList() {
		List<MemberVO> list = mdao.selectAllList();
		for(MemberVO mvo : list) {
			mvo.setAuthList(mdao.selectAuths(mvo.getEmail()));
		}
		return list;
	}

	/*
	 * @Override public MemberVO detail(String email) {
	 * 
	 * 
	 * return null; }
	 */

	@Override
	public int modifyPwdEmpty(MemberVO mvo) {
		// TODO Auto-generated method stub
		return mdao.modifyPwdEmpty(mvo);
	}

	@Override
	public int modify(MemberVO mvo) {
		// TODO Auto-generated method stub
		return mdao.modify(mvo);
	}

	@Transactional
	@Override
	public int remove(String email) {
		int isOk = mdao.removeAuth(email);
		return mdao.remove(email);
	}


}
