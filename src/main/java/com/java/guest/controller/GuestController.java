package com.java.guest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.java.aop.LogAspect;
import com.java.guest.dto.GuestDto;
import com.java.guest.service.GuestService;

public class GuestController extends MultiActionController{
	private GuestService guestService;
	
	public GuestController() { }
	
	public GuestController(GuestService guestService) {
		this.guestService = guestService;
	}
	
	public void setGuestService(GuestService guestService) {
		this.guestService = guestService;
	}

	public ModelAndView guestWrite(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		guestService.guestWrite(mav);
		
		return mav;
	}
	
	public ModelAndView guestWriteOk(HttpServletRequest request, HttpServletResponse response, GuestDto guestDto) {
		LogAspect.logger.info(LogAspect.LogMsg + guestDto.toString());
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("guestDto", guestDto);
		
		guestService.guestWriteOk(mav);
		
		return mav;
	}
	
	public ModelAndView guestDelete(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		
		guestService.guestDelete(mav);
		return mav;
	}
	
	public ModelAndView guestUpdate(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		
		guestService.guestUpdate(mav);
		return mav;
	}
	
	public  ModelAndView guestUpdateOk(HttpServletRequest request, HttpServletResponse response,GuestDto guestDto) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("guestDto",guestDto);
		
		guestService.guestUpdateOk(mav);
		return mav;
	}
}























