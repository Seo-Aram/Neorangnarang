package com.app.rang.project.service.board;

import com.app.rang.project.mapper.BoardMapper;
import com.app.rang.project.model.BoardListModel;
import com.app.rang.project.repository.BoardRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class BoardListService {
    @Autowired(required = false)
    private BoardMapper boardMapper;

    public List<BoardListModel> selectBoardListByPage() {
        List<BoardListModel> list = boardMapper.selectItemListByPage();

        return list;
    }

    public List<BoardListModel> selectBoardListByListIdx(long idx) {
        List<BoardListModel> list = boardMapper.selectItemListByItemIdx(idx);

        return list;
    }
}
