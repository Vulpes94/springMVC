package com.java.member.controller;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.java.member.service.MemberService;

public class MemberController extends MultiActionController{
	private MemberService memberService;

	public MemberController() { }
	
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
}
