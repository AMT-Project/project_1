package ch.heigvd.amt.stack.domain;

import ch.heigvd.amt.stack.domain.person.PersonId;
import ch.heigvd.amt.stack.domain.question.vote.Vote;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class TestVote {
    @Mock
    private static PersonId pid;

    @BeforeAll
    private static void brief(){
        pid = mock(PersonId.class);
    }

    @Test
    public void validCreateVote(){
        Vote vote = Vote.builder()
                .authorUUID(pid)
                .build();
    }

    @Test
    public void invalidCreateVote(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Vote vote = Vote.builder()
                    //.authorUUID(pid)
                    .build();
        });

    }

    @Test
    public void deepCloning(){
        Vote vote = Vote.builder()
                .authorUUID(pid)
                .build();

        Vote clone = vote.deepClone();

        assertEquals(vote.getAnswerUUID(), clone.getAnswerUUID());
        assertEquals(vote.getAuthorUUID(), clone.getAuthorUUID());
        assertEquals(vote.getQuestionUUID(), clone.getQuestionUUID());
        assertEquals(vote.isUpvote(), clone.isUpvote());

        assertNotEquals(vote.getUuid(), clone.getUuid());
    }

}
