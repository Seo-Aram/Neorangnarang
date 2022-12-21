package com.app.rang.project.repository;

import com.app.rang.project.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    // user index로 게시글 찾기
    @Query("select b from Board b where b.useridx = :useridx order by b.boardidx DESC")
    List<Board> findByUseridx(@Param("useridx") Long useridx);


}
