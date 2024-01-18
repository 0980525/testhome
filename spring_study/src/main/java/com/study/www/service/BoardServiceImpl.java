package com.study.www.service;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.www.domain.BoardDTO;
import com.study.www.domain.BoardVO;
import com.study.www.domain.FileVO;
import com.study.www.domain.PagingVO;
import com.study.www.repository.BoardDAO;
import com.study.www.repository.CommentDAO;
import com.study.www.repository.FileDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{

	private final BoardDAO bdao;
	private final FileDAO fdao;
	private final CommentDAO cdao;
	
	@Transactional
	@Override
	public int insert(BoardDTO bdto) {
		int isOk = bdao.insert(bdto.getBvo());
		if(bdto.getFlist()==null) {
			return isOk;
		}
		if(isOk > 0 && bdto.getFlist().size()>0) {
			long bno = bdao.selectOneBno();
			for(FileVO fvo:bdto.getFlist()) {
				fvo.setBno(bno);
				isOk *=fdao.insertFile(fvo);
			}
		}
		return isOk;
	}
	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return bdao.getList(pgvo);
	}
	@Override
	public int getTotalCount(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return bdao.getTotalCnt(pgvo);
	}
	@Transactional
	@Override
	public BoardDTO getDetail(long bno) {
		bdao.updateReadCnt(bno,1);
		BoardVO bvo = bdao.getDetail(bno);
		List<FileVO> flist = fdao.getFileList(bno);
		BoardDTO bdto = new BoardDTO(bvo,flist);
		return bdto;
	}
	@Transactional
	@Override
	public int modify(BoardDTO bdto) {
		int isOk = bdao.updateReadCnt(bdto.getBvo().getBno(),-2);
		if(bdto.getFlist() == null) {
			return isOk;
		}
		
		if(isOk > 0 && bdto.getFlist().size()>0) {
			for(FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bdto.getBvo().getBno());
				isOk += fdao.insertFile(fvo);
			}
		}
		return bdao.update(bdto.getBvo());
	}
	@Override
	public void remove(BoardVO bvo) {
		bdao.delete(bvo);
		
	}
	@Override
	public int removeFile(String uuid) {
		// TODO Auto-generated method stub
		return fdao.delete(uuid);
	}
	
}
