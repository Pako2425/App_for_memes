package com.patryk.app.webapp.Repository;

import com.patryk.app.webapp.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByMemeId(long memeId);
}
