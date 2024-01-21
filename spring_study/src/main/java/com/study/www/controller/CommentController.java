package com.study.www.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.www.domain.CommentVO;
import com.study.www.domain.PagingVO;
import com.study.www.handler.PagingHandler;
import com.study.www.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comment/*")
public class CommentController {

	private final CommentService csv;
	
	@PostMapping(value="/post",consumes = "application/json",produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> post(@RequestBody CommentVO cvo){
		int isOk = csv.post(cvo);
		return isOk>0? new ResponseEntity<String>("1",HttpStatus.OK):
			new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@GetMapping(value="/{bno}/{page}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PagingHandler> list(@PathVariable("bno")long bno,@PathVariable("page")int page){
		PagingVO pgvo = new PagingVO(page,5);
		PagingHandler ph= csv.getList(bno,pgvo);
		return new ResponseEntity<PagingHandler>(ph, HttpStatus.OK);
	}
	
	@PutMapping(value="/edit", consumes = "application/json", produces= MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> edit(@RequestBody CommentVO cvo) {
		log.info(">>> cvo >>> {}", cvo);
		
		int isOk = csv.modify(cvo);
		return isOk > 0 ? 
				new ResponseEntity<String>("1", HttpStatus.OK) :
					new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping(value="/delete/{cno}/{writer}", produces= MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> delete (@PathVariable("cno") long cno, @PathVariable("writer") String writer) {
		log.info(">>> cno / writer >>> {}" +cno +" / " + writer);
		int isOk = csv.remove(cno);
		return isOk > 0 ? 
				new ResponseEntity<String>("1", HttpStatus.OK) :
					new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
