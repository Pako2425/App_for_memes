package com.patryk.app.webapp.Service;

import com.patryk.app.webapp.Model.Comment;
import com.patryk.app.webapp.Model.Like;
import com.patryk.app.webapp.Model.Meme;
import com.patryk.app.webapp.Repository.CommentsRepository;
import com.patryk.app.webapp.Repository.LikesRepository;
import com.patryk.app.webapp.Repository.MemesRepository;
import com.patryk.app.webapp.Repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UIActionsService {
    private final MemesRepository memesRepository;
    private final LikesRepository likesRepository;
    private final UsersRepository usersRepository;
    private final CommentsRepository commentsRepository;

    public void updateLikes(@NotNull UiFavoriteActionDAO uiFavoriteActionDAO) {
        long memeId = uiFavoriteActionDAO.getMemeId();
        long userId = uiFavoriteActionDAO.getUserId();
        Optional<Like> like = likesRepository.findByMemeIdAndUserId(memeId, userId);
        if(like.isEmpty()) {
            Like newLike = new Like(memeId, userId);
            likesRepository.save(newLike);
            Meme meme = memesRepository.getReferenceById(memeId);
            meme.setLikesNumber(meme.getLikesNumber() + 1);
            memesRepository.save(meme);
        }
        else {
            likesRepository.deleteById(like.get().getId());
            Meme meme = memesRepository.getReferenceById(memeId);
            meme.setLikesNumber(meme.getLikesNumber() - 1);
            memesRepository.save(meme);
        }
    }

    public void updateComments(@NotNull UiCommentActionDAO uiCommentActionDAO) {
        long memeId = uiCommentActionDAO.getMemeId();
        long userId = uiCommentActionDAO.getUserId();
        String commentContent = uiCommentActionDAO.getCommentContent();
        String username = usersRepository.getReferenceById(userId).getUsername();
        commentsRepository.save(new Comment(memeId, username, commentContent, 0));
        Meme meme = memesRepository.getReferenceById(memeId);
        meme.setCommentsNumber(meme.getCommentsNumber() + 1);
        memesRepository.save(meme);
    }
}
