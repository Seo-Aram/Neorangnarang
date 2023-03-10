package com.app.rang.project.repository;

import com.app.rang.project.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Transactional
    @Modifying
    @Query("delete from Comment c where c.boardidx = :boardidx")
    int deleteByBoardidx(long boardidx);

    // boardidx 별로 comment 보여주기
    @Query("select c from Comment c where c.boardidx = :boardidx")
    List<Comment> findByBoardidx(Long boardidx);


    // user index로 댓글 찾고 board index별로 보여주기
    @Query("select c from Comment c where c.useridx = :useridx order by c.boardidx DESC")
    List<Comment> findByUseridx(@Param("useridx") Long useridx);

    @Transactional
    @Modifying
    @Query("delete from Comment c where c.commentidx = :commentidx")
    int deleteByCommentidx(Long commentidx);









}
