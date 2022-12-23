package com.app.rang.project.service.comment;

import com.app.rang.project.entity.Comment;
import com.app.rang.project.mapper.CommentMapper;
import com.app.rang.project.model.comment.CommentListModel;
import com.app.rang.project.repository.CommentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class CommentListService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentMapper commentMapper;


    public List<CommentListModel> selectAllComments(long boardidx){

        List<Comment> comments = commentRepository.findByBoardidx(boardidx);
        List<CommentListModel> list = new ArrayList<>();

        for(Comment c : comments){
            list.add(c.toCommentView());
        }

        return list;
    }


    public List<CommentListModel> selectBoardComment(long boardidx, long lastIdx){

        List<Comment> comments = commentMapper.selectCommentByLastCommentIdx(boardidx, lastIdx);
        List<CommentListModel> list = new ArrayList<>();

        for(Comment c : comments){
            list.add(c.toCommentView());
        }

        return list;
    }


    public List<CommentListModel> selectBoardCommentLimit(long boardidx, long lastIdx){

        List<Comment> comments = commentMapper.selectCommentByLastCommentIdxLimit(boardidx, lastIdx);
        List<CommentListModel> list = new ArrayList<>();

        for(Comment c : comments){
            list.add(c.toCommentView());
        }

        return list;
    }

}
