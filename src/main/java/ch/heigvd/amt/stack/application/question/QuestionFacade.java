package ch.heigvd.amt.stack.application.question;


import ch.heigvd.amt.stack.domain.question.IQuestionRepository;
import ch.heigvd.amt.stack.domain.question.Question;

import java.util.Collection;
import java.util.List;

public class QuestionFacade {
    private IQuestionRepository questionRepository;

    public QuestionFacade(IQuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public void registerQuestion(SubmitQuestionCommand command) {

        try{
            Question submittedQuestion = Question.builder()
                    .author(command.getAuthor())
                    .title(command.getText())
                    .description(command.getText())
                    .build();
            questionRepository.save(submittedQuestion);
        } catch(Exception e) {
            System.out.println("QuestionFacade error");
        }

    }

    public QuestionsDTO getQuestions(QuestionsQuery query){
      /*  Collection<Question> allQuestions = questionRepository.find(query);

        List<QuestionsDTO.QuestionDTO> allQuestionsDTO = allQuestions.stream()
                .map(question -> QuestionsDTO.QuestionDTO.builder()
                .text(question:))

       */
      return null;
    }

}