Feature("Gamification");

const stackURL = "http://stack.ch:9080/stack";
const questionsPage = stackURL + "/questions";
const registerPage = stackURL + "/register";
const statisticsURL = stackURL + "/statistics";
const profilePage = stackURL + "/profile";

const uniqueId = new Date().getTime();
const firstName = "Codecept";
const lastName = "JS";
const uniqueUsername = "12gamification_test-" + uniqueId;
const uniqueEmail = "twelveprofile@" + uniqueId + ".ch";
const pwd = "passWord123";

const questionTitle = "I want to know";
const questionDescription = "How to gamify";

const answer = "Sorry I have no idea im just fishing for votes"
const comment = "THIS IS MY COMMENT"
const commentAns = "I don't know what to reply to this"

const locateLeaderBoardComment = locate(".leaderboard__item").withChild("h2").withText("Commentaires");
const locateLeaderBoardQuestion = locate(".leaderboard__item").withChild("h2").withText("Questions");
const locateLeaderBoardAnswer = locate(".leaderboard__item").withChild("h2").withText("Réponses");

const locatePointsScore = locate(".pointsscore__table--td");
const locateBadge = locate(".profile__stat__badge");

const leaderBoardUser = locate(".leaderboard__table--tr-td").withChild(".leaderboard__table--td").withText(uniqueUsername);


Scenario("Anonymous user can consult leaerboards", ({ I }) => {
    I.amOnPage(statisticsURL);
    I.seeElement(locateLeaderBoardQuestion);
    I.seeElement(locateLeaderBoardComment);
    I.seeElement(locateLeaderBoardAnswer);
});


Scenario("Stats updated", async({ I }) => {
    I.amOnPage(registerPage);
    I.register(uniqueUsername, firstName, lastName, uniqueEmail, pwd);

    //Badge and pointScale for question
    I.submitQuestion(questionTitle, questionDescription);

    I.amOnPage(profilePage);
    I.seeElement(locateBadge.withText("Besoin d'aide"));
    I.seeElement(locatePointsScore.withText("Questions"))
    I.seeElement(locate(".pointsscore__table--td").withText("1"));

    //Badge and pointScale for comment
    I.amOnPage(questionsPage);
    I.see(questionDescription);
    I.click(questionDescription);

    I.submitComment(comment);

    I.amOnPage(profilePage);
    I.seeElement(locateBadge.withText("First"));
    I.seeElement(locatePointsScore.withText("Commentaires"))

    // pointScale for answers
    I.amOnPage(questionsPage);
    I.see(questionDescription);
    I.click(questionDescription);
    I.submitAnswer(answer);

    I.amOnPage(questionsPage);
    I.see(questionDescription);
    I.click(questionDescription);
    I.submitAnswer(answer);

    I.submitCommentAnswer(commentAns);

    I.amOnPage(profilePage);
    I.seeElement(locatePointsScore.withText("Réponses"));
    I.seeElement(locate(".pointsscore__table--td").withText("2"));



    //Appear on leaderboard
    I.amOnPage(statisticsURL);
    I.seeElement(leaderBoardUser);

});