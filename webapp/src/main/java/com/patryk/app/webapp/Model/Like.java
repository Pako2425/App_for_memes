package com.patryk.app.webapp.Model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@Table(name="likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long memeId;
    private Long userId;

    public Like(Long memeId, Long userId) {
        this.memeId = memeId;
        this.userId = userId;
    }
}
