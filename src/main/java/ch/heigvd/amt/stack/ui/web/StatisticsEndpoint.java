package ch.heigvd.amt.stack.ui.web;

import ch.heigvd.amt.stack.application.ServiceRegistry;
import ch.heigvd.amt.stack.application.gamification.LeaderboardEntriesDTO;
import ch.heigvd.amt.stack.application.gamification.LeaderboardsDTO;
import ch.heigvd.amt.stack.application.gamification.PointScalesDTO;
import ch.heigvd.amt.stack.application.identitymgmt.IdentityManagementFacade;
import ch.heigvd.amt.stack.application.identitymgmt.PublicUserDTO;
import ch.heigvd.amt.stack.application.question.QuestionFacade;
import ch.heigvd.amt.stack.application.question.answer.AnswerFacade;
import ch.heigvd.amt.stack.domain.person.PersonId;
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

@WebServlet(name = "StatisticsEndpoint", urlPatterns = "/statistics")
public class StatisticsEndpoint extends HttpServlet {
    @Inject
    ServiceRegistry serviceRegistry;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IdentityManagementFacade identityManagementFacade = serviceRegistry.getIdentityManagementFacade();
        QuestionFacade questionFacade = serviceRegistry.getQuestionFacade();
        AnswerFacade answerFacade = serviceRegistry.getAnswerFacade();

        int usersCount = identityManagementFacade.countUsers();
        int questionsCount = questionFacade.countQuestions();
        int answersCount = answerFacade.countAnswers();

        request.setAttribute("usersCount", usersCount);
        request.setAttribute("questionsCount", questionsCount);
        request.setAttribute("answersCount", answersCount);

        // Leaderboards
        LeaderboardsDTO leaderboards = null;
        List<LeaderboardsDTO.LeaderboardDTO> leaderboardList = new ArrayList<>();
        final int LIMIT = 5;    // Used to define how many people to show on leaderboard

        // Obtenir toutes les pointScales existantes
        JSONArray JpointScales = serviceRegistry.getGamificationFacade().getPointScales();

        if(JpointScales != null) {
            List<PointScalesDTO.PointScaleDTO> pointScaleList = new ArrayList<>();
            for(int i = 0; i < JpointScales.length(); ++i) {
                pointScaleList.add(PointScalesDTO.PointScaleDTO.builder()
                    .pointScaleName((String) JpointScales.getJSONObject(i).get("name"))
                    .pointScaleDesc((String) JpointScales.getJSONObject(i).get("description"))
                    .build());
            }

            // Obtenir toutes les lignes de chaque pointScale
            for(PointScalesDTO.PointScaleDTO ps : pointScaleList) {
                JSONArray Jleaders = serviceRegistry.getGamificationFacade().getLeaderboard(ps.getPointScaleName(), LIMIT);

                if(Jleaders != null) {
                    List<LeaderboardEntriesDTO.LeaderboardEntryDTO> leaderboardEntryList = new ArrayList<>();
                    for(int i = 0; i < Jleaders.length(); ++i) {
                        PublicUserDTO user = identityManagementFacade.getPublicUser(new PersonId((String) Jleaders.getJSONObject(i).get("appUserId")));

                        if(user != null) {
                            leaderboardEntryList.add(LeaderboardEntriesDTO.LeaderboardEntryDTO.builder()
                                .username(user.getUsername())
                                .pointsSum((Integer) Jleaders.getJSONObject(i).get("pointsSum"))
                                .build());
                        }
                    }

                    LeaderboardEntriesDTO leaderboardEntries = LeaderboardEntriesDTO.builder()
                        .leaderboardEntries(leaderboardEntryList)
                        .build();

                    // Mettre ensemble la pointScale et les lignes de la table
                    leaderboardList.add(LeaderboardsDTO.LeaderboardDTO.builder()
                        .pointScale(ps)
                        .leaderboardEntries(leaderboardEntries)
                        .build());

                    leaderboards = LeaderboardsDTO.builder()
                        .leaderboards(leaderboardList)
                        .build();
                }
            }

            request.setAttribute("leaderboards", leaderboards);
        }

        request.getRequestDispatcher("/WEB-INF/views/statistics.jsp").forward(request, response);
    }
}
