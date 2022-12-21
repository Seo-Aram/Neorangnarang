package com.app.rang.project.service.comment;

import com.app.rang.project.entity.Comment;
import com.app.rang.project.model.CommentListModel;
import com.app.rang.project.model.CommentWriteRequest;
import com.app.rang.project.repository.CommentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Log4j2
@Service
public class CommentWriteService {

    @Autowired
    private CommentRepository commentRepository;

    public int writeComment(CommentWriteRequest writeRequest){

        int result=0;
        try{
            Comment comment = writeRequest.toComment();
            result = commentRepository.save(comment) !=null ? 1 : 0;
        } catch (Exception e){
            e.printStackTrace();
        }

        return result;

    }
}
