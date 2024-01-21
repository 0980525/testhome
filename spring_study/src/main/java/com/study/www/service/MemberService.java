package com.study.www.service;

import java.util.List;

import com.study.www.security.MemberVO;

public interface MemberService {

	boolean updateLastLogin(String authEmail);

	int register(MemberVO mvo);

	List<MemberVO> getList();

	MemberVO detail(String email);

	int modifyPwdEmpty(MemberVO mvo);

	int modify(MemberVO mvo);

	int remove(String email);

}
