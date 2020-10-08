import ch.heigvd.amt.stack.application.question.QuestionsQuery;
import ch.heigvd.amt.stack.domain.person.Person;
import ch.heigvd.amt.stack.domain.question.IQuestionRepository;
import ch.heigvd.amt.stack.domain.question.Question;
import ch.heigvd.amt.stack.domain.question.QuestionId;
import ch.heigvd.amt.stack.infrastructure.persistence.memory.InMemoryQuestionRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UTQuestion {
    @BeforeAll
    public static void init(){
        System.out.println("test");
        assertTrue(true);
    }
    @Test
    public void storeAndRetrieveFromMemory(){
        IQuestionRepository repo = new InMemoryQuestionRepository();
        Person author = Person.builder().clearTextPassword("pass")
                .username("username de test")
        .email("test@test.ch")
        .firstName("prenomtest")
        .lastName("nomTest")
        .build();
        Question question = Question.builder()
//                .author(author.getUsername())
//                .title("titrede test")
//                .description("desc de test")
                .build();
        QuestionId qID = question.getId();

        try {
            repo.save(question);
        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
        }
        Optional<Question> retrievedQ = repo.findById(qID);

        assertTrue(retrievedQ.isPresent());
        retrievedQ.ifPresent(q ->{
//            assertEquals(question.getId(), q.getId());
            assertEquals(question.getAuthor(), q.getAuthor());
            assertEquals(question.getTitle(), q.getTitle());
            assertEquals(question.getDescription(), q.getDescription());
                }


        );
//        if(retrievedQ.isPresent()){
//            Question q = (Question) retrievedQ;
////            assertEquals(retrievedQ.s);
//        }
//        assertEquals(repo.findById(qID)., question);
    }
}
