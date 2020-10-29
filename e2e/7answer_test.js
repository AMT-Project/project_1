Feature("Answer");

const stackURL = "http://stack.ch:9080/stack";
const profileURL = "/profile";
const loginPage = stackURL + "/login";
const questionsPage = stackURL + "/questions";
const registerPage = stackURL + "/register";
const submitQuestionURL = stackURL + "/submitQuestion";

const uniqueId = new Date().getTime();
const firstName = "Codecept";
const lastName = "JS";
const userFullName = firstName + " " + lastName;
const uniqueUsername = "7answer_test-" + uniqueId;
const uniqueEmail = "sevenprofile@" + uniqueId + ".ch";
const pwd = "pwd";

const questionTitle = "How to browse stack.ch?";
const questionDescription = "I am really lost there, any help?";
const questionTitle2 = "How do I uppercase in java?";
const questionDescription2 = "genuine question";

const answer = "Sorry I have no idea im just fishing for votes"

Scenario("after logging in, I can answer to a question", (I) => {
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

    I.seeInCurrentUrl("/question");
    I.see(questionDescription);
    I.see(answer);
});

Scenario("anonymous user can't answer question", (I) => {
    I.amOnPage(questionsPage);
    I.see(questionDescription);
    I.click(questionDescription);

    I.seeInCurrentUrl("/question");
    I.see(questionDescription);
    I.see("You must be logged in to be able to reply to this question");
});
