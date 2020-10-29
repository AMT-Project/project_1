package ch.heigvd.amt.stack.domain.question.vote;

import ch.heigvd.amt.stack.domain.person.Person;
import ch.heigvd.amt.stack.domain.person.PersonId;
import ch.heigvd.amt.stack.domain.question.Question;
import ch.heigvd.amt.stack.domain.question.QuestionId;
import ch.heigvd.amt.stack.domain.question.answer.Answer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class VoteTest {

    @Test
    public void voteThrowsExceptionWhenHasNoAuthor(){
        Person pq = Person.builder()
                .username("oi")
                .clearTextPassword("pwd")
                .firstName("toto")
                .lastName("dupont")
                .email("toto@example.com").build();
        Question q = Question.builder()
                .authorUUID(pq.getUuid())
                .title("my q")
                .description("how to...").build();
        Answer a = Answer.builder()
                .questionUUID(q.getUuid())
                .content("ioj")
                .authorUUID(pq.getUuid())
                .build();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
                Vote.builder()
                        .isUpvote(true)
                        .answerUUID(a.getUuid())
                        .questionUUID(q.getUuid())
                        .build();
        });
    }
}