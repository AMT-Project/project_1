
import ch.heigvd.amt.stack.domain.question.IQuestionRepository;
import ch.heigvd.amt.stack.domain.question.Question;
import ch.heigvd.amt.stack.infrastructure.persistence.memory.InMemoryQuestionRepository;
import org.junit.jupiter.api.Test;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UTQuestion {
    private static final String TITLE = "Comment faire des tests unitaires?";
    private static final String DESC = "Bonjour! Je suis une pive et j'ai besoin d'aide.";
    private static final String AUTHOR = "octocat3000";

    @Test
    public void validCreateQuestion(){
        Question question = Question.builder()
                .title(TITLE)
                .description(DESC)
                .author(AUTHOR)
                .build();

        assertEquals(question.getTitle(), TITLE);
        assertEquals(question.getDescription(), DESC);
        assertEquals(question.getAuthor(), AUTHOR);
    }

    @Test
    public void storeAndRetrieveFromMemory(){
        IQuestionRepository repo = new InMemoryQuestionRepository();    //TODO mock

        Question question = Question.builder().build();

        try {
            repo.save(question);
        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
        }
        Optional<Question> retrievedQ = repo.findById(question.getId());

        assertTrue(retrievedQ.isPresent());
        retrievedQ.ifPresent(q ->{
                    assertNotEquals(question.getId(), q.getId());
                    assertEquals(question.getAuthor(), q.getAuthor());
                    assertEquals(question.getTitle(), q.getTitle());
                    assertEquals(question.getDescription(), q.getDescription());
                }


        );
    }
}
