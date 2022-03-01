package com.realshop.shop.repository;

import com.realshop.shop.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByItemIdOrderByIdAsc(Long itemId);

    @Query("select count(o) from Comment o " + "where o.item.id = :id"
    )
    Long countComment(@Param("id") Long id);
}
