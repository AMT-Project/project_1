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

    public void giveAnswer(AnswerCommand command) {
        try {
            Answer answer = Answer.builder()
                    .personUUID(command.getAuthorUUID())
                    .questionUUID(command.getQuestionUUID())
                    .content(command.getContent())
                    .build();
            answerRepository.save(answer);
        } catch(Exception e) {
            System.out.println("AnswerFacade error: " + e.toString());
        }
    }

    public AnswersDTO getAnswers(AnswersQuery query) {
        Collection<Answer> allAnswers = answerRepository.find(query);

        List<AnswersDTO.AnswerDTO> allAnswersDTO = allAnswers.stream().map(answer -> {
            Person author = personRepository.findById(answer.getPersonUUID()).get();

            return AnswersDTO.AnswerDTO.builder()
                    .authorUUID(author.getId())
                    .questionId(answer.getQuestionUUID())
                    .content(answer.getContent())
                    .build();
        })
                .collect(Collectors.toList());

        return AnswersDTO.builder()
                .answers(allAnswersDTO)
                .build();
    }

}

