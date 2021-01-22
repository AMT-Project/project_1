Feature("Answer");

const stackURL = "http://stack.ch:9080/stack";
const questionsPage = stackURL + "/";
const registerPage = stackURL + "/register";

const uniqueId = new Date().getTime();
const firstName = "Codecept";
const lastName = "JS";
const uniqueUsername = "7answer_test-" + uniqueId;
const uniqueEmail = "sevenprofile@" + uniqueId + ".ch";
const pwd = "passWord123";

const questionTitle = "How to browse stack.ch?";
const questionDescription = "I am really lost there, any help?";

const answer = "Sorry I have no idea im just fishing for votes"

const myQuestion = locate(".questions-list__list-element").withText(questionTitle);

Scenario("after logging in, I can answer to a question", ({ I }) => {
    I.amOnPage(registerPage);
    I.register(uniqueUsername, firstName, lastName, uniqueEmail, pwd);

    I.submitQuestion(questionTitle, questionDescription);

    I.seeInCurrentUrl(questionsPage);
    I.see(questionDescription);
    I.click(myQuestion);

    I.see("Reply with an answer");
    I.submitAnswer(answer);

    I.seeInCurrentUrl("/question");
    I.see(questionDescription);
    I.see(answer);
});

Scenario("anonymous user can't answer question", ({ I }) => {
    I.amOnPage(questionsPage);
    I.see(questionDescription);
    I.click(myQuestion);

    I.seeInCurrentUrl("/question");
    I.see(questionDescription);
    I.see("You must be logged in to be able to reply to this question");
});
