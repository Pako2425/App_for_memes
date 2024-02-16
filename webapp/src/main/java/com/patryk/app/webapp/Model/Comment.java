package com.patryk.app.webapp.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long memeId;
    private String username;
    private String content;
    private long parentCommentId;

    public Comment(long memeId, String username, String content, long parentCommentId) {
        this.memeId = memeId;
        this.username = username;
        this.content = content;
        this.parentCommentId = parentCommentId;
    }
}
