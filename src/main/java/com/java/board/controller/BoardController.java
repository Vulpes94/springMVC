package com.java.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.java.board.dto.BoardDto;
import com.java.board.service.BoardService;

public class BoardController extends MultiActionController {
	private BoardService boardService;

	public BoardController() {
	}

	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	public ModelAndView boardWrite(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);

		boardService.boardWrite(mav);
		return mav;
	}

	public ModelAndView boardWriteOk(HttpServletRequest request, HttpServletResponse response, BoardDto boardDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardDto", boardDto);

		boardService.boardWriteOk(mav);

		return mav;
	}

	public ModelAndView boardList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);

		boardService.boardList(mav);

		return mav;
	}

	public ModelAndView boardRead(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);

		boardService.boardRead(mav);
		return mav;
	}

	public ModelAndView boardDelete(HttpServletRequest request, HttpServletResponse response) {
		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));

		ModelAndView mav = new ModelAndView();
		mav.addObject("boardNumber", boardNumber);
		mav.addObject("pageNumber", pageNumber);

		mav.setViewName("board/delete");
		return mav;
	}

	public ModelAndView boardDeleteOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);

		boardService.boardDeleteOk(mav);
		return mav;
	}

	public ModelAndView boardUpdate(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		
		boardService.boardUpdate(mav);
		return mav;
	}
	
	public ModelAndView boardUpdateOk(HttpServletRequest request, HttpServletResponse response,BoardDto boardDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("boardDto",boardDto);
		
		boardService.boardUpdateOk(mav);
		return mav;
	}
	
}
