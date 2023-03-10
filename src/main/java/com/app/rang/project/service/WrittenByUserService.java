package com.app.rang.project.service;

import com.app.rang.project.entity.Board;
import com.app.rang.project.entity.Comment;
import com.app.rang.project.model.AuthUserDTO;
import com.app.rang.project.model.comment.CommentListModel;
import com.app.rang.project.model.board.BoardListModel;
import com.app.rang.project.repository.BoardRepository;
import com.app.rang.project.repository.CommentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class WrittenByUserService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private CommentRepository commentRepository;

    public List<BoardListModel> printMyBoard(AuthUserDTO userDTO){

        List<BoardListModel> list = new ArrayList<>();
        List<Board> b = boardRepository.findByUseridx(userDTO.getUseridx());

        for (Board board : b){
            list.add(board.toBoardView());
        }

        return list;

    }

    public List<CommentListModel> printMyComment(AuthUserDTO userDTO){

        List<CommentListModel> list = new ArrayList<>();
        List<Comment> comments = commentRepository.findByUseridx(userDTO.getUseridx());

        for (Comment comment : comments){
            list.add(comment.toCommentView());
        }

        return list;

    }

}
