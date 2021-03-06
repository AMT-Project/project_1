package ch.heigvd.amt.stack.infrastructure.persistence.jdbc;

import ch.heigvd.amt.stack.domain.person.PersonId;
import ch.heigvd.amt.stack.domain.question.QuestionId;
import ch.heigvd.amt.stack.domain.question.answer.AnswerId;
import ch.heigvd.amt.stack.domain.question.comment.Comment;
import ch.heigvd.amt.stack.domain.question.comment.CommentId;
import ch.heigvd.amt.stack.domain.question.comment.ICommentRepository;

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
@Named("JdbcCommentRepository")
public class JdbcCommentRepository extends JdbcRepository<Comment, CommentId> implements ICommentRepository {
    @Resource(lookup = "jdbc/StackDS")
    DataSource dataSource;

    @Override
    public void save(Comment comment) throws SQLIntegrityConstraintViolationException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = dataSource.getConnection();
            if(comment.getQuestionUUID() != null) {
                 preparedStatement =conn.prepareStatement(
                    "INSERT INTO Comment (uuid, question_uuid, person_uuid, content, created_on)" +
                        "VALUES (?,?,?,?,?)");
                preparedStatement.setString(1, comment.getUuid().asString());
                preparedStatement.setString(2, comment.getQuestionUUID().asString());
                preparedStatement.setString(3, comment.getAuthorUUID().asString());
                preparedStatement.setString(4, comment.getContent());

                Date date = new Date(System.currentTimeMillis());
                preparedStatement.setTimestamp(5, new Timestamp(date.getTime()));

                preparedStatement.executeUpdate();
            } else if(comment.getAnswerUUID() != null) {
                 preparedStatement = conn.prepareStatement(
                    "INSERT INTO Comment (uuid, answer_uuid, person_uuid, content, created_on)" +
                        "VALUES (?,?,?,?,?)");
                preparedStatement.setString(1, comment.getUuid().asString());
                preparedStatement.setString(2, comment.getAnswerUUID().asString());
                preparedStatement.setString(3, comment.getAuthorUUID().asString());
                preparedStatement.setString(4, comment.getContent());

                Date date = new Date(System.currentTimeMillis());
                preparedStatement.setTimestamp(5, new Timestamp(date.getTime()));

                preparedStatement.executeUpdate();
            }
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try { if (preparedStatement != null) preparedStatement.close();} catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }

    @Override
    public Collection<Comment> findQuestionComments(QuestionId questionUUID) {
        LinkedList<Comment> questionComments = new LinkedList<>();
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            preparedStatement = conn.prepareStatement(
                "SELECT * FROM Comment WHERE question_uuid=? ORDER BY created_on ASC");
            preparedStatement.setString(1, questionUUID.asString());

            rs = preparedStatement.executeQuery();
            while(rs.next())
                questionComments.add(commentBuilder(rs, new QuestionId(rs.getString("question_uuid")), null));

        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try { if (rs != null) rs.close();} catch (Exception e) {}
            try { if (preparedStatement != null) preparedStatement.close();} catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
        return questionComments;
    }

    @Override
    public Collection<Comment> findAnswerComments(AnswerId answerUUID) {
        LinkedList<Comment> answerComments = new LinkedList<>();
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            preparedStatement = conn.prepareStatement(
                "SELECT * FROM Comment WHERE answer_uuid=? ORDER BY created_on ASC");
            preparedStatement.setString(1, answerUUID.asString());

            rs = preparedStatement.executeQuery();
            while(rs.next())
                answerComments.add(commentBuilder(rs, null, new AnswerId(rs.getString("answer_uuid"))));

        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try { if (rs != null) rs.close();} catch (Exception e) {}
            try { if (preparedStatement != null) preparedStatement.close();} catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
        return answerComments;
    }

    private Comment commentBuilder(ResultSet res, QuestionId qid, AnswerId aid) throws SQLException {
        return Comment.builder()
            .uuid(new CommentId(res.getString("uuid")))
            .authorUUID(new PersonId(res.getString("person_uuid")))
            .content(res.getString("content"))
            .questionUUID(qid)
            .answerUUID(aid)
            .createdOn(LocalDateTime.parse(res.getString("created_on"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
            .build();
    }

    @Override
    public void remove(CommentId uuid) {

    }

    @Override
    public Optional<Comment> findById(CommentId uuid) {
        return Optional.empty();
    }

    @Override
    public Collection<Comment> findAll() {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }
}
