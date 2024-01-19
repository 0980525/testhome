package com.study.www.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import com.study.www.service.MemberService;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler{

	@Getter
	@Setter
	private String authEmail;
	@Getter
	@Setter
	private String authUrl;
	
	private RedirectStrategy rdstg = new DefaultRedirectStrategy();
	private RequestCache reqCache = new HttpSessionRequestCache();
	
	@Inject
	private MemberService msv;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		setAuthEmail(authentication.getName());
		setAuthUrl("/board/boardList");
		
		boolean isOk = msv.updateLastLogin(getAuthEmail());
		
		HttpSession ses =  request.getSession();
		
		log.info("loginSuccess >> ses >>"+ses.toString());
		
		if(!isOk ||ses ==null) {
			return;
		}else {
			ses.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		}
		SavedRequest saveReq = reqCache.getRequest(request, response);
		rdstg.sendRedirect(request, response, (saveReq != null ? saveReq.getRedirectUrl():getAuthUrl()));

		
	}

}
