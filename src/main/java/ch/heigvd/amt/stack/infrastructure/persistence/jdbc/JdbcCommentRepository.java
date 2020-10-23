package ch.heigvd.amt.stack.infrastructure.persistence.jdbc;

import ch.heigvd.amt.stack.application.question.comment.CommentsQuery;
import ch.heigvd.amt.stack.application.question.comment.GetCommentQuery;
import ch.heigvd.amt.stack.domain.person.PersonId;
import ch.heigvd.amt.stack.domain.question.Question;
import ch.heigvd.amt.stack.domain.question.QuestionId;
import ch.heigvd.amt.stack.domain.question.answer.Answer;
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
        try {
            if(comment.getQuestionUUID() != null) {
                PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(
                    "INSERT INTO Comment (uuid, question_uuid, person_uuid, content, created_on)" +
                        "VALUES (?,?,?,?,?)");
                preparedStatement.setString(1, comment.getId().asString());
                preparedStatement.setString(2, comment.getQuestionUUID().asString());
                preparedStatement.setString(3, comment.getPersonUUID().asString());
                preparedStatement.setString(4, comment.getContent());
                Date date = new Date(System.currentTimeMillis());
                preparedStatement.setTimestamp(5, new Timestamp(date.getTime()));
                preparedStatement.executeUpdate();
            } else if(comment.getAnswerUUID() != null) {
                PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(
                    "INSERT INTO Comment (uuid, answer_uuid, person_uuid, content, created_on)" +
                        "VALUES (?,?,?,?,?)");
                preparedStatement.setString(1, comment.getId().asString());
                preparedStatement.setString(2, comment.getAnswerUUID().asString());
                preparedStatement.setString(3, comment.getPersonUUID().asString());
                preparedStatement.setString(4, comment.getContent());
                preparedStatement.setDate(5, new Date(System.currentTimeMillis()));
                preparedStatement.executeUpdate();
            }
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // TODO : implement
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

    private Comment commentBuilder(ResultSet res, QuestionId qid, AnswerId aid) throws SQLException {
        return Comment.builder()
            .id(new CommentId(res.getString("uuid")))
            .personUUID(new PersonId(res.getString("person_uuid")))
            .content(res.getString("content"))
            .questionUUID(qid)
            .answerUUID(aid)
            .createdOn(LocalDateTime.parse(res.getString("created_on"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
            .build();
    }

    @Override
    public Collection<Comment> findQuestionComments(QuestionId questionUUID) {
        LinkedList<Comment> questionComments = new LinkedList<>();
        ResultSet rs = null;

        try {
            PreparedStatement preparedStatement;
            preparedStatement = dataSource.getConnection().prepareStatement(
                "SELECT * FROM Comment WHERE question_uuid=?");
            preparedStatement.setString(1, questionUUID.asString());

            rs = preparedStatement.executeQuery();
            while(rs.next())
                questionComments.add(commentBuilder(rs, new QuestionId(rs.getString("question_uuid")), null));

        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        return questionComments;
    }

    @Override
    public Collection<Comment> findAnswerComments(AnswerId answerUUID) {
        LinkedList<Comment> answerComments = new LinkedList<>();
        ResultSet rs = null;

        try {
            PreparedStatement preparedStatement;
            preparedStatement = dataSource.getConnection().prepareStatement(
                "SELECT * FROM Comment WHERE answer_uuid=?");
            preparedStatement.setString(1, answerUUID.asString());

            rs = preparedStatement.executeQuery();
            while(rs.next())
                answerComments.add(commentBuilder(rs, null, new AnswerId(rs.getString("answer_uuid"))));

        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        return answerComments;
    }
}
