package com.study.www.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.www.domain.BoardDTO;
import com.study.www.domain.BoardVO;
import com.study.www.domain.FileVO;
import com.study.www.domain.PagingVO;
import com.study.www.handler.FileHandler;
import com.study.www.handler.PagingHandler;
import com.study.www.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/board/*")
@RequiredArgsConstructor
@Controller
public class BoardController {

	private final BoardService bsv;
	
	private final FileHandler fh;
	
	@GetMapping("/boardRegister")
	public void register() {}
	@PostMapping("/boardRegister")
	public String insert(BoardVO bvo,
			@RequestParam(name="files",required = false)MultipartFile[] files) {
		List<FileVO> flist = null;
		
		if(files[0].getSize()>0) {
			flist = fh.uploadFiles(files);
		}
		int isOk = bsv.insert(new BoardDTO(bvo,flist));
		
		return "index";
	}
	@GetMapping("/boardList")
	public void list(Model m,PagingVO pgvo) {
		log.info("000000pgvo000000",pgvo);
		List<BoardVO> list = bsv.getList(pgvo);
		int totalCount = bsv.getTotalCount(pgvo);
		PagingHandler ph = new PagingHandler(pgvo, totalCount);
		m.addAttribute("list",list);
		m.addAttribute("ph",ph);
	}
	@GetMapping({"/boardDetail", "/boardModify"})
	public void detail(Model m, @RequestParam("bno") long bno) {
		
		m.addAttribute("bdto",bsv.getDetail(bno));
	}
	@PostMapping("/boardModify")
	public String modify(BoardVO bvo,RedirectAttributes re,
			@RequestParam(name="files",required= false)MultipartFile[]files) {
		log.info(">>> modify >>>{}");
		List<FileVO> flist = null;
		
		if(files[0].getSize()>0) {
			flist = fh.uploadFiles(files);
		}
		int isOk = bsv.modify(new BoardDTO(bvo,flist));
		re.addAttribute("bvo",bvo.getBno());
		
	return "redirect:/board/boardDetail?bno="+bvo.getBno();
	}
	
	@GetMapping("/remove")
	public String remove (BoardVO bvo) {
		bsv.remove(bvo);
		bsv.removeFile(bvo.getBno());
		return "redirect:/board/boardList";
	}
	@DeleteMapping(value="/file/{uuid}",produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> removeFile(@PathVariable("uuid")String uuid){
		int isOk = bsv.modRemoveFile(uuid);
		return isOk >0? new ResponseEntity<String>("1",HttpStatus.OK):
			new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
