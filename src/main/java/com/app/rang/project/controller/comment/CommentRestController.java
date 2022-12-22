package com.app.rang.project.controller.comment;

import com.app.rang.project.entity.Comment;
import com.app.rang.project.model.CommentListModel;
import com.app.rang.project.model.CommentWriteRequest;
import com.app.rang.project.service.comment.CommentDeleteService;
import com.app.rang.project.service.comment.CommentListService;
import com.app.rang.project.service.comment.CommentWriteService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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


    @GetMapping("/{boardidx}/{lastCommentIdx}")
    public ResponseEntity<List<CommentListModel>> selectList(
            @PathVariable("boardidx") long boardidx,
            @PathVariable("lastCommentIdx") long lastCommentIdx,
            Model model){


        log.info(lastCommentIdx);

        HttpHeaders httpHeaders = new HttpHeaders();

        List<CommentListModel> list = commentListService.selectBoardCommentLimit(boardidx, lastCommentIdx);
        model.addAttribute("commentList", list);

        log.info("list >>>>>>>> " + list);

        return new ResponseEntity<>(list, httpHeaders, HttpStatus.OK);

    }

    @PostMapping("/{lastCommentIdx}")
    public ResponseEntity<List<Comment>> writeComment(
            @PathVariable("lastCommentIdx") long lastCommentIdx,
            @RequestBody CommentWriteRequest comment){

        log.info("not inserted >>> " + comment);
        log.info("lastCommentIdx >>> " + lastCommentIdx);

        HttpHeaders httpHeaders = new HttpHeaders();

        // null 체크
        if(comment.getContent()==null || comment.getContent().isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        /*comment.setUseridx(Long.valueOf(0));
        comment.setNickname("nick");*/


        List<Comment> list = null;
        try{
            list = commentWriteService.writeCommentAndSelectList(comment, lastCommentIdx);
        } catch (Exception e){
            e.printStackTrace();
        }


        log.info("insert comment >>>>>> " + comment);

        return new ResponseEntity<>(list, httpHeaders, HttpStatus.OK);

    }


    @DeleteMapping("/{commentidx}")
    public ResponseEntity<Integer> deleteComment(@PathVariable("commentidx") long commentidx){

        log.info("delete comment ... ");
        HttpHeaders httpHeaders = new HttpHeaders();

        return new ResponseEntity<>(commentDeleteService.deleteComment(commentidx), httpHeaders, HttpStatus.OK);

    }


}
