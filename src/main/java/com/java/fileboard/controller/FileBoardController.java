package com.java.fileboard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import com.java.fileboard.dto.FileBoardDto;
import com.java.fileboard.service.FileBoardService;

public class FileBoardController extends MultiActionController {
  private FileBoardService fileBoardService;

  public FileBoardController() {}

  public FileBoardController(FileBoardService fileBoardService) {
    this.fileBoardService = fileBoardService;
  }

  public void setFileBoardService(FileBoardService fileBoardService) {
    this.fileBoardService = fileBoardService;
  }

  public ModelAndView fileBoardWrite(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView();
    mav.addObject("request", request);

    fileBoardService.fileBoardWrite(mav);
    return mav;
  }

  public ModelAndView fileBoardWriteOk(HttpServletRequest request, HttpServletResponse response, FileBoardDto fileBoardDto) {
    ModelAndView mav = new ModelAndView();
    mav.addObject("request", request);
    mav.addObject("fileBoardDto", fileBoardDto);

    fileBoardService.fileBoardWriteOk(mav);
    return mav;
  }

  public ModelAndView fileBoardList(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView();
    mav.addObject("request", request);

    fileBoardService.fileBoardList(mav);

    return mav;
  }

  public ModelAndView fileBoardRead(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView();
    mav.addObject("request", request);

    fileBoardService.fileBoardRead(mav);
    return mav;
  }

  public ModelAndView fileDownLoad(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView();
    mav.addObject("request", request);
    mav.addObject("response", response);

    fileBoardService.fileBoardDownload(mav);
    return mav;
  }

  public ModelAndView fileUpdate(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView();
    mav.addObject("request", request);

    fileBoardService.fileBoardUpdate(mav);
    return mav;
  }

  public ModelAndView fileUpdateOk(HttpServletRequest request, HttpServletResponse response, FileBoardDto fileBoardDto) {
    ModelAndView mav = new ModelAndView();
    mav.addObject("request", request);
    mav.addObject("fileBoardDto", fileBoardDto);

    fileBoardService.fileBoardUpdateOk(mav);
    return mav;
  }

  public ModelAndView fileDelete(HttpServletRequest request, HttpServletResponse response) {
    int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
    int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));

    ModelAndView mav = new ModelAndView();
    mav.addObject("boardNumber", boardNumber);
    mav.addObject("pageNumber", pageNumber);

    mav.setViewName("fileboard/delete");
    return mav;
  }

  public ModelAndView fileDeleteOk(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView();
    mav.addObject("request", request);

    fileBoardService.fileBoardDeleteOk(mav);
    return mav;
  }

}
