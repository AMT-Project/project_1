Feature("Comment");

const stackURL = "http://stack.ch:9080/stack";
const profileURL = "/profile";
const loginPage = stackURL + "/login";
const questionsPage = stackURL + "/questions";
const registerPage = stackURL + "/register";
const submitQuestionURL = "/submitQuestion";
const singleQuestionPage = "/question";

const uniqueId = new Date().getTime();
const firstName = "Codecept";
const lastName = "JS";
const userFullName = firstName + " " + lastName;
const uniqueUsername = "8comment_test-" + uniqueId;
const uniqueEmail = "eightprofile@" + uniqueId + ".ch";
const pwd = "pwd";

const questionTitle = "How to browse stack.ch?";
const questionDescription = "I am really lost there, any help?";
const questionTitle2 = "How do I uppercase in java?";
const questionDescription2 = "genuine question";

const answer = "Sorry I have no idea im just fishing for votes"
const comment = "THIS IS MY COMMENT"
const commentAns = "I don't know what to reply to this"

Scenario("after logging in, I can comment a question", (I) => {
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

    I.fillField('content', comment);
    I.click("Submit");

    I.seeInCurrentUrl("/question");
    I.see(questionDescription);
    I.see(comment);
});

Scenario("after logging in, I can comment an answer", (I) => {
    I.amOnPage(loginPage);
    I.login(uniqueUsername, pwd);

    I.seeInCurrentUrl(questionsPage);
    I.see(questionDescription);
    I.click(questionDescription);

    I.see("Reply with an answer");
    I.fillField('Write your answer', answer);
    I.click("#submitAnswer");

    I.fillField('#commentAnswer', commentAns);
    I.click("#submitCommentAnswer");

    I.seeInCurrentUrl(singleQuestionPage);
    I.see(commentAns);
});

Scenario("anonymous user can't comment question", (I) => {
    I.amOnPage(questionsPage);
    I.see(questionDescription);
    I.click(questionDescription);

    I.seeInCurrentUrl(singleQuestionPage);
    I.see(questionDescription);
    I.see('You must be logged in to be able to comment a question');
});