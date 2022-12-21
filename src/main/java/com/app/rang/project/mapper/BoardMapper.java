package com.app.rang.project.mapper;

import com.app.rang.project.model.BoardListModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BoardMapper {
    @Select("SELECT * FROM board ORDER BY boardidx DESC LIMIT 0, 20")
    List<BoardListModel> selectItemListByPage();
    @Select("SELECT * FROM board WHERE boardidx < ${idx} ORDER BY boardidx DESC LIMIT 0, 20")
    List<BoardListModel> selectItemListByItemIdx(long idx);
}
