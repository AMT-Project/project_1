package ch.heigvd.amt.stack.domain;

import ch.heigvd.amt.stack.domain.person.PersonId;
import ch.heigvd.amt.stack.domain.question.QuestionId;
import ch.heigvd.amt.stack.domain.question.answer.Answer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


@ExtendWith(MockitoExtension.class)
public class TestAnswer {
    @Mock
    private static PersonId pid;

    @Mock
    private static QuestionId qid;

    @BeforeAll
    private static void brief(){
        pid = mock(PersonId.class);
        qid = mock(QuestionId.class);
    }

    @Test
    public void validCreateAnswer(){
        Answer answer = Answer.builder()
                .content("Dummy answer")
                .authorUUID(pid)
                .questionUUID(qid)
                .build();
    }

    @Test
    public void invalidCreateAnswer(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Answer answer = Answer.builder()
                    //.content("Dummy answer")
                    .authorUUID(pid)
                    .questionUUID(qid)
                    .build();

        });

        exception = assertThrows(IllegalArgumentException.class, () -> {
            Answer answer = Answer.builder()
                    .content("Dummy answer")
                    //.authorUUID(pid)
                    .questionUUID(qid)
                    .build();

        });

        exception = assertThrows(IllegalArgumentException.class, () -> {
            Answer answer = Answer.builder()
                    .content("Dummy answer")
                    .authorUUID(pid)
                    //.questionUUID(qid)
                    .build();

        });
    }

    @Test
    public void deepCloning(){
        Answer answer = Answer.builder()
                .content("Dummy answer")
                .authorUUID(pid)
                .questionUUID(qid)
                .build();

        Answer clone = answer.deepClone();

        assertEquals(answer.getAuthorUUID(), clone.getAuthorUUID());
        assertEquals(answer.getContent(), clone.getContent());
        assertEquals(answer.getCreatedOn(), clone.getCreatedOn());
        assertEquals(answer.getQuestionUUID(), clone.getQuestionUUID());


        assertNotEquals(answer.getUuid(), clone.getUuid());
    }
}
