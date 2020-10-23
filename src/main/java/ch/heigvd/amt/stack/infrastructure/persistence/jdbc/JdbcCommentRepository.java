package ch.heigvd.amt.stack.infrastructure.persistence.jdbc;

import ch.heigvd.amt.stack.application.question.comment.CommentsQuery;
import ch.heigvd.amt.stack.domain.question.comment.Comment;
import ch.heigvd.amt.stack.domain.question.comment.CommentId;
import ch.heigvd.amt.stack.domain.question.comment.ICommentRepository;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
@Named("JdbcCommentRepository")
public class JdbcCommentRepository extends JdbcRepository<Comment, CommentId> implements ICommentRepository {
    @Resource(lookup = "jdbc/StackDS")
    DataSource dataSource;

    @Override
    public Collection<Comment> find(CommentsQuery query) {
        return null;
    }

    @Override
    public void save(Comment comment) throws SQLIntegrityConstraintViolationException {
        try {
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(
                    "INSERT INTO Comment (uuid, question_uuid, person_uuid, answer_uuid , content, created_at)" +
                            "VALUES (?,?,?,?,?,?)");
            preparedStatement.setString(1, comment.getId().asString());
            preparedStatement.setString(2, comment.getQuestionUUID().asString());
            preparedStatement.setString(3, comment.getPersonUUID().asString());
            preparedStatement.setString(4, comment.getAnswerUUID().asString());
            preparedStatement.setString(5, comment.getContent());
            preparedStatement.setDate(6, new Date(System.currentTimeMillis()));
            preparedStatement.executeUpdate();
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void remove(CommentId id) {

    }

    @Override
    public Optional<Comment> findById(CommentId id) {
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
