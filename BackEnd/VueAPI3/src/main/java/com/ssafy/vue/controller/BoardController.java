package com.ssafy.vue.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.ssafy.vue.model.BoardParameterDto;
import com.ssafy.vue.model.CommentDto;
import com.ssafy.vue.model.SubCommentDto;
import com.ssafy.vue.model.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//http://localhost:9999/vue/swagger-ui.html
//@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/board")
@Api("커뮤니티 컨트롤러  API V1")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private BoardService boardService;

	@ApiOperation(value = "커뮤니티 글작성", notes = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeArticle(@RequestBody @ApiParam(value = "게시글 정보.", required = true) BoardDto boardDto) throws Exception {
		logger.info("writeArticle - 호출");
		if (boardService.writeArticle(boardDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "커뮤니티 글목록", notes = "모든 게시글의 정보를 반환한다.", response = Map.class)
    @GetMapping
    public ResponseEntity<Map<String, Object>> listArticle(@ApiParam(value = "게시글을 얻기위한 부가정보.", required = true) BoardParameterDto boardParameterDto, HttpServletRequest request) throws Exception {
        logger.info("listArticle - 호출");
        Map<String, Object> map = new HashMap<>();
        map.put("total", boardService.makePageNavigation(boardParameterDto).getTotalCount());
        map.put("list", boardService.listArticle(boardParameterDto));

        System.out.println(request.getRequestURL());
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }
	
	@ApiOperation(value = "이전 게시글 번호", notes = "이전 게시글의 번호를 반환한다.", response = Integer.class)
    @GetMapping("/prev/{articleno}")
    public ResponseEntity<Integer> getPrev(@PathVariable("articleno") @ApiParam(value = "현재 게시글의 번호", required = true) int articleno) throws Exception {
        logger.info("getPrev - 호출");
        
        return new ResponseEntity<Integer>(boardService.getPrev(articleno), HttpStatus.OK);
    }
	
	@ApiOperation(value = "다음 게시글 번호", notes = "다음 게시글의 번호를 반환한다.", response = Integer.class)
    @GetMapping("/next/{articleno}")
    public ResponseEntity<Integer> getNext(@PathVariable("articleno") @ApiParam(value = "현재 게시글의 번호", required = true) int articleno) throws Exception {
        logger.info("getPrev - 호출");
        
        return new ResponseEntity<Integer>(boardService.getNext(articleno), HttpStatus.OK);
    }
	
	@ApiOperation(value = "커뮤니티 글보기", notes = "글번호에 해당하는 게시글의 정보를 반환한다.", response = BoardDto.class)
	@GetMapping("/{articleno}")
	public ResponseEntity<BoardDto> getArticle(@PathVariable("articleno") @ApiParam(value = "얻어올 글의 글번호.", required = true) int articleno) throws Exception {
		logger.info("getArticle - 호출 : " + articleno);
		boardService.updateHit(articleno);
		return new ResponseEntity<BoardDto>(boardService.getArticle(articleno), HttpStatus.OK);
	}
	
	@ApiOperation(value = "커뮤니티 글수정", notes = "새로운 게시글 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> modifyArticle(@RequestBody @ApiParam(value = "수정할 글정보.", required = true) BoardDto boardDto) throws Exception {
		logger.info("modifyArticle - 호출");
		
		if (boardService.modifyArticle(boardDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "커뮤니티 글삭제", notes = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{articleno}")
	public ResponseEntity<String> deleteArticle(@PathVariable("articleno") @ApiParam(value = "살제할 글의 글번호.", required = true) int articleno) throws Exception {
		logger.info("deleteArticle - 호출");
		if (boardService.deleteArticle(articleno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "커뮤니티 댓글작성", notes = "새로운 댓글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/comment")
	public ResponseEntity<String> writeComment(@RequestBody @ApiParam(value = "댓글 정보.", required = true) CommentDto commentDto) throws Exception {
		logger.info("writeComment - 호출");
		if (boardService.writeComment(commentDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "댓글 목록", notes = "모든 댓글 정보를 반환한다.", response = List.class)
    @GetMapping("/comment/{articleno}")
    public ResponseEntity<List<CommentDto>> listComment(@PathVariable("articleno") @ApiParam(value = "게시글의 번호", required = true) int articleno) throws Exception {
        logger.info("listComment - 호출");
        
        return new ResponseEntity<List<CommentDto>>(boardService.listComment(articleno), HttpStatus.OK);
    }
	
	@ApiOperation(value = "커뮤니티 댓글삭제", notes = "번호에 해당하는 댓글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/comment/{commentid}")
	public ResponseEntity<String> deleteComment(@PathVariable("commentid") @ApiParam(value = "살제할 댓글의 글번호.", required = true) int commentid) throws Exception {
		logger.info("deleteComment - 호출");
		if (boardService.deleteComment(commentid)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "댓글 하위 댓글 작성", notes = "새로운 하위 댓글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/comment/sub")
	public ResponseEntity<String> writeSubComment(@RequestBody @ApiParam(value = "하위 댓글 정보.", required = true) SubCommentDto subCommentDto) throws Exception {
		logger.info("writeSubComment - 호출");
		
		if (boardService.writeSubComment(subCommentDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "하위 댓글 목록", notes = "모든 하위 댓글 정보를 반환한다.", response = List.class)
    @GetMapping("/comment/sub/{commentid}")
    public ResponseEntity<List<SubCommentDto>> listSubComment(@PathVariable("commentid") @ApiParam(value = "상위 댓글의 번호", required = true) int commentid) throws Exception {
        logger.info("listSubComment - 호출");
        
        return new ResponseEntity<List<SubCommentDto>>(boardService.listSubComment(commentid), HttpStatus.OK);
    }
	
	
	@ApiOperation(value = "하위 댓글 삭제", notes = "번호에 해당하는 하위 댓글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/comment/sub/{sub_comment_id}")
	public ResponseEntity<String> deleteSubComment(@PathVariable("sub_comment_id") @ApiParam(value = "살제할 하위 댓글의 번호.", required = true) int sub_comment_id) throws Exception {
		logger.info("deleteSubComment - 호출");
		
		if (boardService.deleteSubComment(sub_comment_id)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "하위 댓글 개수", notes = "해당 게시글에 달린 하위 댓글 수를 반환한다.", response = Integer.class)
    @GetMapping("/comment/count/{articleno}")
    public ResponseEntity<Integer> getSubCommentCount(@PathVariable("articleno") @ApiParam(value = "게시글의 번호", required = true) int articleno) throws Exception {
        logger.info("getSubCommentCount - 호출");
        
        return new ResponseEntity<Integer>(boardService.getSubCommentCount(articleno), HttpStatus.OK);
    }
}
