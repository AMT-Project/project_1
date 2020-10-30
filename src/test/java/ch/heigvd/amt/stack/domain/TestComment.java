package ch.heigvd.amt.stack.domain;

import ch.heigvd.amt.stack.domain.person.PersonId;
import ch.heigvd.amt.stack.domain.question.comment.Comment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class TestComment {
    @Mock
    private static PersonId pid;

    @BeforeAll
    private static void brief(){
        pid = mock(PersonId.class);
    }

    @Test
    public void validCreateComment(){
        Comment comment = Comment.builder()
                .content("Dummy content")
                .authorUUID(pid)
                .build();
    }

    @Test
    public void invalidCreateComment(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Comment comment = Comment.builder()
                    .content("Dummy content")
                    // .authorUUID(pid)
                    .build();

        });

        exception = assertThrows(IllegalArgumentException.class, () -> {
            Comment comment = Comment.builder()
                    //.content("Dummy content")
                    .authorUUID(pid)
                    .build();

        });

    }

    @Test
    public void deepCloning(){
        Comment comment = Comment.builder()
                .content("Dummy content")
                .authorUUID(pid)
                .build();

        Comment clone = comment.deepClone();

        assertEquals(comment.getAnswerUUID(), clone.getAnswerUUID());
        assertEquals(comment.getAuthorUUID(), clone.getAuthorUUID());
        assertEquals(comment.getContent(), clone.getContent());
        assertEquals(comment.getQuestionUUID(), clone.getQuestionUUID());
        assertEquals(comment.getCreatedOn(), clone.getCreatedOn());

        assertNotEquals(comment.getUuid(), clone.getUuid());
    }

}
