package com.app.rang;

import com.app.rang.entity.Board;
import com.app.rang.model.BoardWriteRequest;
import com.app.rang.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class BoardTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void test(){

        BoardWriteRequest writeRequest = BoardWriteRequest.builder()
                                                        .category(1)
                                                        .title("test")
                                                        .content("insert test")
                                                        .price(3000)
                                                        .build();

        Board board = writeRequest.toBoardEntity();

        boardRepository.save(board);

    }
}
