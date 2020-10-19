package ch.heigvd.amt.stack.infrastructure.persistence.memory;

import ch.heigvd.amt.stack.application.question.QuestionsQuery;
import ch.heigvd.amt.stack.domain.question.IQuestionRepository;
import ch.heigvd.amt.stack.domain.question.QuestionId;
import ch.heigvd.amt.stack.domain.question.Question;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.util.Collection;

@ApplicationScoped
@Named("InMemoryQuestionRepository")
public class InMemoryQuestionRepository extends InMemoryRepository<Question, QuestionId> implements IQuestionRepository {

    @Resource(lookup = "StackOverflowOS")
    DataSource dataSource;

    public InMemoryQuestionRepository(){

    }

    public InMemoryQuestionRepository(DataSource dataSource) {this.dataSource = dataSource;}

    @Override
    public Collection<Question> find(QuestionsQuery query) {
        return findAll();
    }
}
