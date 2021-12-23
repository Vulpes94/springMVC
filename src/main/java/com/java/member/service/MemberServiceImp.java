package com.java.member.service;

import com.java.member.dao.MemberDao;

public class MemberServiceImp {
	private MemberDao memberDao;

	public MemberServiceImp() { }
	
	public MemberServiceImp(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
}
