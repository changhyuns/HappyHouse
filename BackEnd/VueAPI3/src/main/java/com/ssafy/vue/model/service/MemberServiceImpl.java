package com.ssafy.vue.model.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.vue.model.MemberDto;
import com.ssafy.vue.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		if(memberDto.getUserid() == null || memberDto.getUserpwd() == null)
			return null;
		return sqlSession.getMapper(MemberMapper.class).login(memberDto);
	}

	@Override
	public MemberDto userInfo(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).userInfo(userid);
	}
	
	@Override
	public boolean registerMember(MemberDto memberDto) throws Exception {
		if(memberDto.getUserid() == null || memberDto.getUsername() == null || memberDto.getUserpwd() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(MemberMapper.class).registerMember(memberDto) == 1;
	}

	@Override
	@Transactional
	public boolean modifyMember(MemberDto memberDto) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).modifyMember(memberDto) == 1;
	}
	

	@Override
	public boolean deleteMember(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).deleteMember(userid) == 1;
	}

	@Override
	public MemberDto findPwd(String id, String name) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).findPwd(id, name);
	}

	@Override
	public boolean resetPwd(MemberDto memberDto) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).resetPwd(memberDto) == 1;
	}

}
