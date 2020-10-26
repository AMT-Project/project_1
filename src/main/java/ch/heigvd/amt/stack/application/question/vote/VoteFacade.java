package ch.heigvd.amt.stack.application.question.vote;

import ch.heigvd.amt.stack.domain.question.comment.Comment;
import ch.heigvd.amt.stack.domain.question.vote.IVoteRepository;
import ch.heigvd.amt.stack.domain.question.vote.Vote;

public class VoteFacade {
    IVoteRepository voteRepository;

    public VoteFacade(IVoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public void registerVote(VoteCommand command) {
        // TODO
        // Vérifier si un vote existe déjà pour l'entité (question ou answer)
        // Si c'est le cas, ne rien faire
        // Sinon ajouter le vote

        // Ou alors vérifier si un vote existe déjà pour l'entité (question ou answer)
        // Si c'est le cas et que le vote est différent, UPDATE le vote enregistré
        // Sinon ne rien faire

        // Actuellement enregistre plusieurs votes sur le même objet par le même user
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

    public VotesDTO getVotes(VotesQuery query) {
        return VotesDTO.builder()
            .count(voteRepository.countQuestionVotes(VotesQuery.builder()
                .questionUUID(query.getQuestionUUID())
                .build()))
            .build();
    }
}
