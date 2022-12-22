package com.app.rang.project.service.comment;

import com.app.rang.project.repository.CommentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class CommentDeleteService {

    @Autowired
    private CommentRepository commentRepository;

    public int deleteComment(long commentidx){

        return commentRepository.deleteByCommentidx(commentidx);

    }


}
