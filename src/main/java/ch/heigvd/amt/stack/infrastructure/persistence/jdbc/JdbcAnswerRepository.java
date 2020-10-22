package ch.heigvd.amt.stack.infrastructure.persistence.jdbc;

import ch.heigvd.amt.stack.application.question.answer.AnswersQuery;
import ch.heigvd.amt.stack.domain.question.answer.Answer;
import ch.heigvd.amt.stack.domain.question.answer.AnswerId;
import ch.heigvd.amt.stack.domain.question.answer.IAnswerRepository;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
@Named("JdbcAnswerRepository")
public class JdbcAnswerRepository extends JdbcRepository<Answer, AnswerId> implements IAnswerRepository {

    @Resource(lookup = "jdbc/StackDS")
    DataSource dataSource;

    @Override
    public Collection<Answer> find(AnswersQuery query) {
        return null;
    }

    @Override
    public void save(Answer answer) throws SQLIntegrityConstraintViolationException {
        try {
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(
                "INSERT INTO Answer (uuid, content, question_uuid, person_uuid, created_at)" +
                    "VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, answer.getId().asString());
            preparedStatement.setString(2, answer.getContent());
            preparedStatement.setString(3, answer.getQuestionUUID().asString());
            preparedStatement.setString(4, answer.getPersonUUID().asString());
            preparedStatement.setDate(5, new Date(System.currentTimeMillis()));
            preparedStatement.executeUpdate();
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
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

    @Override
    public int count() {
        try {
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement("SELECT COUNT(*) AS nbAnswers FROM Answer");
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return rs.getInt("nbAnswers");
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}
