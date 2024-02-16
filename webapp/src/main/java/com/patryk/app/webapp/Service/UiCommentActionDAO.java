package com.patryk.app.webapp.Service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UiCommentActionDAO {
    private long memeId;
    private long userId;
    private String url;
    private String commentContent;

    public UiCommentActionDAO(long memeId, long userId, String url, String commentContent) {
        this.memeId = memeId;
        this.userId = userId;
        this.url = url;
        this.commentContent = commentContent;
    }
}
