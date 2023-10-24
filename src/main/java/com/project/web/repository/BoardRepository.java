package com.project.web.repository;

import com.project.web.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Modifying
    @Query(value = "update Board b set b.hits=b.hits+1 where b.id=:id")
    void updateHits(@Param("id") Long id);
}
