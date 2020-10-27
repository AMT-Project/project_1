Feature("Vote");

const stackURL = "http://stack.ch:9080/stack";
const profileURL = "/profile";
const loginPage = stackURL + "/login";
const questionsPage = stackURL + "/questions";
const registerPage = stackURL + "/register";
const submitQuestionURL = "/submitQuestion";

const uniqueId = new Date().getTime();
const firstName = "Codecept";
const lastName = "JS";
const userFullName = firstName + " " + lastName;
const uniqueUsername = "9vote_test-" + uniqueId;
const uniqueEmail = "nineprofile@" + uniqueId + ".ch";
const pwd = "pwd";

const questionTitle = "How to browse stack.ch?";
const questionDescription = "I am really lost there, any help?";
const questionTitle2 = "How do I uppercase in java?";
const questionDescription2 = "genuine question";

const answer = "Sorry I have no idea im just fishing for votes"
const comment = "THIS IS MY COMMENT"
const commentAns = "I don't know what to reply to this"

Scenario("after logging in, I can upvote a question", (I) => {
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
    I.click('upvoteBtn');
});

Scenario("after logging in, I can upvote an answer", (I) => {
  //TODO
    I.click();
});

Scenario("I cannot upvote an answer twice", (I) => {
    //TODO
    I.click();
});

//TODO downvotes

Scenario("Anonymous user can't vote", (I) => {
    //TODO
    I.click();
});