package ch.heigvd.amt.stack.infrastructure.persistence.jdbc;

import ch.heigvd.amt.stack.application.question.QuestionsQuery;
import ch.heigvd.amt.stack.domain.person.Person;
import ch.heigvd.amt.stack.domain.person.PersonId;
import ch.heigvd.amt.stack.domain.question.IQuestionRepository;
import ch.heigvd.amt.stack.domain.question.Question;
import ch.heigvd.amt.stack.domain.question.QuestionId;
import lombok.Setter;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
@Named("JdbcQuestionRepository")
public class JdbcQuestionRepository extends JdbcRepository<Question, QuestionId> implements IQuestionRepository {

    @Resource(lookup = "jdbc/StackDS")
    DataSource dataSource;

    public JdbcQuestionRepository(){
    }

    public JdbcQuestionRepository(DataSource dataSource) {this.dataSource = dataSource;}

    @Override
    public Collection<Question> find(QuestionsQuery query) {
        //  TODO implement
        try {
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement("");
            ResultSet rs = super.getSet(preparedStatement);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Question entity) throws SQLIntegrityConstraintViolationException {
        //  TODO implement
        try {
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement("");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void remove(QuestionId id) {
        //  TODO implement

    }

    @Override
    public Optional<Question> findById(QuestionId id) {
        //  TODO implement
        return Optional.empty();
    }

    @Override
    public Collection<Question> findAll() {
        //  TODO implement
        return null;
    }


}
