package ch.heigvd.amt.stack.application.question.comment;

import ch.heigvd.amt.stack.domain.person.IPersonRepository;
import ch.heigvd.amt.stack.domain.person.Person;
import ch.heigvd.amt.stack.domain.question.IQuestionRepository;
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
        System.out.println(" ------ ICI : -------" + command.getAnswerUUID());
        try {
            if(command.getQuestionUUID() != null){
                Comment comment = Comment.builder()
                        .personUUID(command.getAuthorUUID())
                        .questionUUID(command.getQuestionUUID())
                        .content(command.getContent())
                        .build();
                commentRepository.save(comment);
            } else if (command.getAnswerUUID() != null){
                Comment comment = Comment.builder()
                        .personUUID(command.getAuthorUUID())
                        .answerUUID(command.getAnswerUUID())
                        .content(command.getContent())
                        .build();
                commentRepository.save(comment);
            }

        } catch(Exception e) {
            System.out.println("CommentFacade error: " + e.toString());
        }
    }

    public CommentsDTO getComments(CommentsQuery query) {
        Collection<Comment> allComments = commentRepository.find(query);

        List<CommentsDTO.CommentDTO> allCommentsDTO = allComments.stream().map(comment -> {
            Person author = personRepository.findById(comment.getPersonUUID()).get();

            return CommentsDTO.CommentDTO.builder()
                    .authorUUID(author.getId())
                    .questionId(comment.getQuestionUUID())
                    .answerId(comment.getAnswerUUID())
                    .content(comment.getContent())
                    .build();
        })
                .collect(Collectors.toList());

        return CommentsDTO.builder()
                .comments(allCommentsDTO)
                .build();
    }

    public int countAnswers() {
        return this.commentRepository.count();
    }
}


