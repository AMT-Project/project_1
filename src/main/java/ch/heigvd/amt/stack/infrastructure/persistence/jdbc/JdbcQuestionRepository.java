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

    @Override
    public void save(Question question) throws SQLIntegrityConstraintViolationException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = dataSource.getConnection();
            preparedStatement = conn.prepareStatement(
                "INSERT INTO Question (uuid, title, description, person_uuid, created_on)" +
                    "VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, question.getUuid().asString());
            preparedStatement.setString(2, question.getTitle());
            preparedStatement.setString(3, question.getDescription());
            preparedStatement.setString(4, question.getAuthorUUID().asString());

            Date date = new Date(System.currentTimeMillis());
            preparedStatement.setTimestamp(5, new Timestamp(date.getTime()));

            preparedStatement.executeUpdate();
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try { if (preparedStatement != null) preparedStatement.close();} catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }

    @Override
    public void remove(QuestionId uuid) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = dataSource.getConnection();
            preparedStatement = conn.prepareStatement(
                "DELETE FROM Question * WHERE uuid=?");
            preparedStatement.setString(1, uuid.asString());
            preparedStatement.executeUpdate();
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try { if (preparedStatement != null) preparedStatement.close();} catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }

    @Override
    public Optional<Question> findById(QuestionId uuid) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            preparedStatement = dataSource.getConnection().prepareStatement(
                "SELECT * FROM Question WHERE uuid=?");
            preparedStatement.setString(1, uuid.asString());
            rs = preparedStatement.executeQuery();

            Collection<Question> questions = getQuestions(rs);

            if(questions.size() != 1) {
                return Optional.empty();
            }

            return Optional.of(questions.iterator().next());
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try { if (rs != null) rs.close();} catch (Exception e) {}
            try { if (preparedStatement != null) preparedStatement.close();} catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
        return Optional.empty();
    }

    @Override
    public Collection<Question> findAll() {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            preparedStatement = conn.prepareStatement(
                "SELECT * FROM Question");
            rs = preparedStatement.executeQuery();

            return getQuestions(rs);
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
    public int count() {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            preparedStatement = dataSource.getConnection().prepareStatement("SELECT COUNT(*) AS nbQuestions FROM Question");
            rs = preparedStatement.executeQuery();
            rs.next();
            return rs.getInt("nbQuestions");
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

    public Collection<Question> find(QuestionsQuery query) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            if(query.getAuthorUUID() != null) {
                preparedStatement = conn.prepareStatement(
                    "SELECT * FROM Question WHERE person_uuid=? ORDER BY created_on DESC");
                preparedStatement.setString(1, query.getAuthorUUID().asString());
            } else {
                preparedStatement = conn.prepareStatement(
                    "SELECT * FROM Question ORDER BY created_on DESC");
            }
            rs = preparedStatement.executeQuery();

            return getQuestions(rs);

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
    public Collection<Question> getQuestionsPagination(int currentPage, int recordsPerPage) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            int start = currentPage * recordsPerPage - recordsPerPage;

            preparedStatement = conn.prepareStatement(
                "SELECT * FROM Question ORDER BY created_on DESC LIMIT ?, ?");
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, recordsPerPage);

            rs = preparedStatement.executeQuery();

            return getQuestions(rs);

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
