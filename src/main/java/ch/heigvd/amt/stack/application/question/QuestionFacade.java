package ch.heigvd.amt.stack.application.question;


import ch.heigvd.amt.stack.domain.question.IQuestionRepository;
import ch.heigvd.amt.stack.domain.question.Question;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionFacade {
    private IQuestionRepository questionRepository;

    public QuestionFacade(IQuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public void registerQuestion(SubmitQuestionCommand command) {

        try{
            Question submittedQuestion = Question.builder()
//                    .author(command.getAuthor())
//                    .title(command.getTitle())
//                    .description(command.getText())
                    .author("testau")
                    .title("testit")
                    .description("testdesc")
                    .build();
            questionRepository.save(submittedQuestion);
        } catch(Exception e) {
            System.out.println("QuestionFacade error: "  + e.toString());
        }

    }

    public QuestionsDTO getQuestions(QuestionsQuery query){
        Collection<Question> allQuestions = questionRepository.find(query);

        List<QuestionsDTO.QuestionDTO> allQuestionsDTO = allQuestions.stream()
                .map(question -> QuestionsDTO.QuestionDTO.builder()
                        .author(question.getAuthor())
                        .text(question.getDescription())
                        .build())
                .collect(Collectors.toList());


        return QuestionsDTO.builder()
                .questions(allQuestionsDTO)
                .build();
    }

}