package ch.heigvd.amt.stack.infrastructure.persistence.jdbc;

import ch.heigvd.amt.stack.application.question.answer.AnswersQuery;
import ch.heigvd.amt.stack.domain.question.answer.Answer;
import ch.heigvd.amt.stack.domain.question.answer.AnswerId;
import ch.heigvd.amt.stack.domain.question.answer.IAnswerRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
@Named("JdbcAnswerRepository")
public class JdbcAnswerRepository extends JdbcRepository<Answer, AnswerId> implements IAnswerRepository {

    @Override
    public Collection<Answer> find(AnswersQuery query) {
        return null;
    }

    @Override
    public void save(Answer entity) throws SQLIntegrityConstraintViolationException {

    }

    @Override
    public void remove(AnswerId id) {

    }

    @Override
    public Optional<Answer> findById(AnswerId id) {
        return Optional.empty();
    }

    @Override
    public Collection<Answer> findAll() {
        return null;
    }
}
