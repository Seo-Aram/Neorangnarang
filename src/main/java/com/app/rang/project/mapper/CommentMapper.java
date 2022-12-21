package com.app.rang.project.mapper;

import com.app.rang.project.entity.Comment;
import com.app.rang.project.model.CommentListModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("SELECT * FROM boardcomment WHERE boardidx = #{boardidx} AND commentidx > #{lastIdx} ORDER BY commentidx ASC LIMIT 0, 50")
    List<Comment> selectCommentByLastCommentIdxLimit(long boardidx, long lastIdx);

    @Select("SELECT * FROM boardcomment WHERE boardidx = #{boardidx} AND commentidx > #{lastIdx} ORDER BY commentidx ASC")
    List<Comment> selectCommentByLastCommentIdx(long boardidx, long lastIdx);


}
