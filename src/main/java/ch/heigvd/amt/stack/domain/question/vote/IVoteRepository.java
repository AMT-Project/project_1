package ch.heigvd.amt.stack.domain.question.vote;

import ch.heigvd.amt.stack.application.question.vote.VotesQuery;
import ch.heigvd.amt.stack.domain.IRepository;

import java.util.Collection;
import java.util.Optional;

public interface IVoteRepository extends IRepository<Vote, VoteId> {
    Optional<Vote> findExistingQuestionVotes(VotesQuery query);
    //Optional<Vote> findExistingAnswerVotes(VotesQuery query);

    Collection<Vote> find(VotesQuery query);

    void changeVote(VoteId id);

    int countQuestionVotes(VotesQuery query);
}
