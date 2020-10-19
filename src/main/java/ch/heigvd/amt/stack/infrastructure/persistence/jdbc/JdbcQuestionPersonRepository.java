package ch.heigvd.amt.stack.infrastructure.persistence.jdbc;

import ch.heigvd.amt.stack.application.question.QuestionsQuery;
import ch.heigvd.amt.stack.domain.question.IQuestionRepository;
import ch.heigvd.amt.stack.domain.question.Question;
import ch.heigvd.amt.stack.domain.question.QuestionId;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
@Named("JdbcQuestionRepository")
public class JdbcQuestionPersonRepository implements IQuestionRepository {

    @Resource(lookup = "jdbc/StackDS")
    DataSource dataSource;

    public JdbcQuestionPersonRepository(){

    }

    public JdbcQuestionPersonRepository(DataSource dataSource) {this.dataSource = dataSource;}

    @Override
    public Collection<Question> find(QuestionsQuery query) {
        return null;
    }

    @Override
    public void save(Question entity) throws SQLIntegrityConstraintViolationException {

    }

    @Override
    public void remove(QuestionId id) {

    }

    @Override
    public Optional<Question> findById(QuestionId id) {
        return Optional.empty();
    }

    @Override
    public Collection<Question> findAll() {
        return null;
    }
}
