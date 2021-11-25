package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.BoardDto;
import com.ssafy.vue.model.BoardParameterDto;
import com.ssafy.vue.model.CommentDto;
import com.ssafy.vue.model.CommentPlusDto;
import com.ssafy.vue.model.SubCommentDto;

@Mapper
public interface BoardMapper {
	
	public int writeArticle(BoardDto boardDto) throws SQLException;
	public List<BoardDto> listArticle(BoardParameterDto boardParameterDto) throws SQLException;
	public int getTotalCount(BoardParameterDto boardParameterDto) throws SQLException;
	public BoardDto getArticle(int articleno) throws SQLException;
	public int getPrev(int articleno) throws SQLException;
	public int getNext(int articleno) throws SQLException;
	public void updateHit(int articleno) throws SQLException;
	public int modifyArticle(BoardDto boardDto) throws SQLException;
	public int deleteArticle(int articleno) throws SQLException;
	public int writeComment(CommentDto commentDto) throws SQLException;
	public List<CommentDto> listComment(int articleno) throws SQLException;
	public int plusComment(CommentPlusDto commentPlusDto) throws SQLException;
	public int modifyComment(CommentDto commentDto) throws SQLException;
	public int deleteComment(int commentid) throws SQLException;
	public int writeSubComment(SubCommentDto subCommentDto) throws SQLException;
	public List<SubCommentDto> listSubComment(int commentid) throws SQLException;
	public int deleteSubComment(int sub_comment_id) throws SQLException;
	public int getSubCommentCount(int articleno) throws SQLException;
}