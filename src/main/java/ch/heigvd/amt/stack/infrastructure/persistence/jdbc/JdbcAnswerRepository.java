package ch.heigvd.amt.stack.infrastructure.persistence.jdbc;

import ch.heigvd.amt.stack.application.question.answer.AnswersQuery;
import ch.heigvd.amt.stack.domain.person.PersonId;
import ch.heigvd.amt.stack.domain.question.QuestionId;
import ch.heigvd.amt.stack.domain.question.answer.Answer;
import ch.heigvd.amt.stack.domain.question.answer.AnswerId;
import ch.heigvd.amt.stack.domain.question.answer.IAnswerRepository;

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
@Named("JdbcAnswerRepository")
public class JdbcAnswerRepository extends JdbcRepository<Answer, AnswerId> implements IAnswerRepository {

    @Resource(lookup = "jdbc/StackDS")
    DataSource dataSource;

    @Override
    public Collection<Answer> find(AnswersQuery query) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            if(query.getAuthorUUID() != null) {
                preparedStatement = conn.prepareStatement(
                    "SELECT * FROM Answer WHERE person_uuid=? ORDER BY created_on ASC");
                preparedStatement.setString(1, query.getAuthorUUID().asString());
            } else if(query.getQuestionUUID() != null) {
                preparedStatement = conn.prepareStatement(
                    "SELECT * FROM Answer WHERE question_uuid=? ORDER BY created_on ASC");
                preparedStatement.setString(1, query.getQuestionUUID().asString());
            }
            if(preparedStatement != null) {
                rs = preparedStatement.executeQuery();
            } else {
                return null;
            }
            return getAnswers(rs);

        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try { if (rs != null) rs.close();} catch (Exception e) {}
            try { if (preparedStatement != null) preparedStatement.close();} catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
        return null;
    }

    @Override
    public void save(Answer answer) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = dataSource.getConnection();
            preparedStatement = conn.prepareStatement(
                "INSERT INTO Answer (uuid, content, question_uuid, person_uuid, created_on)" +
                    "VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, answer.getUuid().asString());
            preparedStatement.setString(2, answer.getContent());
            preparedStatement.setString(3, answer.getQuestionUUID().asString());
            preparedStatement.setString(4, answer.getAuthorUUID().asString());
            // TODO : DATETIME - 2_Utilise un timestamp
            Date date = new Date(System.currentTimeMillis());
            preparedStatement.setTimestamp(5, new Timestamp(date.getTime()));

            // TODO : DATETIME - 1_Ancienne version
            //preparedStatement.setDate(5, new Date(System.currentTimeMillis()));
            preparedStatement.executeUpdate();
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try { if (preparedStatement != null) preparedStatement.close();} catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }

    // TODO : implement all below
    @Override
    public void remove(AnswerId uuid) {

    }

    @Override
    public Optional<Answer> findById(AnswerId uuid) {
        return Optional.empty();
    }

    @Override
    public Collection<Answer> findAll() {
        return null;
    }

    @Override
    public int count() {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            preparedStatement = conn.prepareStatement("SELECT COUNT(*) AS nbAnswers FROM Answer");
            rs = preparedStatement.executeQuery();
            rs.next();
            return rs.getInt("nbAnswers");
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try { if (rs != null) rs.close();} catch (Exception e) {}
            try { if (preparedStatement != null) preparedStatement.close();} catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
        return 0;
    }

    private Collection<Answer> getAnswers(ResultSet rs) throws SQLException {
        LinkedList<Answer> answers = new LinkedList<>();

        while(rs.next()) {
            Answer answer = Answer.builder()
                .uuid(new AnswerId(rs.getString("uuid")))
                .content(rs.getString("content"))
                .questionUUID(new QuestionId(rs.getString("question_uuid")))
                .authorUUID(new PersonId(rs.getString("person_uuid")))
                .createdOn(LocalDateTime.parse(rs.getString("created_on"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
            answers.add(answer);
        }
        return answers;
    }
}
