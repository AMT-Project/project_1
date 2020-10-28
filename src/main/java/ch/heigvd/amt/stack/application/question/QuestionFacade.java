package ch.heigvd.amt.stack.application.question;

import ch.heigvd.amt.stack.application.question.answer.AnswerFacade;
import ch.heigvd.amt.stack.application.question.answer.AnswersQuery;
import ch.heigvd.amt.stack.application.question.comment.CommentFacade;
import ch.heigvd.amt.stack.application.question.vote.VoteCommand;
import ch.heigvd.amt.stack.application.question.vote.VoteFacade;
import ch.heigvd.amt.stack.application.question.vote.VotesQuery;
import ch.heigvd.amt.stack.domain.person.IPersonRepository;
import ch.heigvd.amt.stack.domain.person.Person;
import ch.heigvd.amt.stack.domain.question.IQuestionRepository;
import ch.heigvd.amt.stack.domain.question.Question;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionFacade {
    private IQuestionRepository questionRepository;
    private IPersonRepository personRepository;
    private CommentFacade commentFacade;
    private AnswerFacade answerFacade;
    private VoteFacade voteFacade;

    public QuestionFacade(IQuestionRepository questionRepository, IPersonRepository personRepository, CommentFacade commentFacade, AnswerFacade answerFacade, VoteFacade voteFacade) {
        this.questionRepository = questionRepository;
        this.personRepository = personRepository;
        this.commentFacade = commentFacade;
        this.answerFacade = answerFacade;
        this.voteFacade = voteFacade;
    }

    public void registerQuestion(SubmitQuestionCommand command) {
        try {
            Question submittedQuestion = Question.builder()
                .title(command.getTitle())
                .description(command.getText())
                .authorUUID(command.getAuthorUUID())
                .build();
            questionRepository.save(submittedQuestion);
        } catch(Exception e) {
            System.out.println("QuestionFacade error: " + e.toString());
        }
    }

    public QuestionsDTO getQuestionsPagination(int currentPage, int recordsPerPage) {
        Collection<Question> allQuestions = questionRepository.getQuestionsPagination(currentPage, recordsPerPage);

        List<QuestionsDTO.QuestionDTO> allQuestionsDTO = allQuestions.stream().map(question -> {
            Person author = personRepository.findById(question.getAuthorUUID()).get();

            return QuestionsDTO.QuestionDTO.builder()
                .uuid(question.getUuid())
                .title(question.getTitle())
                .description(question.getDescription())
                .authorUUID(author.getUuid())
                .username(author.getUsername())
                .createdOn(question.getCreatedOn())
                .build();
        })
            .collect(Collectors.toList());

        return QuestionsDTO.builder()
            .questions(allQuestionsDTO)
            .build();
    }

    // TODO : Devenu inutile à cause de la pagination? Voir fonction getQuestionsPagination ci-dessus
    public QuestionsDTO getQuestions(QuestionsQuery query) {
        Collection<Question> allQuestions = questionRepository.find(query);

        List<QuestionsDTO.QuestionDTO> allQuestionsDTO = allQuestions.stream().map(question -> {
            Person author = personRepository.findById(question.getAuthorUUID()).get();

            return QuestionsDTO.QuestionDTO.builder()
                .uuid(question.getUuid())
                .title(question.getTitle())
                .description(question.getDescription())
                .authorUUID(author.getUuid())
                .username(author.getUsername())
                .createdOn(question.getCreatedOn())
                .build();
        })
            .collect(Collectors.toList());

        return QuestionsDTO.builder()
            .questions(allQuestionsDTO)
            .build();
    }

    public QuestionsDTO.QuestionDTO getQuestion(int currentPage, int recordsPerPage, GetQuestionQuery query) {
        Question question = questionRepository.findById(query.getUuid()).orElse(null);
        Person author = personRepository.findById(question.getAuthorUUID()).get();

        return QuestionsDTO.QuestionDTO.builder()
            .uuid(question.getUuid())
            .title(question.getTitle())
            .description(question.getDescription())
            .authorUUID(author.getUuid())
            .username(author.getUsername())
            .createdOn(question.getCreatedOn())
            .comments(commentFacade.getQuestionComments(question.getUuid()))
            .answers(answerFacade.getAnswersPagination(currentPage, recordsPerPage, question.getUuid()))
            .votes(voteFacade.getVotes(VoteCommand.builder()
                .questionUUID(query.getUuid())
                .build()))
            .build();
    }

    public int countQuestions() {
        return this.questionRepository.count();
    }
}
