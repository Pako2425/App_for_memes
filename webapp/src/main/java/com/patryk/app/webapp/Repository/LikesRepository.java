package com.patryk.app.webapp.Repository;

import com.patryk.app.webapp.Model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikesRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByMemeIdAndUserId(long memeId, long userId);
}
