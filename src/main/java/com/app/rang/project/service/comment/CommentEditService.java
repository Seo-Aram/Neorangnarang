package com.app.rang.project.service.comment;

import com.app.rang.project.entity.Comment;
import com.app.rang.project.repository.CommentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class CommentEditService {

    @Autowired
    private CommentRepository commentRepository;

    public int editComment(Comment comment){

        int result = 0;

        try{

            result = commentRepository.save(comment) !=null ? 1 : 0;

        } catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }
}
