package com.ssafy.vue.model.service;

import com.ssafy.vue.model.MemberDto;

public interface MemberService {

	public MemberDto login(MemberDto memberDto) throws Exception;
	public MemberDto userInfo(String userid) throws Exception;
	boolean registerMember(MemberDto memberDto) throws Exception;
	boolean modifyMember(MemberDto memberDto) throws Exception;
	boolean deleteMember(String userid)throws Exception;
	MemberDto findPwd(String id, String name)throws Exception;
	boolean resetPwd(MemberDto memberDto)throws Exception;
	
}
