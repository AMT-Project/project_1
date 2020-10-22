package ch.heigvd.amt.stack.application.question;

import ch.heigvd.amt.stack.domain.person.IPersonRepository;
import ch.heigvd.amt.stack.domain.person.Person;
import ch.heigvd.amt.stack.domain.question.IQuestionRepository;
import ch.heigvd.amt.stack.domain.question.Question;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionFacade {
    private IQuestionRepository questionRepository;
    private IPersonRepository personRepository;

    public QuestionFacade(IQuestionRepository questionRepository, IPersonRepository personRepository) {
        this.questionRepository = questionRepository;
        this.personRepository = personRepository;
    }

    public void registerQuestion(SubmitQuestionCommand command) {
        try {
            Question submittedQuestion = Question.builder()
                .authorUUID(command.getAuthorUUID())
                .title(command.getTitle())
                .description(command.getText())
                .createdOn(LocalDate.now())
                .build();
            questionRepository.save(submittedQuestion);
        } catch(Exception e) {
            System.out.println("QuestionFacade error: " + e.toString());
        }
    }

    public QuestionsDTO getQuestions(QuestionsQuery query) {
        Collection<Question> allQuestions = questionRepository.find(query);

        List<QuestionsDTO.QuestionDTO> allQuestionsDTO = allQuestions.stream().map(question -> {
            Person author = personRepository.findById(question.getAuthorUUID()).get();

            return QuestionsDTO.QuestionDTO.builder()
                .uuid(question.getId())
                .authorUUID(author.getId())
                .username(author.getUsername())
                .title(question.getTitle())
                .description(question.getDescription())
                .createdOn(question.getCreatedOn())
                .build();
        })
            .collect(Collectors.toList());

        return QuestionsDTO.builder()
            .questions(allQuestionsDTO)
            .build();
    }

    public QuestionsDTO.QuestionDTO getQuestion(GetQuestionQuery query) {
        Question question = questionRepository.findById(query.getUuid()).orElse(null);
        Person author = personRepository.findById(question.getAuthorUUID()).get();

        return QuestionsDTO.QuestionDTO.builder()
            .uuid(question.getId())
            .authorUUID(author.getId())
            .username(author.getUsername())
            .title(question.getTitle())
            .description(question.getDescription())
            .createdOn(question.getCreatedOn())
            .build();
    }

    public int countQuestions() {
        return this.questionRepository.count();
    }
}
