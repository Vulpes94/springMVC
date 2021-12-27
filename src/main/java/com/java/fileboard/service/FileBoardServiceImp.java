package com.java.fileboard.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.java.aop.LogAspect;
import com.java.fileboard.dao.FileBoardDao;
import com.java.fileboard.dto.FileBoardDto;

public class FileBoardServiceImp implements FileBoardService {
  private FileBoardDao fileBoardDao;

  public FileBoardServiceImp() {}

  public FileBoardServiceImp(FileBoardDao fileBoardDao) {
    this.fileBoardDao = fileBoardDao;
  }

  public void setFileBoardDao(FileBoardDao fileBoardDao) {
    this.fileBoardDao = fileBoardDao;
  }

  @Override
  public void fileBoardWrite(ModelAndView mav) {
    Map<String, Object> map = mav.getModelMap();
    HttpServletRequest request = (HttpServletRequest) map.get("request");

    // 부모글 -ROOT
    int boardNumber = 0; // 글번호 : ROOT 항상 boardNumber 0, 답글인 경우 부모의 boardNumber
    int groupNumber = 1; // 그룹번호
    int sequenceNumber = 0; // 글순서
    int sequenceLevel = 0; // 글레벨

    if (request.getParameter("boardNumber") != null) { // 답글인 경우
      boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
      groupNumber = Integer.parseInt(request.getParameter("groupNumber"));
      sequenceNumber = Integer.parseInt(request.getParameter("sequenceNumber"));
      sequenceLevel = Integer.parseInt(request.getParameter("sequenceLevel"));
    }

    mav.addObject("boardNumber", boardNumber);
    mav.addObject("groupNumber", groupNumber);
    mav.addObject("sequenceNumber", sequenceNumber);
    mav.addObject("sequenceLevel", sequenceLevel);

    mav.setViewName("fileboard/write");
  }

  @Override
  public void fileBoardWriteOk(ModelAndView mav) {
    Map<String, Object> map = mav.getModelMap();
    FileBoardDto fileBoardDto = (FileBoardDto) map.get("fileBoardDto");
    MultipartHttpServletRequest request = (MultipartHttpServletRequest) map.get("request");

    fileBoardWriterNumber(fileBoardDto);

    MultipartFile upFile = request.getFile("file");
    if (upFile.getSize() != 0) {
      String fileName = Long.toString(System.currentTimeMillis()) + "_" + upFile.getOriginalFilename();
      long fileSize = upFile.getSize();
      LogAspect.logger.info(LogAspect.LogMsg + fileName + "," + fileSize);

      File path = new File("D:\\pds\\");
      path.mkdir();

      if (path.exists() && path.isDirectory()) {
        File file = new File(path, fileName);
        try {
          upFile.transferTo(file);

          fileBoardDto.setPath(file.getAbsolutePath());
          fileBoardDto.setFileName(fileName);
          fileBoardDto.setFileSize(fileSize);
        } catch (Exception e) {
          e.printStackTrace();
        }

      }
    }

    LogAspect.logger.info(LogAspect.LogMsg + fileBoardDto.toString());
    int check = fileBoardDao.fileBoardWriteNumber(fileBoardDto);
    LogAspect.logger.info(LogAspect.LogMsg + check);

    mav.addObject("check", check);
    mav.setViewName("fileboard/writeOk");
  }

  public void fileBoardWriterNumber(FileBoardDto fileBoardDto) {
    int boardNumber = fileBoardDto.getBoardNumber();
    int groupNumber = fileBoardDto.getGroupNumber();
    int sequenceNumber = fileBoardDto.getSequenceNumber();
    int sequenceLevel = fileBoardDto.getSequenceLevel();

    if (boardNumber == 0) { // ROOT(부모글) : 그룹번호 작업
      int max = fileBoardDao.fileBoardGroupNumberMax();

      if (max != 0) {
        max = max + 1;
        fileBoardDto.setGroupNumber(max);
      }
    } else { // 자식글 : 글순서, 글레벨 작업
      HashMap<String, Integer> hMap = new HashMap<String, Integer>();
      hMap.put("groupNumber", groupNumber);
      hMap.put("sequenceNumber", sequenceNumber);

      int check = fileBoardDao.fileBoardWriteNumber(hMap);
      LogAspect.logger.info(LogAspect.LogMsg + check);

      sequenceNumber = sequenceNumber + 1;
      sequenceLevel = sequenceLevel + 1;

      fileBoardDto.setSequenceNumber(sequenceNumber);
      fileBoardDto.setSequenceLevel(sequenceLevel);
    }
  }

  public void fileBoardList(ModelAndView mav) {
    Map<String, Object> map = mav.getModelMap();
    HttpServletRequest request = (HttpServletRequest) map.get("request");

    String pageNumber = request.getParameter("pageNumber");
    if (pageNumber == null)
      pageNumber = "1";

    int currentPage = Integer.parseInt(pageNumber);
    LogAspect.logger.info(LogAspect.LogMsg + currentPage);

    // 한 페이지 당 게시물 1page 10개 / start 1, end 10
    int boardSize = 10;
    int startRow = (currentPage - 1) * boardSize + 1;
    int endRow = currentPage * boardSize;

    int count = fileBoardDao.getCount();
    LogAspect.logger.info(LogAspect.LogMsg + count);

    List<FileBoardDto> fileBoardList = null;
    if (count > 0) {
      fileBoardList = fileBoardDao.fileBoardList(startRow, endRow);
      LogAspect.logger.info(LogAspect.LogMsg + fileBoardList.size());
    }

    mav.addObject("boardSize", boardSize); // 한페이지당 게시물 수
    mav.addObject("currentPage", currentPage); // 요청페이지
    mav.addObject("boardList", fileBoardList); // 게시물 리스트
    mav.addObject("count", count); // 전체 게시물 수

    mav.setViewName("fileboard/list");
  }

  @Override
  public void fileBoardRead(ModelAndView mav) {
    Map<String, Object> map = mav.getModelMap();
    HttpServletRequest request = (HttpServletRequest) map.get("request");

    int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
    int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
    LogAspect.logger.info(LogAspect.LogMsg + boardNumber + "," + pageNumber);

    FileBoardDto fileBoardDto = fileBoardDao.fileRead(boardNumber);
    LogAspect.logger.info(LogAspect.LogMsg + fileBoardDto.toString());
    
    if (fileBoardDto.getFileSize() != 0) {
      int index = fileBoardDto.getFileName().indexOf("_") + 1;
      fileBoardDto.setFileName(fileBoardDto.getFileName().substring(index));
    }
    
    mav.addObject("boardDto", fileBoardDto);
    mav.addObject("pageNumber", pageNumber);

    mav.setViewName("fileboard/read");
  }
  
  

}
