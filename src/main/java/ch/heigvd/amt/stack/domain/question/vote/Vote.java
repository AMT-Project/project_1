package ch.heigvd.amt.stack.domain.question.vote;

import ch.heigvd.amt.stack.domain.IEntity;
import ch.heigvd.amt.stack.domain.person.PersonId;
import ch.heigvd.amt.stack.domain.question.QuestionId;
import ch.heigvd.amt.stack.domain.question.answer.AnswerId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class Vote implements IEntity<Vote, VoteId> {
    private VoteId uuid;
    private boolean isUpvote;
    private AnswerId answerUUID;
    private QuestionId questionUUID;
    private PersonId authorUUID;

    @Override
    public Vote deepClone() {
        return this.toBuilder()
            .uuid(new VoteId(uuid.asString()))
            .build();
    }

    public static class VoteBuilder {
        public Vote build() {
            if(uuid == null) {
                uuid = new VoteId();
            }

            if(authorUUID == null) {
                throw new IllegalArgumentException("Author is mandatory");
            }

            return new Vote(uuid, isUpvote, answerUUID, questionUUID, authorUUID);
        }

    }
}
