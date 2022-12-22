package com.app.rang.project.service.board;

import com.app.rang.project.entity.Board;
import com.app.rang.project.repository.BoardRepository;
import com.app.rang.project.repository.CommentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2
@Service
public class BoardDeleteService {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private CommentRepository commentRepository;

    public int deleteBoard(long boardidx) {
        Board board = boardRepository.findByBoardidx(boardidx);

        commentRepository.deleteByBoardidx(boardidx);
        int result = boardRepository.deleteByBoardidx(boardidx);
        if(result > 0 && board != null) {
            List<String> fileList = new ArrayList<>(Arrays.asList(board.getImg().split(",")));
            String absolutePath = new File("").getAbsolutePath() + "\\upload";
            List<File> delFile = new ArrayList<>();

            for(String fileName : fileList) {
                delFile.add(new File(absolutePath, fileName));
            }
            delFile.add(new File(absolutePath + "\\thumbnail", board.getThumbnail()));

            for(File file : delFile) {
                if (file.exists()) {
                    // 파일 삭제
                    file.delete();
                }
            }
        }

        return result;
    }
}
