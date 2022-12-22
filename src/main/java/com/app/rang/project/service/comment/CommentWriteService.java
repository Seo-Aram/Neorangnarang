package com.app.rang.project.service.comment;

import com.app.rang.project.entity.Comment;
import com.app.rang.project.mapper.CommentMapper;
import com.app.rang.project.model.CommentWriteRequest;
import com.app.rang.project.repository.CommentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class CommentWriteService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentMapper commentMapper;

    public int writeComment(CommentWriteRequest writeRequest){

        Comment comment = writeRequest.toComment();

        comment.setUseridx(Long.valueOf(0));
        comment.setNickname("nick");

        int result=0;

        try{

            //comment.setBoardidx();

            result = commentRepository.save(comment) !=null ? 1 : 0;


        } catch (Exception e){
            e.printStackTrace();
        }

        return result;

    }
    public List<Comment> writeCommentAndSelectList(CommentWriteRequest writeRequest, long lastidx){

        Comment comment = writeRequest.toComment();

        comment.setUseridx(Long.valueOf(0));
        comment.setNickname("nick");

        List<Comment> result = null;

        try{

            // DB에 저장
            commentRepository.save(comment);
            result = commentMapper.selectCommentByLastCommentIdx(comment.getBoardidx(), lastidx); // 작성한 댓글과 함께 최신 댓글 view

        } catch (Exception e){
            e.printStackTrace();
        }

        return result;

    }
}
