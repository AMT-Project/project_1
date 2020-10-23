package ch.heigvd.amt.stack.domain.question.comment;

import ch.heigvd.amt.stack.application.question.comment.CommentsQuery;
import ch.heigvd.amt.stack.domain.IRepository;

import java.util.Collection;

public interface ICommentRepository extends IRepository<Comment, CommentId> {
    public Collection<Comment> find(CommentsQuery query);
}
