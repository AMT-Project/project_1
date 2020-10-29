package ch.heigvd.amt.stack.infrastructure.persistence.jdbc;

import ch.heigvd.amt.stack.application.question.vote.VoteCommand;
import ch.heigvd.amt.stack.application.question.vote.VotesQuery;
import ch.heigvd.amt.stack.domain.person.PersonId;
import ch.heigvd.amt.stack.domain.question.Question;
import ch.heigvd.amt.stack.domain.question.QuestionId;
import ch.heigvd.amt.stack.domain.question.answer.Answer;
import ch.heigvd.amt.stack.domain.question.answer.AnswerId;
import ch.heigvd.amt.stack.domain.question.vote.IVoteRepository;
import ch.heigvd.amt.stack.domain.question.vote.Vote;
import ch.heigvd.amt.stack.domain.question.vote.VoteId;

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
@Named("JdbcVoteRepository")
public class JdbcVoteRepository implements IVoteRepository {
    @Resource(lookup = "jdbc/StackDS")
    DataSource dataSource;

    @Override
    public Optional<Vote> findExistingVotes(VotesQuery query) {
        Collection<Vote> existingVotes = find(query);

        if(existingVotes.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(existingVotes.iterator().next());
    }

    @Override
    public Collection<Vote> find(VotesQuery query) {
        LinkedList<Vote> matches = new LinkedList<>();

        try {
            PreparedStatement preparedStatement = null;
            ResultSet rs;
            if(query.getAuthorUUID() == null) {
                if(query.getAnswerUUID() != null) {
                    preparedStatement = dataSource.getConnection().prepareStatement(
                        "SELECT * FROM Vote WHERE answer_uuid=?");
                    preparedStatement.setString(1, query.getAnswerUUID().asString());
                } else if(query.getQuestionUUID() != null) {
                    preparedStatement = dataSource.getConnection().prepareStatement(
                        "SELECT * FROM Vote WHERE question_uuid=?");
                    preparedStatement.setString(1, query.getQuestionUUID().asString());
                }
            } else {
                if(query.getAnswerUUID() != null) {
                    preparedStatement = dataSource.getConnection().prepareStatement(
                        "SELECT * FROM Vote WHERE answer_uuid=? AND person_uuid=?");
                    preparedStatement.setString(1, query.getAnswerUUID().asString());
                    preparedStatement.setString(2, query.getAuthorUUID().asString());
                } else if(query.getQuestionUUID() != null) {
                    preparedStatement = dataSource.getConnection().prepareStatement(
                        "SELECT * FROM Vote WHERE question_uuid=? AND person_uuid=?");
                    preparedStatement.setString(1, query.getQuestionUUID().asString());
                    preparedStatement.setString(2, query.getAuthorUUID().asString());
                }
            }
            if(preparedStatement != null) {
                rs = preparedStatement.executeQuery();
            } else {
                return null;
            }

            while(rs.next()) {
                QuestionId questionId = null;
                if(rs.getString("question_uuid") != null) {
                    questionId = new QuestionId(rs.getString("question_uuid"));

                    matches.add(Vote.builder()
                        .uuid(new VoteId(rs.getString("uuid")))
                        .isUpvote(rs.getBoolean("is_upvote"))
                        .questionUUID(questionId)
                        .authorUUID(new PersonId(rs.getString("person_uuid")))
                        .build());
                }

                AnswerId answerId = null;
                if(rs.getString("answer_uuid") != null) {
                    answerId = new AnswerId(rs.getString("answer_uuid"));

                    matches.add(Vote.builder()
                        .uuid(new VoteId(rs.getString("uuid")))
                        .isUpvote(rs.getBoolean("is_upvote"))
                        .authorUUID(new PersonId(rs.getString("person_uuid")))
                        .answerUUID(answerId)
                        .build());
                }
            }
            return matches;

        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    @Override
    public void save(Vote entity) {
        try {
            if(entity.getQuestionUUID() != null) {
                PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(
                    "INSERT INTO Vote (uuid, is_upvote, question_uuid, person_uuid, answer_uuid)" +
                        "VALUES (?,?,?,?,?)");
                preparedStatement.setString(1, entity.getUuid().asString());
                preparedStatement.setBoolean(2, entity.isUpvote());
                preparedStatement.setString(3, entity.getQuestionUUID().asString());
                preparedStatement.setString(4, entity.getAuthorUUID().asString());
                preparedStatement.setString(5, null);

                preparedStatement.executeUpdate();
            } else if(entity.getAnswerUUID() != null) {
                PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(
                    "INSERT INTO Vote (uuid, is_upvote, answer_uuid, person_uuid, question_uuid)" +
                        "VALUES (?,?,?,?,?)");
                preparedStatement.setString(1, entity.getUuid().asString());
                preparedStatement.setBoolean(2, entity.isUpvote());
                preparedStatement.setString(3, entity.getAnswerUUID().asString());
                preparedStatement.setString(4, entity.getAuthorUUID().asString());
                preparedStatement.setString(5, null);
                preparedStatement.executeUpdate();
            }
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void remove(VoteId id) {
        try {
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(
                "DELETE FROM Vote WHERE uuid=?");
            preparedStatement.setString(1, id.asString());
            preparedStatement.executeUpdate();
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void changeVote(VoteId id) {
        try {
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(
                "UPDATE Vote SET is_upvote = NOT is_upvote WHERE uuid=?");
            preparedStatement.setString(1, id.asString());
            preparedStatement.executeUpdate();
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // TODO : implement all below

    @Override
    public Optional<Vote> findById(VoteId id) {
        return Optional.empty();
    }

    @Override
    public Collection<Vote> findAll() {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public int countVotes(VotesQuery query) {
        try {
            if(query.getQuestionUUID() != null) {
                PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement("SELECT SUM(is_upvote * 2 - 1) AS nbVotes FROM Vote WHERE question_uuid=?");
                preparedStatement.setString(1, query.getQuestionUUID().asString());
                ResultSet rs = preparedStatement.executeQuery();
                rs.next();
                return rs.getInt("nbVotes");
            } else if(query.getAnswerUUID() != null) {
                PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement("SELECT SUM(is_upvote * 2 - 1) AS nbVotes FROM Vote WHERE answer_uuid=?");
                preparedStatement.setString(1, query.getAnswerUUID().asString());
                ResultSet rs = preparedStatement.executeQuery();
                rs.next();
                return rs.getInt("nbVotes");
            }
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}
