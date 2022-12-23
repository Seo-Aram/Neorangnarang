package com.app.rang.project.controller.comment;

import com.app.rang.project.entity.Comment;
import com.app.rang.project.model.AuthUserDTO;
import com.app.rang.project.model.comment.CommentListModel;
import com.app.rang.project.model.comment.CommentWriteRequest;
import com.app.rang.project.service.comment.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/comment")
public class CommentRestController {

    @Autowired
    private CommentListService commentListService;

    @Autowired
    private CommentWriteService commentWriteService;

    @Autowired
    private CommentDeleteService commentDeleteService;

    @Autowired
    private CommentEditService commentEditService;

    @Autowired
    private CommentReadService commentReadService;


    @GetMapping("/{boardidx}/{lastCommentIdx}")
    public ResponseEntity<List<CommentListModel>> selectList(
            @PathVariable("boardidx") long boardidx,
            @PathVariable("lastCommentIdx") long lastCommentIdx,
            Model model){


        log.info(lastCommentIdx);

        HttpHeaders httpHeaders = new HttpHeaders();

        List<CommentListModel> list = commentListService.selectBoardCommentLimit(boardidx, lastCommentIdx);
        model.addAttribute("commentList", list);

        log.info("list >>>>>>>> " + list.size());

        return new ResponseEntity<>(list, httpHeaders, HttpStatus.OK);

    }

    @PostMapping("/{lastCommentIdx}")
    public ResponseEntity<List<CommentListModel>> writeComment(
            @AuthenticationPrincipal AuthUserDTO userDTO,
            @PathVariable("lastCommentIdx") long lastCommentIdx,
            @RequestBody CommentWriteRequest comment){

        log.info("insert comment ... ");

        log.info("not inserted >>> " + comment);
        log.info("lastCommentIdx >>> " + lastCommentIdx);
        log.info("Useridx >>>" + userDTO.getUseridx());

        HttpHeaders httpHeaders = new HttpHeaders();

        // null 체크
        if(comment.getContent()==null || comment.getContent().isEmpty()){
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.BAD_REQUEST);
        }

        log.info("Useridx >>>" + userDTO.getUseridx());

        comment.setUseridx(userDTO.getUseridx());
        comment.setNickname(userDTO.getNickname());

        List<CommentListModel> listModel = null;
        try{
            listModel = commentWriteService.writeCommentAndSelectList(comment, lastCommentIdx, userDTO.getUseridx());
        } catch (Exception e){
            e.printStackTrace();
        }


        log.info("insert comment >>>>>> " + comment);

        return new ResponseEntity<>(listModel, httpHeaders, HttpStatus.OK);

    }

    @PostMapping("/{boardidx}/{commentidx}")
    public ResponseEntity<Integer> editComment(
            @AuthenticationPrincipal AuthUserDTO userDTO,
            @PathVariable("commentidx") long commentidx,
            @RequestBody Comment comment){

        log.info("edit comment ... ");
        HttpHeaders httpHeaders = new HttpHeaders();

        comment.setUseridx(userDTO.getUseridx());

        log.info("useridx >>> " + comment.getUseridx());

        // 수정시 권한 체크
        if(comment.getUseridx() != userDTO.getUseridx()){
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(commentEditService.editComment(comment), httpHeaders, HttpStatus.OK);
    }


    @DeleteMapping("/{commentidx}")
    public ResponseEntity<Integer> deleteComment(
            @AuthenticationPrincipal AuthUserDTO userDTO,
            Comment comment,
            @PathVariable("commentidx") long commentidx){

        log.info("delete comment ... ");
        HttpHeaders httpHeaders = new HttpHeaders();

        if(userDTO.getUseridx() != comment.getUseridx()){
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(commentDeleteService.deleteComment(commentidx), httpHeaders, HttpStatus.OK);

    }

    @GetMapping("/{commentidx}")
    public ResponseEntity<Comment> getComment(
            @AuthenticationPrincipal AuthUserDTO userDTO,
            @PathVariable("commentidx")long commentidx
    ){

        HttpHeaders httpHeaders = new HttpHeaders();
        Comment comment = commentReadService.getComment(commentidx);

        if(comment.getUseridx() != userDTO.getUseridx()) {
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(comment, httpHeaders, HttpStatus.OK);
    }


}
