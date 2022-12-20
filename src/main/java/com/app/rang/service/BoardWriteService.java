package com.app.rang.service;

import com.app.rang.model.BoardWriteRequest;
import com.app.rang.repository.BoardRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class BoardWriteService {

    @Autowired
    private BoardRepository boardRepository;

    public int writeBoard(BoardWriteRequest writeRequest){


        int result = boardRepository.save(writeRequest.toBoardEntity()) !=null ? 1 : 0;

        return result;
    }


}
