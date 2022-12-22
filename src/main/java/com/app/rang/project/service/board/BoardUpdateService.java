package com.app.rang.project.service.board;

import com.app.rang.project.entity.Board;
import com.app.rang.project.repository.BoardRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class BoardUpdateService {

    @Autowired
    private BoardRepository boardRepository;

    public int updateBoard(Board board){
        int result = 0;
        try {
            result = boardRepository.save(board) != null ? 1 : 0;
        } catch (Exception e) {

        }

        return result;
    }
}
