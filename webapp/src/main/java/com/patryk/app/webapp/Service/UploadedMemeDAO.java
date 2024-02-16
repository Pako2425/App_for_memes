package com.patryk.app.webapp.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@AllArgsConstructor
public class UploadedMemeDAO {
    private String title;
    private MultipartFile image;
    private long userId;

}
