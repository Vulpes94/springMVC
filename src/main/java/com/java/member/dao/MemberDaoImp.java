package com.java.member.dao;

import org.mybatis.spring.SqlSessionTemplate;

public class MemberDaoImp implements MemberDao {
	private SqlSessionTemplate sqlSessionTemplate;

	public MemberDaoImp() { }
	
	public MemberDaoImp(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
}
