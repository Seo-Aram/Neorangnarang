package com.app.rang.project.service.board;

import com.app.rang.project.entity.Board;
import com.app.rang.project.model.board.BoardWriteRequest;
import com.app.rang.project.repository.BoardRepository;
import com.app.rang.project.util.Util;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.*;

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
