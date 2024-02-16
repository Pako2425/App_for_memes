package com.patryk.app.webapp.Service;

import com.dropbox.core.DbxException;
import com.patryk.app.webapp.Model.Image;
import com.patryk.app.webapp.Model.Meme;
import com.patryk.app.webapp.Repository.ImagesRepository;
import com.patryk.app.webapp.Repository.MemesRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
@AllArgsConstructor
public class UploadMemeService {
    private final MemesRepository memesRepository;
    private final ImagesRepository imagesRepository;
    private static final String DROPBOX_FILE_SAVING_PATH = "/memes/";
    private final DropboxCommunicationService dropboxCommunicationService;

    public void saveMeme(@NotNull UploadedMemeDAO uploadedMemeDAO) throws IOException, DbxException {
        Image image = new Image();
        Meme meme = new Meme();
        String filePath = dropboxCommunicationService.saveImage(uploadedMemeDAO.getImage(),
                DROPBOX_FILE_SAVING_PATH + uploadedMemeDAO.getTitle() + ".jpg");
        image.setFilePath(filePath);
        image.setUserId(uploadedMemeDAO.getUserId());
        image = imagesRepository.save(image);

        meme.setTitle(uploadedMemeDAO.getTitle());
        meme.setUserId(uploadedMemeDAO.getUserId());
        meme.setImageId(image.getId());
        meme = memesRepository.save(meme);

        image.setMemeId(meme.getId());
        imagesRepository.save(image);
    }
}
