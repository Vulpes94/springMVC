package com.java.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class TestController extends MultiActionController{

	public void test(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("TestController test-----");
	}
	
	
}
