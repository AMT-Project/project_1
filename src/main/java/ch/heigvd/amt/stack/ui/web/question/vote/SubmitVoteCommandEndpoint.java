package ch.heigvd.amt.stack.ui.web.question.vote;

import ch.heigvd.amt.stack.application.ServiceRegistry;
import ch.heigvd.amt.stack.application.gamification.GamificationFacade;
import ch.heigvd.amt.stack.application.identitymgmt.authenticate.CurrentUserDTO;
import ch.heigvd.amt.stack.application.question.vote.VoteCommand;
import ch.heigvd.amt.stack.application.question.vote.VoteFacade;
import ch.heigvd.amt.stack.domain.question.QuestionId;
import ch.heigvd.amt.stack.domain.question.answer.AnswerId;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "VoteCommandEndpoint", urlPatterns = "/vote.do")
public class SubmitVoteCommandEndpoint extends HttpServlet {
    @Inject
    ServiceRegistry serviceRegistry;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VoteFacade voteFacade = serviceRegistry.getVoteFacade();
        GamificationFacade gamificationFacade = serviceRegistry.getGamificationFacade();

        CurrentUserDTO currentUserDTO = (CurrentUserDTO) request.getSession().getAttribute("currentUser");

        // If it's a vote on a question
        if(request.getParameter("questionUuid") != null) {
            VoteCommand command = VoteCommand.builder()
                .authorUUID(currentUserDTO.getUuid())
                .questionUUID(new QuestionId(request.getParameter("questionUuid")))
                .isUpvote(request.getParameter("voteType").equals("up"))
                .build();
            try {
                voteFacade.registerVote(command);
                // FIXME upvoting several times causes several participation events
                gamificationFacade.postParticipationEvent(currentUserDTO.getUuid(), "vote");
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        // If it's a vote on an answer
        if(request.getParameter("answerUuid") != null) {
            VoteCommand command = VoteCommand.builder()
                .authorUUID(currentUserDTO.getUuid())
                .answerUUID(new AnswerId(request.getParameter("answerUuid")))
                .isUpvote(request.getParameter("voteType").equals("up"))
                .build();
            try {
                voteFacade.registerVote(command);
                // FIXME upvoting several times causes several participation events
                gamificationFacade.postParticipationEvent(currentUserDTO.getUuid(), "vote");
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect(request.getContextPath() + "/question?uuid=" + request.getParameter("redirectUuid"));
    }
}
