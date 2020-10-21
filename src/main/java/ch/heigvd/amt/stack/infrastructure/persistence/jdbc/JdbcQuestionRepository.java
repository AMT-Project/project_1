package ch.heigvd.amt.stack.infrastructure.persistence.jdbc;

import ch.heigvd.amt.stack.application.question.QuestionsQuery;
import ch.heigvd.amt.stack.domain.person.PersonId;
import ch.heigvd.amt.stack.domain.question.IQuestionRepository;
import ch.heigvd.amt.stack.domain.question.Question;
import ch.heigvd.amt.stack.domain.question.QuestionId;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

@ApplicationScoped
@Named("JdbcQuestionRepository")
public class JdbcQuestionRepository extends JdbcRepository<Question, QuestionId> implements IQuestionRepository {
    @Resource(lookup = "jdbc/StackDS")
    DataSource dataSource;

    public JdbcQuestionRepository(){}

    public JdbcQuestionRepository(DataSource dataSource) {this.dataSource = dataSource;}

    public Collection<Question> find(QuestionsQuery query) {
        //  TODO implement
        //   pas d'utilisation de query????
        try {
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(
                    "SELECT * FROM Question");
            ResultSet rs = preparedStatement.executeQuery();

            return getQuestions(rs);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Question question) throws SQLIntegrityConstraintViolationException {
        //  TODO implement
        try {
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(
                    "INSERT INTO Question (uuid, title, description, person_uuid, created_at)" +
                    "VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, question.getId().asString());
            preparedStatement.setString(2, question.getTitle());
            preparedStatement.setString(3, question.getDescription());
            preparedStatement.setString(4, question.getAuthorUUID().asString());
            preparedStatement.setDate(5, new Date(System.currentTimeMillis()));
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void remove(QuestionId id) {
        //  TODO implement
        try {
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(
                    "DELETE FROM Question * WHERE uuid=?");
            preparedStatement.setString(1, id.asString());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Optional<Question> findById(QuestionId id) {
        //  TODO implement
        try {
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(
                    "SELECT * FROM Question WHERE uuid=?");
            preparedStatement.setString(1, id.asString());
            ResultSet rs = preparedStatement.executeQuery();

            Collection<Question> questions = getQuestions(rs);

            if(questions.size() != 1) {
                return Optional.empty();
            }

            return Optional.of(questions.iterator().next());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Collection<Question> findAll() {
        //  TODO implement
        try {
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(
                    "SELECT * FROM Question");
            ResultSet rs = preparedStatement.executeQuery();

            return getQuestions(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private Collection<Question> getQuestions(ResultSet rs) throws SQLException {
        LinkedList<Question> questions = new LinkedList<>();

        while (rs.next()) {
            Question question = Question.builder()
                    .id(new QuestionId(rs.getString("uuid")))
                    .title(rs.getString("title"))
                    .description(rs.getString("description"))
                    .authorUUID(new PersonId(rs.getString("person_uuid")))
                    .build();
            questions.add(question);
        }

        return questions;
    }
}
