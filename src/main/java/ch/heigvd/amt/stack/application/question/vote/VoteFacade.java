package ch.heigvd.amt.stack.application.question.vote;

import ch.heigvd.amt.stack.domain.question.vote.IVoteRepository;
import ch.heigvd.amt.stack.domain.question.vote.Vote;

public class VoteFacade {
    IVoteRepository voteRepository;

    public VoteFacade(IVoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public void registerVote(VoteCommand command) {
        // Verify if a user has already voted for the object
        Vote existingVote = voteRepository.findExistingVotes(VotesQuery.builder()
            .authorUUID(command.getAuthorUUID())
            .answerUUID(command.getAnswerUUID())
            .questionUUID(command.getQuestionUUID())
            .build()).orElse(null);

        // If user has already voted
        if(existingVote != null) {
            // If the vote is different than previously
            if(existingVote.isUpvote() != command.isUpvote()) {
                // Change the vote (upvote->downvote or downvote->upvote)
                voteRepository.changeVote(existingVote.getUuid());
            }
            else {
                voteRepository.remove(existingVote.getUuid());
            }
        }
        // Else user hasn't voted, register his vote
        else {
            try {
                Vote newVote = Vote.builder()
                    .authorUUID(command.getAuthorUUID())
                    .answerUUID(command.getAnswerUUID())
                    .questionUUID(command.getQuestionUUID())
                    .isUpvote(command.isUpvote())
                    .build();
                voteRepository.save(newVote);
            } catch(Exception e) {
                System.out.println("VoteFacade error: " + e.toString());
            }
        }
    }

    public VotesDTO getVotes(VoteCommand command) {
        return VotesDTO.builder()
            .count(voteRepository.countVotes(VotesQuery.builder()
                .answerUUID(command.getAnswerUUID())
                .questionUUID(command.getQuestionUUID())
                .build()))
            .build();
    }
}
