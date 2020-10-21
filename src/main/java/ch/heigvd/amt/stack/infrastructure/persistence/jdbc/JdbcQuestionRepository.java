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
import java.util.LinkedList;
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
        //   pas d'utilisation de query????
        try {
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(
                    "SELECT * FROM Question");
            ResultSet rs = preparedStatement.executeQuery();

            LinkedList<Question> questions = new LinkedList<>();

            while(rs.next()){
                Question question = Question.builder()
                        .id(new QuestionId(rs.getString("uuid")))
                        .title(rs.getString("title"))
                        .description(rs.getString("description"))
                        .authorUUID(new PersonId(rs.getString("person_uuid")))
                        .build();
                questions.add(question);
            }

            return questions;

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
                    "INSERT INTO Question (uuid, title, description, person_uuid)" +
                    "VALUES (?,?,?,?)");
            preparedStatement.setString(1, question.getId().asString());
            preparedStatement.setString(2, question.getTitle());
            preparedStatement.setString(3, question.getDescription());
            preparedStatement.setString(4, question.getAuthorUUID().asString());
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
                    "DELETE FROM Question * WHERE id=?");
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
                    "SELECT * FROM Question WHERE id=?");
            preparedStatement.setString(1, id.asString());
            ResultSet rs = preparedStatement.executeQuery();

            LinkedList<Question> questions = new LinkedList<>();

            while(rs.next()){
                Question question = Question.builder()
                        .id(new QuestionId(rs.getString("uuid")))
                        .title(rs.getString("title"))
                        .description(rs.getString("description"))
                        .authorUUID(new PersonId(rs.getString("person_uuid")))
                        .build();
                questions.add(question);
            }

            if(questions.size() != 1) {
                return Optional.empty();
            }

            return Optional.of(questions.get(0));
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

            LinkedList<Question> questions = new LinkedList<>();

            while(rs.next()){
                Question question = Question.builder()
                        .id(new QuestionId(rs.getString("uuid")))
                        .title(rs.getString("title"))
                        .description(rs.getString("description"))
                        .authorUUID(new PersonId(rs.getString("person_uuid")))
                        .build();
                questions.add(question);
            }

            return questions;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
