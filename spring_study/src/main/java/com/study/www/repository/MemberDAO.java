package com.study.www.repository;

import java.util.List;

import com.study.www.security.AuthVO;
import com.study.www.security.MemberVO;

public interface MemberDAO {

	MemberVO selectEmail(String username);

	List<AuthVO> selectAuths(String username);

	int updateLastLogin(String authEmail);

	int insert(MemberVO mvo);

	int insertAuthInit(String email);

	List<MemberVO> selectAllList();

	int modifyPwdEmpty(MemberVO mvo);

	int modify(MemberVO mvo);

	int removeAuth(String email);

	int remove(String email);


}
