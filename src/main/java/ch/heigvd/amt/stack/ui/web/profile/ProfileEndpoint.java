package ch.heigvd.amt.stack.ui.web.profile;

import ch.heigvd.amt.stack.application.ServiceRegistry;
import ch.heigvd.amt.stack.application.gamification.BadgesDTO;
import ch.heigvd.amt.stack.application.gamification.PointsScoresDTO;
import ch.heigvd.amt.stack.application.identitymgmt.authenticate.CurrentUserDTO;
import ch.heigvd.amt.stack.application.question.QuestionFacade;
import ch.heigvd.amt.stack.application.question.QuestionsDTO;
import ch.heigvd.amt.stack.application.question.QuestionsQuery;
import ch.heigvd.amt.stack.application.question.answer.AnswerFacade;
import ch.heigvd.amt.stack.application.question.answer.AnswersDTO;
import ch.heigvd.amt.stack.application.question.answer.AnswersQuery;
import org.json.JSONArray;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProfileEndpoint", urlPatterns = "/profile")
public class ProfileEndpoint extends HttpServlet {
    @Inject
    ServiceRegistry serviceRegistry;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CurrentUserDTO currentUserDTO = (CurrentUserDTO) request.getSession().getAttribute("currentUser");
        QuestionFacade questionFacade = serviceRegistry.getQuestionFacade();
        AnswerFacade answerFacade = serviceRegistry.getAnswerFacade();

        QuestionsDTO userQuestions = questionFacade.getQuestions(QuestionsQuery.builder()
            .authorUUID(currentUserDTO.getUuid())
            .build());

        AnswersDTO userAnswers = answerFacade.getAnswers(AnswersQuery.builder()
            .authorUUID(currentUserDTO.getUuid())
            .build());

        request.setAttribute("user", currentUserDTO);
        request.setAttribute("userAnswersCount", userAnswers.getAnswers().size());
        request.setAttribute("userQuestionsCount", userQuestions.getQuestions().size());

        // Badges
        JSONArray Jbadges = serviceRegistry.getGamificationFacade().getUserBadges(currentUserDTO.getUuid().asString());
        if(Jbadges != null) {
            List<BadgesDTO.BadgeDTO> badgesList = new ArrayList<>();
            for(int i = 0; i < Jbadges.length(); ++i) {
                badgesList.add(BadgesDTO.BadgeDTO.builder()
                    .badgeName((String) Jbadges.getJSONObject(i).get("name"))
                    .badgeDesc((String) Jbadges.getJSONObject(i).get("description"))
                    .build());
            }

            BadgesDTO badges = BadgesDTO.builder()
                .badges(badgesList)
                .build();

            request.setAttribute("badges", badges);
        }

        // PointsScores
        JSONArray JpointsScores = serviceRegistry.getGamificationFacade().getUserPointsScores(currentUserDTO.getUuid().asString());
        if(JpointsScores != null) {
            List<PointsScoresDTO.PointsScoreDTO> pointsScoreList = new ArrayList<>();
            for(int i = 0; i < JpointsScores.length(); ++i) {
                pointsScoreList.add(PointsScoresDTO.PointsScoreDTO.builder()
                    .pointScaleName((String) JpointsScores.getJSONObject(i).get("pointScale"))
                    .score((Integer) JpointsScores.getJSONObject(i).get("score"))
                    .build());
            }

            PointsScoresDTO pointsScores = PointsScoresDTO.builder()
                .pointsScores(pointsScoreList)
                .build();

            request.setAttribute("pointsScores", pointsScores);
        }

        request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
    }
}