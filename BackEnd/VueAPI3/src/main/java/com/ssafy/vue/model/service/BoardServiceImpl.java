package com.ssafy.vue.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.util.PageNavigation;
import com.ssafy.vue.model.BoardDto;
import com.ssafy.vue.model.BoardParameterDto;
import com.ssafy.vue.model.CommentDto;
import com.ssafy.vue.model.CommentPlusDto;
import com.ssafy.vue.model.SubCommentDto;
import com.ssafy.vue.model.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public boolean writeArticle(BoardDto boardDto) throws Exception {
		if(boardDto.getSubject() == null || boardDto.getContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(BoardMapper.class).writeArticle(boardDto) == 1;
	}

	@Override
	public List<BoardDto> listArticle(BoardParameterDto boardParameterDto) throws Exception {
		int start = boardParameterDto.getPg() == 0 ? 0 : (boardParameterDto.getPg() - 1) * boardParameterDto.getSpp();
		boardParameterDto.setStart(start);
		return sqlSession.getMapper(BoardMapper.class).listArticle(boardParameterDto);
	}

	@Override
	public PageNavigation makePageNavigation(BoardParameterDto boardParameterDto) throws Exception {
		int naviSize = 5;
		PageNavigation pageNavigation = new PageNavigation();
		pageNavigation.setCurrentPage(boardParameterDto.getPg());
		pageNavigation.setNaviSize(naviSize);
		int totalCount = sqlSession.getMapper(BoardMapper.class).getTotalCount(boardParameterDto);//총글갯수  269
		pageNavigation.setTotalCount(totalCount);  
		int totalPageCount = (totalCount - 1) / boardParameterDto.getSpp() + 1;//27
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = boardParameterDto.getPg() <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < boardParameterDto.getPg();
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}

	@Override
	public BoardDto getArticle(int articleno) throws Exception {
		return sqlSession.getMapper(BoardMapper.class).getArticle(articleno);
	}
	
	@Override
	public int getPrev(int articleno) throws Exception {
		return sqlSession.getMapper(BoardMapper.class).getPrev(articleno);
	}
	
	@Override
	public int getNext(int articleno) throws Exception {
		return sqlSession.getMapper(BoardMapper.class).getNext(articleno);
	}
	
	@Override
	public void updateHit(int articleno) throws Exception {
		sqlSession.getMapper(BoardMapper.class).updateHit(articleno);
	}

	@Override
	@Transactional
	public boolean modifyArticle(BoardDto boardDto) throws Exception {
		return sqlSession.getMapper(BoardMapper.class).modifyArticle(boardDto) == 1;
	}

	@Override
	@Transactional
	public boolean deleteArticle(int articleno) throws Exception {
		return sqlSession.getMapper(BoardMapper.class).deleteArticle(articleno) == 1;
	}

	@Override
	public boolean writeComment(CommentDto commentDto) throws Exception {
		if(commentDto.getUserid() == null || commentDto.getContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(BoardMapper.class).writeComment(commentDto) == 1;
	}

	@Override
	public List<CommentDto> listComment(int articleno) throws Exception {
		return sqlSession.getMapper(BoardMapper.class).listComment(articleno);
	}
	
	@Override
	public boolean plusComment(CommentPlusDto commentPlusDto) throws Exception {
		return sqlSession.getMapper(BoardMapper.class).plusComment(commentPlusDto) == 1;
	}
	
	@Override
	@Transactional
	public boolean modifyComment(CommentDto commentDto) throws Exception {
		return sqlSession.getMapper(BoardMapper.class).modifyComment(commentDto) == 1;
	}
	
	@Override
	@Transactional
	public boolean deleteComment(int commentid) throws Exception {
		sqlSession.getMapper(BoardMapper.class).deleteComment(commentid);
		return sqlSession.getMapper(BoardMapper.class).deleteComment(commentid) == 1;
	}
	
	@Override
	public boolean writeSubComment(SubCommentDto subCommentDto) throws Exception {
		if(subCommentDto.getUserid() == null || subCommentDto.getArticleno() == 0 || subCommentDto.getContent() == null || subCommentDto.getCommentid() == 0) {
			throw new Exception();
		}
		return sqlSession.getMapper(BoardMapper.class).writeSubComment(subCommentDto) == 1;
	}

	@Override
	public List<SubCommentDto> listSubComment(int commentid) throws Exception {
		return sqlSession.getMapper(BoardMapper.class).listSubComment(commentid);
	}
	
	@Override
	@Transactional
	public boolean deleteSubComment(int sub_comment_id) throws Exception {
		sqlSession.getMapper(BoardMapper.class).deleteSubComment(sub_comment_id);
		return sqlSession.getMapper(BoardMapper.class).deleteSubComment(sub_comment_id) == 1;
	}
	
	@Override
	public int getSubCommentCount(int articleno) throws Exception {
		return sqlSession.getMapper(BoardMapper.class).getSubCommentCount(articleno);
	}
}