package com.ssafy.vue.model.service;

import java.util.List;

import com.ssafy.util.PageNavigation;
import com.ssafy.vue.model.BoardDto;
import com.ssafy.vue.model.BoardParameterDto;
import com.ssafy.vue.model.CommentDto;
import com.ssafy.vue.model.CommentPlusDto;

public interface BoardService {
	public boolean writeArticle(BoardDto boardDto) throws Exception;
	public List<BoardDto> listArticle(BoardParameterDto boardParameterDto) throws Exception;
	public PageNavigation makePageNavigation(BoardParameterDto boardParameterDto) throws Exception;
	
	public BoardDto getArticle(int articleno) throws Exception;
	public void updateHit(int articleno) throws Exception;
	public boolean modifyArticle(BoardDto boardDto) throws Exception;
	public boolean deleteArticle(int articleno) throws Exception;
	
	public boolean writeComment(CommentDto commentDto) throws Exception;
	public List<CommentDto> listComment(int articleno) throws Exception;
	public boolean plusComment(CommentPlusDto commentPlusDto) throws Exception;
}
