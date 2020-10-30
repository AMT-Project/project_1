package ch.heigvd.amt.stack.domain.question.comment;

import ch.heigvd.amt.stack.domain.person.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CommentTest {

    @Test
    public void throwsIllegalArgumentExceptionWhenNoContent(){
        Person p = Person.builder()
                .username("ti")
                .firstName("OIjo")
                .lastName("wqeuq")
                .email("ti@example.com")
                .clearTextPassword("Password1234")
                .build();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Comment.builder()
                    .authorUUID(p.getUuid())
                    .build();
        });
    }

    @Test
    public void throwIllegalArgumentExceptionWhenNoAuthor(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Comment.builder()
                    .content("hi")
                    .build();
        });
    }
}