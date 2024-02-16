package com.patryk.app.webapp.Service;

import com.patryk.app.webapp.Model.Comment;
import com.patryk.app.webapp.Model.Image;
import com.patryk.app.webapp.Model.Meme;
import com.patryk.app.webapp.Model.User;
import com.patryk.app.webapp.Repository.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PostDAO {
    private long memeId;
    private long userId;
    private String username;
    private String title;
    private String imagePath;
    private int likesNumber;
    private int commentsNumber;
    private List<Comment> comments;
    private boolean isLiked;

    public PostDAO(Meme meme,
                   UsersRepository usersRepository,
                   ImagesRepository imagesRepository,
                   CommentsRepository commentsRepository,
                   LikesRepository likesRepository,
                   Long sessionUserId) {
        this.memeId = meme.getId();
        User user = usersRepository.getReferenceById(meme.getUserId());
        this.userId = user.getId();
        this.username = user.getUsername();
        this.title = meme.getTitle();
        Image image = imagesRepository.getReferenceById(meme.getImageId());
        this.imagePath = image.getFilePath();
        this.likesNumber = meme.getLikesNumber();
        this.commentsNumber = meme.getCommentsNumber();
        this.comments = commentsRepository.findAllByMemeId(meme.getId());
        this.isLiked = sessionUserId != null && likesRepository.findByMemeIdAndUserId(meme.getId(), sessionUserId).isPresent();
    }
}
