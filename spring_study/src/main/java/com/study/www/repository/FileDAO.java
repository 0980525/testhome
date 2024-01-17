package com.study.www.repository;

import java.util.List;

import com.study.www.domain.FileVO;

public interface FileDAO {

	int insertFile(FileVO fvo);

	List<FileVO> getFileList(long bno);

}
