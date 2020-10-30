package ch.heigvd.amt.stack.domain.question.comment;

import ch.heigvd.amt.stack.domain.IRepository;
import ch.heigvd.amt.stack.domain.question.QuestionId;
import ch.heigvd.amt.stack.domain.question.answer.AnswerId;

import java.util.Collection;

public interface ICommentRepository extends IRepository<Comment, CommentId> {
    public Collection<Comment> findQuestionComments(QuestionId questionUUID);

    public Collection<Comment> findAnswerComments(AnswerId answerUUID);
}
