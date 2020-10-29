Feature("Statistics");

const stackURL = "http://stack.ch:9080/stack";
const profileURL = "/profile";
const loginPage = stackURL + "/login";
const questionsPage = stackURL + "/questions";
const singleQuestionPage = stackURL + "/question";
const registerPage = stackURL + "/register";
const submitQuestionURL = stackURL + "/submitQuestion";
const statisticsURL = stackURL + "/statistics";

const uniqueId = new Date().getTime();
const firstName = "Codecept";
const lastName = "JS";
const userFullName = firstName + " " + lastName;
const uniqueUsername = "10statistics_test-" + uniqueId;
const uniqueEmail = "tenprofile@" + uniqueId + ".ch";
const pwd = "pwd";

const questionTitle = "How to browse stack.ch?";
const questionDescription = "I am really lost there, any help?";
const questionTitle2 = "How do I uppercase in java?";
const questionDescription2 = "genuine question";

const answer = "Sorry I have no idea im just fishing for votes"
const comment = "THIS IS MY COMMENT"
const commentAns = "I don't know what to reply to this"

const locateStatUser = locate(".stat").withText("Registered users");
const locateStatQuestion = locate(".stat").withText("Questions asked");
const locateStatAnswer = locate(".stat").withText("Answers provided");
const locateNbUser = locate(".circle").inside(locateStatUser);

Scenario("Anonymous user can consult statistics", (I) => {
    I.amOnPage(statisticsURL);
    I.seeElement(locateStatUser);
    I.seeElement(locateStatQuestion);
    I.seeElement(locateStatAnswer);
});

Scenario("Stats updated after user registration", async(I) => {
    I.amOnPage(statisticsURL);
    I.seeElement(locateNbUser);
    const nbUser = await I.grabTextFrom(locateNbUser);
    I.see(nbUser.toString());

    I.amOnPage(registerPage);
    I.register(uniqueUsername, firstName, lastName, uniqueEmail, pwd);
    I.click("See app statistics")
    I.see((Number(nbUser) + 1).toString());
});