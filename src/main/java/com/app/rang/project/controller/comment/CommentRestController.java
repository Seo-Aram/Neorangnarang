package com.app.rang.project.controller.comment;

import com.app.rang.project.entity.Comment;
import com.app.rang.project.model.CommentListModel;
import com.app.rang.project.model.CommentWriteRequest;
import com.app.rang.project.service.comment.CommentListService;
import com.app.rang.project.service.comment.CommentWriteService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/comment")
public class CommentRestController {

    @Autowired
    private CommentListService commentListService;

    @Autowired
    private CommentWriteService commentWriteService;


    @GetMapping("/{boardidx}/{lastCommentIdx}")
    public ResponseEntity<List<CommentListModel>> selectList(
            @PathVariable("boardidx") long boardidx,
            @PathVariable("lastCommentIdx") long lastCommentIdx,
            Model model){


        log.info(lastCommentIdx);

        List<CommentListModel> list = commentListService.selectBoardCommentLimit(boardidx, lastCommentIdx);
        model.addAttribute("commentList", list);

        return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/{lastCommentIdx}")
    public ResponseEntity<List<CommentListModel>> writeComment(
            @PathVariable("lastCommentIdx") long lastCommentIdx,
            CommentWriteRequest comment,
            HttpServletRequest request){

        log.info(comment);
        log.info(lastCommentIdx);

        // null 체크
        if(comment.getContent()==null || comment.getContent().isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        comment.setUseridx(Long.valueOf(0));
        comment.setNickname("nick");

        log.info(comment);

        List<CommentListModel> list = null;
        try{
            commentWriteService.writeComment(comment);
        } catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(list, HttpStatus.OK);

    }



}
