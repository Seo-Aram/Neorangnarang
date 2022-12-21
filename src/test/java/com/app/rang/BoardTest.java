package com.app.rang;

import com.app.rang.project.entity.Board;
import com.app.rang.project.model.BoardListModel;
import com.app.rang.project.model.BoardWriteRequest;
import com.app.rang.project.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class BoardTest {

    @Autowired
    private BoardRepository boardRepository;

/*    @Test
    public void test(){

        BoardWriteRequest writeRequest = BoardWriteRequest.builder()
                                                        .category(1)
                                                        .title("test")
                                                        .content("insert test")
                                                        .price(3000)
                                                        .build();

        Board board = writeRequest.toBoardEntity();

        boardRepository.save(board);

    }*/


    @Test
    public void mine(){

        List<BoardListModel> list = new ArrayList<>();
        long useridx=0;
        List<Board> b = boardRepository.findByUseridx(useridx);

        for (Board board : b){
            list.add(board.toBoardView());
        }

        System.out.print(list);

    }
}
