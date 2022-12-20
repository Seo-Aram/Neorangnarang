package com.app.rang.project.controller.board;

import com.app.rang.project.model.BoardWriteRequest;
import com.app.rang.project.service.BoardWriteService;
import com.app.rang.project.util.Util;
import com.app.rang.project.util.CategoryUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/board")
public class BoardRestController {
    @Autowired
    BoardWriteService boardWriteService;

    @GetMapping
    public ModelAndView writeItemViewPage(
            HttpServletRequest request
    ) {
        ModelAndView mav = new ModelAndView();
        mav.clear();
        mav.addObject("itemCategory", CategoryUtil.categoryNames);
        mav.setViewName("view/board/write");
        return mav;
    }

    @PostMapping
    public ResponseEntity<String> writeItem(
            HttpServletRequest request,
            @RequestBody BoardWriteRequest itemEntity
    ) {
        log.info(itemEntity);

        HttpHeaders httpHeaders = new HttpHeaders();

        if((itemEntity.getTitle() == null  || itemEntity.getTitle().isEmpty())
                || (itemEntity.getContent() == null  || itemEntity.getContent().isEmpty())
                || (itemEntity.getPrice() <= 0)) {
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.BAD_REQUEST);
        }

        log.info(itemEntity);
        int result = boardWriteService.writeBoard(itemEntity);

        return new ResponseEntity<>("/", httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, List<String>>> writeItem(
            MultipartHttpServletRequest multipartRequest,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        Map<String, List<String>> nameList = null;

        try {
            multipartRequest.setCharacterEncoding("utf-8");
            // 파일을 업로드한 후 반환된 파일 이름이 저장되는 fileList를 다시 map에 저장할 것이다
            nameList = boardWriteService.fileUpload(multipartRequest);
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error(e.getStackTrace());
            return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<>(nameList, new HttpHeaders(), HttpStatus.OK);
    }


}