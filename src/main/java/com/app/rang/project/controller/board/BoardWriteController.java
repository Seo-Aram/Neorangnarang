package com.app.rang.project.controller.board;

import com.app.rang.project.model.BoardWriteRequest;
import com.app.rang.project.service.BoardWriteService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Log4j2
@Controller
public class BoardWriteController {

    @Autowired
    private BoardWriteService boardWriteService;

    @GetMapping("/board/write")
    public void getWritePage(){

    }

    @PostMapping
    public String writeBoard(BoardWriteRequest boardWriteRequest){

        boardWriteService.writeBoard(boardWriteRequest);
        return "redirect:/board/list";

    }
}
