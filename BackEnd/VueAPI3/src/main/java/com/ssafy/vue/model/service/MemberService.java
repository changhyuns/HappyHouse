package com.ssafy.vue.model.service;

import com.ssafy.vue.model.MemberDto;

public interface MemberService {

	public MemberDto login(MemberDto memberDto) throws Exception;
	public MemberDto userInfo(String userid) throws Exception;
	public boolean registerMember(MemberDto memberDto) throws Exception;
	public boolean updateMember(MemberDto memberDto) throws Exception;
	public boolean deleteMember(String userid) throws Exception;
	
}
