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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

@ApplicationScoped
@Named("JdbcQuestionRepository")
public class JdbcQuestionRepository extends JdbcRepository<Question, QuestionId> implements IQuestionRepository {
    @Resource(lookup = "jdbc/StackDS")
    DataSource dataSource;

    public JdbcQuestionRepository() {
    }

    public JdbcQuestionRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Collection<Question> find(QuestionsQuery query) {
        try {
            PreparedStatement preparedStatement;
            if(query.getAuthorUUID() != null) {
                preparedStatement = dataSource.getConnection().prepareStatement(
                    "SELECT * FROM Question WHERE person_uuid=?");
                preparedStatement.setString(1, query.getAuthorUUID().asString());
            } else {
                preparedStatement = dataSource.getConnection().prepareStatement(
                    "SELECT * FROM Question");
            }
            ResultSet rs = preparedStatement.executeQuery();

            return getQuestions(rs);

        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Question question) throws SQLIntegrityConstraintViolationException {
        //  TODO implement
        try {
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(
                "INSERT INTO Question (uuid, title, description, person_uuid, created_on)" +
                    "VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, question.getUuid().asString());
            preparedStatement.setString(2, question.getTitle());
            preparedStatement.setString(3, question.getDescription());
            preparedStatement.setString(4, question.getAuthorUUID().asString());
            preparedStatement.setString(5, question.getCreatedOn().toString());
            //Date date = new Date(System.currentTimeMillis());
            //preparedStatement.setTimestamp(5, new Timestamp(date.getTime()));
            preparedStatement.executeUpdate();
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void remove(QuestionId uuid) {
        //  TODO implement
        try {
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(
                "DELETE FROM Question * WHERE uuid=?");
            preparedStatement.setString(1, uuid.asString());
            preparedStatement.executeUpdate();
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Optional<Question> findById(QuestionId uuid) {
        //  TODO implement
        try {
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(
                "SELECT * FROM Question WHERE uuid=?");
            preparedStatement.setString(1, uuid.asString());
            ResultSet rs = preparedStatement.executeQuery();

            Collection<Question> questions = getQuestions(rs);

            if(questions.size() != 1) {
                return Optional.empty();
            }

            return Optional.of(questions.iterator().next());
        } catch(SQLException throwables) {
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
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public int count() {
        try {
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement("SELECT COUNT(*) AS nbQuestions FROM Question");
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return rs.getInt("nbQuestions");
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    private Collection<Question> getQuestions(ResultSet rs) throws SQLException {
        LinkedList<Question> questions = new LinkedList<>();

        while(rs.next()) {
            Question question = Question.builder()
                .uuid(new QuestionId(rs.getString("uuid")))
                .title(rs.getString("title"))
                .description(rs.getString("description"))
                .authorUUID(new PersonId(rs.getString("person_uuid")))
                .createdOn(LocalDateTime.parse(rs.getString("created_on"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
            questions.add(question);
        }

        return questions;
    }
}
