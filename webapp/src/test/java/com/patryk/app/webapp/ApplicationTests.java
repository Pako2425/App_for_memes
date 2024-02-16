package com.patryk.app.webapp;

import com.patryk.app.webapp.Model.*;
import com.patryk.app.webapp.Repository.*;
import com.patryk.app.webapp.Service.PostDAO;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

//@SpringBootTest
//@AllArgsConstructor
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class ApplicationTests {

	@Test
	void contextLoads() {
	}

	//@Mock
	//private Meme memeMock;

	@Mock
	private UsersRepository usersRepositoryMock;

	@Mock
	private ImagesRepository imagesRepositoryMock;

	@Mock
	private CommentsRepository commentsRepositoryMock;

	@Mock
	private LikesRepository likesRepositoryMock;

	/*
	@Test
	void createPostDTOTest() {
		long memeId = 201L;
		long imageId = 201L;
		long userId = 82L;
		long userIdFirstComment = 83L;
		long userIdSecondComment = 84L;

		Meme meme = new Meme();
		meme.setId(memeId);
		meme.setUserId(userId);
		meme.setTitle("Test Meme");
		meme.setImageId(imageId);
		meme.setLikesNumber(999);
		meme.setCommentsNumber(54);

		User user = new User("john_doe", "john@example.com", "jagusiek31", UserRole.ROLE_USER);
		user.setId(userId);
		Mockito.when(usersRepositoryMock.getReferenceById(userId)).thenReturn(user);

		Image image = new Image("files-url.pl", userId, memeId);
		image.setId(imageId);
		Mockito.when(imagesRepositoryMock.getReferenceById(imageId)).thenReturn(image);

		List<Comment> comments = Arrays.asList(new Comment(memeId, userIdFirstComment, "pierwszy", 0L), new Comment(memeId,userIdSecondComment, "Funny!", 0));
		Mockito.when(commentsRepositoryMock.findAllByMemeId(memeId)).thenReturn(comments);

		Mockito.when(likesRepositoryMock.findByMemeIdAndUserId(memeId, userId)).thenReturn(Optional.of(new Like(memeId, userId)));
		// Tworzenie obiektu PostDAO
		PostDAO postDAO = new PostDAO(meme, usersRepositoryMock, imagesRepositoryMock, commentsRepositoryMock, likesRepositoryMock);

		// Sprawdzanie poprawności utworzonego obiektu
		Assertions.assertEquals("john_doe", postDAO.getUsername());
		Assertions.assertEquals("Test Meme", postDAO.getTitle());
		Assertions.assertEquals("files-url.pl", postDAO.getImagePath());
		Assertions.assertEquals(999, postDAO.getLikesNumber());
		Assertions.assertEquals(54, postDAO.getCommentsNumber());
		Assertions.assertEquals(comments, postDAO.getComments());
		Assertions.assertTrue(postDAO.isLiked());


	}
	*/

}
