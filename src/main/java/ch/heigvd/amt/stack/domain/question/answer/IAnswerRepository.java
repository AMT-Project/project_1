package ch.heigvd.amt.stack.domain.question.answer;

import ch.heigvd.amt.stack.application.question.answer.AnswersQuery;
import ch.heigvd.amt.stack.domain.IRepository;

import java.util.Collection;

public interface IAnswerRepository extends IRepository<Answer,AnswerId> {
    public Collection<Answer> find(AnswersQuery query);
}
