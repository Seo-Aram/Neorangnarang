package com.app.rang.project.mapper;

import com.app.rang.project.entity.Comment;
import com.app.rang.project.model.CommentListModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("SELECT * FROM boardcomment WHERE boardidx = #{boardidx} AND commentidx > #{lastidx} ORDER BY commentidx ASC LIMIT 0, 50")
    List<Comment> selectCommentByLastCommentIdxLimit(long boardidx, long lastidx);

    @Select("SELECT * FROM boardcomment WHERE boardidx = #{boardidx} AND commentidx > #{lastidx} ORDER BY commentidx ASC")
    List<Comment> selectCommentByLastCommentIdx(long boardidx, long lastidx);


}
