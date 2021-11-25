package com.ssafy.vue.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.MemberDto;

@Mapper
public interface MemberMapper {

	public MemberDto login(MemberDto memberDto) throws SQLException;
	public MemberDto userInfo(String userid) throws SQLException;
	public int idCheck(String checkid) throws SQLException;
	public int registerMember(MemberDto memberDto) throws SQLException;
	public int modifyMember(MemberDto memberDto) throws SQLException;
	public int deleteMember(String userid) throws SQLException;
	
}
