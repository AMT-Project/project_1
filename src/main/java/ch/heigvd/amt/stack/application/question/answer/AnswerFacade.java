package ch.heigvd.amt.stack.application.question.answer;

import ch.heigvd.amt.stack.domain.question.answer.Answer;
import ch.heigvd.amt.stack.domain.question.answer.IAnswerRepository;
import ch.heigvd.amt.stack.domain.person.IPersonRepository;
import ch.heigvd.amt.stack.domain.person.Person;
import ch.heigvd.amt.stack.domain.question.IQuestionRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AnswerFacade {
    private IAnswerRepository answerRepository;
    private IQuestionRepository questionRepository;
    private IPersonRepository personRepository;

    public AnswerFacade(IAnswerRepository answerRepository, IQuestionRepository questionRepository, IPersonRepository personRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.personRepository = personRepository;
    }

    public void registerAnswer(AnswerCommand command) {
        try {
            Answer answer = Answer.builder()
                .content(command.getContent())
                .questionUUID(command.getQuestionUUID())
                .authorUUID(command.getAuthorUUID())
                .build();
            answerRepository.save(answer);
        } catch(Exception e) {
            System.out.println("AnswerFacade error: " + e.toString());
        }
    }

    public AnswersDTO getAnswers(AnswersQuery query) {
        Collection<Answer> allAnswers = answerRepository.find(query);

        List<AnswersDTO.AnswerDTO> allAnswersDTO = allAnswers.stream().map(answer -> {
            Person author = personRepository.findById(answer.getAuthorUUID()).get();

            return AnswersDTO.AnswerDTO.builder()
                .content(answer.getContent())
                .questionUUID(answer.getQuestionUUID())
                .authorUUID(author.getUuid())
                .username(author.getUsername())
                .createdOn(answer.getCreatedOn())
                .build();
        })
            .collect(Collectors.toList());

        return AnswersDTO.builder()
            .answers(allAnswersDTO)
            .build();
    }

    public int countAnswers() {
        return this.answerRepository.count();
    }
}

