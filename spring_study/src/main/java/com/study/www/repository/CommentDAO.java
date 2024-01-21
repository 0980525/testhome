package com.study.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.study.www.domain.CommentVO;
import com.study.www.domain.PagingVO;

public interface CommentDAO {

	int insert(CommentVO cvo);

	List<CommentVO> getList(@Param("bno")long bno,@Param("pgvo")PagingVO pgvo);

	int totalCount(long bno);

	int update(CommentVO cvo);

	int delete(long cno);

}
