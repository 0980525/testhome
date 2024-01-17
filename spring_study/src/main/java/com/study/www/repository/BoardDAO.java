package repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import domain.BoardVO;
import domain.PagingVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	long selectOneBno();

	List<BoardVO> getList(PagingVO pgvo);

	int getTotalCnt(PagingVO pgvo);

	BoardVO getDetail(long bno);

	int updateReadCnt(@Param("bno")long bno,@Param("count")int count);

}
