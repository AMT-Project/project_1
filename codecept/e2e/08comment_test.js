Feature("Comment");

const stackURL = "http://stack.ch:9080/stack";
const loginPage = stackURL + "/login";
const questionsPage = stackURL + "/";
const registerPage = stackURL + "/register";
const singleQuestionPage = stackURL + "/question";

const uniqueId = new Date().getTime();
const firstName = "Codecept";
const lastName = "JS";
const uniqueUsername = "8comment_test-" + uniqueId;
const uniqueEmail = "eightprofile@" + uniqueId + ".ch";
const pwd = "passWord123";

const questionTitle = "How to browse stack.ch?";
const questionDescription = "I am really lost there, any help?";

const answer = "Sorry I have no idea im just fishing for votes"
const comment = "THIS IS MY COMMENT"
const commentAns = "I don't know what to reply to this"

const myQuestion = locate(".questions-list__list-element").withText(questionTitle);

Scenario("after logging in, I can comment a question", ({ I }) => {
    I.amOnPage(registerPage);
    I.register(uniqueUsername, firstName, lastName, uniqueEmail, pwd);

    I.submitQuestion(questionTitle, questionDescription);
    I.seeInCurrentUrl(questionsPage);

    I.seeElement(myQuestion);
    I.see(questionDescription);
    I.click(myQuestion);

    I.submitComment(comment);

    I.seeInCurrentUrl("/question");
    I.see(questionDescription);
    I.see(comment);
});

Scenario("after logging in, I can comment an answer", ({ I }) => {
    I.amOnPage(loginPage);
    I.login(uniqueUsername, pwd);

    I.seeInCurrentUrl(questionsPage);
    I.see(questionDescription);
    I.click(myQuestion);

    I.submitAnswer(answer)

   I.submitCommentAnswer(commentAns);

    I.seeInCurrentUrl(singleQuestionPage);
    I.see(commentAns);
});

Scenario("anonymous user can't comment question", ({ I }) => {
    I.amOnPage(questionsPage);
    I.see(questionDescription);
    I.click(myQuestion);


    I.seeInCurrentUrl(singleQuestionPage);
    I.see(questionDescription);
    I.see('You must be logged in to be able to comment a question');
});