package com.app.rang.project.service.comment;

import com.app.rang.project.entity.Comment;
import com.app.rang.project.mapper.CommentMapper;
import com.app.rang.project.model.comment.CommentListModel;
import com.app.rang.project.model.comment.CommentWriteRequest;
import com.app.rang.project.repository.CommentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        int result=0;

        try{

            result = commentRepository.save(comment) !=null ? 1 : 0;


        } catch (Exception e){
            e.printStackTrace();
        }

        return result;

    }

    public List<CommentListModel> writeCommentAndSelectList(CommentWriteRequest writeRequest, long lastidx, long userIdx){

        Comment comment = writeRequest.toComment();

        comment.setUseridx(writeRequest.getUseridx());
        comment.setNickname(writeRequest.getNickname());

        List<CommentListModel> listModel = new ArrayList<>();

        try{
            // DB에 저장
            commentRepository.save(comment);
            List<Comment> commentList = commentMapper.selectCommentByLastCommentIdx(comment.getBoardidx(), lastidx); // 작성한 댓글과 함께 최신 댓글 view

            for(Comment c : commentList) {
                CommentListModel m = c.toCommentView();
                m.setIsmine(userIdx == c.getUseridx());
                listModel.add(m);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return listModel;

    }
}
