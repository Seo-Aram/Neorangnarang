package com.app.rang.project.controller;

import com.app.rang.project.entity.Board;
import com.app.rang.project.entity.Comment;
import com.app.rang.project.service.WrittenByUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Log4j2
@RestController
@RequestMapping("/user")
public class WrittenByUserController {

    @Autowired
    private WrittenByUserService byUserService;


    @GetMapping("/myboard")
    public ModelAndView getUserBoard(){

        ModelAndView mav = new ModelAndView();
        mav.clear();
        long useridx = 0;
        mav.addObject("myBoardList", byUserService.printMyBoard(useridx));
        mav.setViewName("view/user/myboard");

        return mav;

    }

    @GetMapping("/mycomment")
    public ModelAndView getUserComment(){

        ModelAndView mav = new ModelAndView();
        mav.clear();
        long useridx = 0;
        mav.addObject("myCommentList", byUserService.printMyComment(useridx));
        mav.setViewName("view/user/mycomment");

        return mav;

    }

}
