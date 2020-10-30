package ch.heigvd.amt.stack.domain.question.answer;

import ch.heigvd.amt.stack.application.question.answer.AnswersQuery;
import ch.heigvd.amt.stack.domain.IRepository;
import ch.heigvd.amt.stack.domain.question.QuestionId;

import java.util.Collection;

public interface IAnswerRepository extends IRepository<Answer,AnswerId> {
    public Collection<Answer> find(AnswersQuery query);

    public Collection<Answer> getAnswersPagination(int currentPage, int recordsPerPage, QuestionId id);
    public int countAnswersToQuestion(QuestionId questionId);
}
