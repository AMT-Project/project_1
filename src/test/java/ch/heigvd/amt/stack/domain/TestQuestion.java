package ch.heigvd.amt.stack.domain;

import ch.heigvd.amt.stack.domain.person.PersonId;
import ch.heigvd.amt.stack.domain.question.Question;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class TestQuestion {
    private static final String TITLE = "Comment faire des tests unitaires?";
    private static final String DESC = "Bonjour! Je suis une pive et j'ai besoin d'aide.";

    @Mock
    private static PersonId pid;

   @BeforeAll
    private static void brief(){
        pid = mock(PersonId.class);
    }

    @Test
    public void validCreateQuestion(){
        Question question = Question.builder()
                .title(TITLE)
                .description(DESC)
                .authorUUID(pid)
                .build();

        assertEquals(question.getTitle(), TITLE);
        assertEquals(question.getDescription(), DESC);
        assertEquals(question.getAuthorUUID(), pid);
    }

    @Test
    public void invalidCreateQuestion(){
        Exception error = assertThrows(IllegalArgumentException.class, () -> {
            Question question = Question.builder()
                    .title(TITLE)
                    .description(DESC)
                   // .authorUUID(pid)
                    .build();
        });


        error = assertThrows(IllegalArgumentException.class, () -> {
            Question question = Question.builder()
                    .title(TITLE)
                    //.description(DESC)
                    .authorUUID(pid)
                    .build();
        });

        error = assertThrows(IllegalArgumentException.class, () -> {
            Question question = Question.builder()
                    //.title(TITLE)
                    .description(DESC)
                    .authorUUID(pid)
                    .build();
        });
    }

    @Test
    public void deepCloning(){
        Question question = Question.builder()
                .title(TITLE)
                .description(DESC)
                .authorUUID(pid)
                .build();

        Question clone = question.deepClone();

        assertEquals(question.getAuthorUUID(), clone.getAuthorUUID());
        assertEquals(question.getDescription(),clone.getDescription());
        assertEquals(question.getTitle(),clone.getTitle());

        assertNotEquals(question.getUuid(), clone.getUuid());
    }
}
