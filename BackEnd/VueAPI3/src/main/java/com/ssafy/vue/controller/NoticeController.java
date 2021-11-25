package com.ssafy.vue.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.model.BoardDto;
import com.ssafy.vue.model.GalleryDto;
import com.ssafy.vue.model.NoticeDto;
import com.ssafy.vue.model.NoticeParameterDto;
import com.ssafy.vue.model.service.NoticeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/notice")
@Api("공지사항 컨트롤러  API V1")
public class NoticeController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private NoticeService noticeService;

	@ApiOperation(value = "공지사항 글작성", notes = "새로운 공지사항 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeNotice(@RequestBody @ApiParam(value = "공지사항 정보.", required = true) NoticeDto noticeDto) throws Exception {
		logger.info("writeNotice - 호출");
		
		if (noticeService.writeNotice(noticeDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "공지사항 글목록", notes = "모든 공지사항의 정보를 반환한다.", response = Map.class)
    @GetMapping
    public ResponseEntity<Map<String, Object>> listNotice(@ApiParam(value = "공지사항 얻기위한 부가정보.", required = true) NoticeParameterDto noticeParameterDto) throws Exception {
        logger.info("listNotice - 호출");
        
        Map<String, Object> map = new HashMap<>();
        map.put("total", noticeService.makePageNavigation(noticeParameterDto).getTotalCount());
        map.put("list", noticeService.listNotice(noticeParameterDto));

        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }
	
	@ApiOperation(value = "이전 공지사항 번호", notes = "이전 공지사항의 번호를 반환한다.", response = Integer.class)
    @GetMapping("/prev/{noticeno}")
    public ResponseEntity<Integer> getPrev(@PathVariable("noticeno") @ApiParam(value = "현재 공지사항의 번호", required = true) int noticeno) throws Exception {
        logger.info("getPrev(Notice) - 호출");
        
        return new ResponseEntity<Integer>(noticeService.getPrev(noticeno), HttpStatus.OK);
    }
	
	@ApiOperation(value = "다음 공지사항 번호", notes = "다음 공지사항의 번호를 반환한다.", response = Integer.class)
    @GetMapping("/next/{noticeno}")
    public ResponseEntity<Integer> getNext(@PathVariable("noticeno") @ApiParam(value = "현재 공지사항의 번호", required = true) int noticeno) throws Exception {
        logger.info("getNext(Notice) - 호출");
        
        return new ResponseEntity<Integer>(noticeService.getNext(noticeno), HttpStatus.OK);
    }
	
	@ApiOperation(value = "공지사항 글보기", notes = "번호에 해당하는 공지사항의 정보를 반환한다.", response = BoardDto.class)
	@GetMapping("/{noticeno}")
	public ResponseEntity<NoticeDto> getNotice(@PathVariable("noticeno") @ApiParam(value = "얻어올 공지사항의 번호.", required = true) int noticeno) throws Exception {
		logger.info("getNotice - 호출 : " + noticeno);
		
		noticeService.updateHit(noticeno);
		return new ResponseEntity<NoticeDto>(noticeService.getNotice(noticeno), HttpStatus.OK);
	}
	
	@ApiOperation(value = "공지사항 글수정", notes = "새로운 공지사항 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> modifyNotice(@RequestBody @ApiParam(value = "수정할 글정보.", required = true) NoticeDto noticeDto) throws Exception {
		logger.info("modifyNotice - 호출");
		
		if (noticeService.modifyNotice(noticeDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "공지사항 글삭제", notes = "번호에 해당하는 공지사항 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{noticeno}")
	public ResponseEntity<String> deleteNotice(@PathVariable("noticeno") @ApiParam(value = "살제할 공지사항의 번호.", required = true) int noticeno) throws Exception {
		logger.info("deleteNotice - 호출");
		
		if (noticeService.deleteNotice(noticeno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "공지글 사진 가져오기", notes = "번호에 해당하는 공지글의 사진리스트를 반환한다.", response = List.class)
	@GetMapping("/gallery/{noticeno}")
	public ResponseEntity<List<GalleryDto>> getFile(@PathVariable("noticeno") @ApiParam(value = "공지글의 번호.", required = true) int noticeno) throws Exception {
		logger.info("getFile - 호출 : " + noticeno);
		
		return new ResponseEntity<List<GalleryDto>>(noticeService.getFile(noticeno), HttpStatus.OK);
	}
}
