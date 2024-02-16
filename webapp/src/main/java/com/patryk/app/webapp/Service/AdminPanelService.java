package com.patryk.app.webapp.Service;

import com.patryk.app.webapp.Model.User;
import com.patryk.app.webapp.Model.Meme;
import com.patryk.app.webapp.Repository.MemesRepository;
import com.patryk.app.webapp.Repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.util.List;

@Service
@AllArgsConstructor
public class AdminPanelService {
    private final UsersRepository usersRepository;
    private final MemesRepository memesRepository;

    public void showUsersList(@NotNull Model model) {
        List<User> users = usersRepository.findAll();
        model.addAttribute("users", users);
    }

    public void showMemesList(@NotNull Model model) {
        List<Meme> memes = memesRepository.findAll();
        model.addAttribute("memes", memes);
    }

    public void showUser(long userId, @NotNull Model model) {
        User user = usersRepository.getReferenceById(userId);
        model.addAttribute("user", user);
    }

    public void showMeme(long memeId, @NotNull Model model) {
        Meme meme = memesRepository.getReferenceById(memeId);
        model.addAttribute("meme", meme);
    }

    public void updateUserStatus(long userId, boolean unlock, @NotNull Model model) {
        User userToUpdate = usersRepository.getReferenceById(userId);
        userToUpdate.setBlocked(!unlock);
        User updatedUser = usersRepository.save(userToUpdate);
        model.addAttribute("user", updatedUser);
    }

    public void updateMemeStatus(long memeId, boolean unlock, @NotNull Model model) {
        Meme memeToUpdate = memesRepository.getReferenceById(memeId);
        memeToUpdate.setBlocked(!unlock);
        Meme updatedMeme = memesRepository.save(memeToUpdate);
        model.addAttribute("meme", updatedMeme);
    }
}
