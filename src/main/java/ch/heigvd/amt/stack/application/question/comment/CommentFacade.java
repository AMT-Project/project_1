package ch.heigvd.amt.stack.application.question.comment;

import ch.heigvd.amt.stack.domain.person.IPersonRepository;
import ch.heigvd.amt.stack.domain.person.Person;
import ch.heigvd.amt.stack.domain.question.IQuestionRepository;
import ch.heigvd.amt.stack.domain.question.QuestionId;
import ch.heigvd.amt.stack.domain.question.answer.AnswerId;
import ch.heigvd.amt.stack.domain.question.answer.IAnswerRepository;
import ch.heigvd.amt.stack.domain.question.comment.Comment;
import ch.heigvd.amt.stack.domain.question.comment.ICommentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CommentFacade {
    private ICommentRepository commentRepository;
    private IAnswerRepository answerRepository;
    private IQuestionRepository questionRepository;
    private IPersonRepository personRepository;

    public CommentFacade(ICommentRepository commentRepository, IAnswerRepository answerRepository, IQuestionRepository questionRepository, IPersonRepository personRepository) {
        this.commentRepository = commentRepository;
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.personRepository = personRepository;
    }

    public void registerComment(CommentCommand command) {
        try {
            if(command.getQuestionUUID() != null) {
                Comment comment = Comment.builder()
                    .authorUUID(command.getAuthorUUID())
                    .questionUUID(command.getQuestionUUID())
                    .content(command.getContent())
                    .build();
                commentRepository.save(comment);
            } else if(command.getAnswerUUID() != null) {
                Comment comment = Comment.builder()
                    .authorUUID(command.getAuthorUUID())
                    .answerUUID(command.getAnswerUUID())
                    .content(command.getContent())
                    .build();
                commentRepository.save(comment);
            }

        } catch(Exception e) {
            System.out.println("CommentFacade error: " + e.toString());
        }
    }

    public CommentsDTO getQuestionComments(QuestionId questionUUID) {
        return CommentsDTO.builder()
            .comments(commentDTOListBuilder(commentRepository.findQuestionComments(questionUUID)))
            .build();
    }

    public CommentsDTO getAnswerComments(AnswerId answerUUID) {
        return CommentsDTO.builder()
            .comments(commentDTOListBuilder(commentRepository.findAnswerComments(answerUUID)))
            .build();
    }

    private List<CommentsDTO.CommentDTO> commentDTOListBuilder(Collection<Comment> comments) {

        return comments.stream().map(comment -> {
                Person author = personRepository.findById(comment.getAuthorUUID()).get();

                return CommentsDTO.CommentDTO.builder()
                    .uuid(comment.getUuid())
                    .authorUUID(author.getUuid())
                    .username(author.getUsername())
                    .content(comment.getContent())
                    .createdOn(comment.getCreatedOn())
                    .build();
            }
        ).collect(Collectors.toList());
    }

    public int countAnswers() {
        return this.commentRepository.count();
    }
}


