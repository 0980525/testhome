package service;

import java.util.List;

import domain.BoardDTO;
import domain.BoardVO;
import domain.PagingVO;

public interface BoardService {

	int insert(BoardDTO boardDTO);

	List<BoardVO> getList(PagingVO pgvo);

	int getTotalCount(PagingVO pgvo);

	BoardDTO getDetail(long bno);

	int update(BoardDTO boardDTO);

	void remove(BoardVO bvo);

	int removeFile(String uuid);

}
