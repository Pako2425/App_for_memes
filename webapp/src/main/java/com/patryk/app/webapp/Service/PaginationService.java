package com.patryk.app.webapp.Service;

import com.patryk.app.webapp.Model.Meme;
import com.patryk.app.webapp.Repository.*;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@PropertySource("classpath:application.properties")
@AllArgsConstructor
public class PaginationService {
    private final MemesRepository memesRepository;
    private final UsersRepository usersRepository;
    private final CommentsRepository commentsRepository;
    private final ImagesRepository imagesRepository;
    private final LikesRepository likesRepository;

    private static final int MAIN_PAGE_SIZE = 2;
    private static final int MAX_MAIN_PAGE_LINKS = 5;

    public void pagePagination(int maxPageLinks, int totalPages, int currentPageIndex, @NotNull Model model) {
        int firstPage = Math.max(0, currentPageIndex - (maxPageLinks / 2));
        int lastPage = Math.min(totalPages - 1, firstPage + (maxPageLinks - 1));
        firstPage = Math.max(0, lastPage - maxPageLinks + 1);

        model.addAttribute("currentPage", currentPageIndex);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("firstPage", firstPage);
        model.addAttribute("lastPage", lastPage);
    }

    public void showMainPage(int pageIndex, @NotNull Model model) {
        Page<Meme> memesPage = memesRepository.findAll(PageRequest.of(pageIndex, MAIN_PAGE_SIZE, Sort.Direction.DESC, "id"));
        List<Meme> memes = memesPage.getContent();
        List<PostDAO> posts;
        posts = memes.stream()
                .map(meme -> new PostDAO(meme,
                        usersRepository,
                        imagesRepository,
                        commentsRepository,
                        likesRepository,
                        (Long)model.getAttribute("sessionUserId")))
                .toList();

        model.addAttribute("posts", posts);
        int totalPages = memesPage.getTotalPages();
        pagePagination(MAX_MAIN_PAGE_LINKS, totalPages, pageIndex, model);
    }

    public void showDetailedPost(long memeId, @NotNull Model model) {
        Meme meme = memesRepository.getReferenceById(memeId);
        PostDAO post = new PostDAO(meme, usersRepository, imagesRepository, commentsRepository, likesRepository, (Long)model.getAttribute("sessionUserId"));
        model.addAttribute("post", post);
    }

    public void showRandomPage(Model model) {
        long totalElements = memesRepository.count();
        long randomMemeId = ThreadLocalRandom.current().nextLong(totalElements);
        showDetailedPost(randomMemeId, model);
    }
}
