package com.study.www.security;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.study.www.repository.MemberDAO;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class CustomAuthMemberService implements UserDetailsService{

	@Inject
	private MemberDAO mdao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO mvo = mdao.selectEmail(username);
		if(mvo == null) {
			throw new UsernameNotFoundException(username);
		}
		mvo.setAuthList(mdao.selectAuths(username));
		log.info("<< userDetail >> {} ",mvo);
		return new AuthMember(mvo);
	}

}
