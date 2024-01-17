package service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.BoardDTO;
import domain.BoardVO;
import domain.FileVO;
import domain.PagingVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import repository.BoardDAO;
import repository.CommentDAO;
import repository.FileDAO;

@Service
@Slf4j
@RequiredArgsConstructor
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
	@Override
	public int update(BoardDTO boardDTO) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void remove(BoardVO bvo) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int removeFile(String uuid) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
