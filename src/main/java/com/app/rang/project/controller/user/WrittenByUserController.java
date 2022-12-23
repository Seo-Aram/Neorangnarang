package com.app.rang.project.controller.user;

import com.app.rang.project.entity.Board;
import com.app.rang.project.model.AuthUserDTO;
import com.app.rang.project.service.WrittenByUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ModelAndView getUserBoard(@AuthenticationPrincipal AuthUserDTO userDTO){

        ModelAndView mav = new ModelAndView();
        mav.clear();
        mav.addObject("myBoardList", byUserService.printMyBoard(userDTO));

        log.info("useridx >>> " + userDTO.getUseridx());

        mav.setViewName("view/user/myboard");

        return mav;

    }

    @GetMapping("/mycomment")
    public ModelAndView getUserComment(@AuthenticationPrincipal AuthUserDTO userDTO){

        ModelAndView mav = new ModelAndView();
        mav.clear();
        mav.addObject("myCommentList", byUserService.printMyComment(userDTO));

        log.info("useridx >>> " + userDTO.getUseridx());

        mav.setViewName("view/user/mycomment");

        return mav;

    }

}
