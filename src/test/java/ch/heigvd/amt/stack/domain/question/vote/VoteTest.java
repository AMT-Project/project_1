package ch.heigvd.amt.stack.domain.question.vote;

import ch.heigvd.amt.stack.domain.person.Person;
import ch.heigvd.amt.stack.domain.question.Question;
import ch.heigvd.amt.stack.domain.question.answer.Answer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class VoteTest {

    @Test
    public void voteThrowsExceptionWhenHasNoAuthor(){
        Person pq = Person.builder()
                .username("oi")
                .clearTextPassword("Password1234")
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