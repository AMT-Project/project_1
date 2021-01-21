Feature("Statistics");

const stackURL = "http://stack.ch:9080/stack";
const questionsPage = stackURL ; //+ "/questions";
const registerPage = stackURL + "/register";
const submitQuestionURL = stackURL + "/submitQuestion";
const statisticsURL = stackURL + "/statistics";

const uniqueId = new Date().getTime();
const firstName = "Codecept";
const lastName = "JS";
const uniqueUsername = "10statistics_test-" + uniqueId;
const uniqueEmail = "tenprofile@" + uniqueId + ".ch";
const pwd = "passWord123";

const questionTitle = "How to browse stack.ch?";
const questionDescription = "I am really lost there, any help?";

const answer = "Sorry I have no idea im just fishing for votes"
const comment = "THIS IS MY COMMENT"
const commentAns = "I don't know what to reply to this"

const locateStatUser = locate(".stat").withText("Registered users");
const locateStatQuestion = locate(".stat").withText("Questions asked");
const locateStatAnswer = locate(".stat").withText("Answers provided");
const locateNbUser = locate(".circle").inside(locateStatUser);
const locateNbQuestion = locate(".circle").inside(locateStatQuestion);
const locateNbAnswer = locate(".circle").inside(locateStatAnswer);

Scenario("Anonymous user can consult statistics", (I) => {
    I.amOnPage(statisticsURL);
    I.seeElement(locateStatUser);
    I.seeElement(locateStatQuestion);
    I.seeElement(locateStatAnswer);
});

Scenario("Stats updated", async(I) => {
    I.amOnPage(statisticsURL);
    I.seeElement(locateNbUser);
    const nbUser = await I.grabTextFrom(locateNbUser);
    const nbQuestion = await I.grabTextFrom(locateNbQuestion);
    const nbAnswer = await I.grabTextFrom(locateNbAnswer);

    I.see(nbUser.toString());
    I.see(nbQuestion.toString());
    I.see(nbAnswer.toString());

    I.amOnPage(registerPage);
    I.register(uniqueUsername, firstName, lastName, uniqueEmail, pwd);

    I.click("New Question");
    I.seeInCurrentUrl(submitQuestionURL);

    I.fillField('title', questionTitle);
    I.fillField('description', questionDescription);
    I.click("Submit");

    I.seeInCurrentUrl(questionsPage);
    I.see(questionDescription);
    I.click(questionDescription);

    I.see("Reply with an answer");
    I.fillField('Write your answer', answer);
    I.click("#submitAnswer");


    I.click("See stack statistics")
    I.see((Number(nbUser) + 1).toString());
    I.see((Number(nbQuestion) + 1).toString());
    I.see((Number(nbAnswer) + 1).toString());
});