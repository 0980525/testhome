package com.study.www;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.study.www.domain.BoardVO;
import com.study.www.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {com.study.www.config.RootConfig.class})
public class BoardTest {

	
	@Inject
	private BoardDAO bdao;
	
	@Test
	public void insertBoard() {
		for(int i =0;i<150;i++) {
			BoardVO bvo = new BoardVO();
			bvo.setTitle("title TEST"+i);
			bvo.setWriter("tester");
			bvo.setContent(i+"번째 TEST context입니다.");
			bdao.insert(bvo);
		}
	}

}
