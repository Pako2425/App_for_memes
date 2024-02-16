package com.patryk.app.webapp.Service;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
public class UiFavoriteActionDAO {
    private long memeId;
    private long userId;
    private String url;
}
