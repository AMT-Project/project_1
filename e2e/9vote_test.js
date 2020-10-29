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

Scenario("after logging in, I can vote on a question", (I) => {
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
    I.seeElement(locate('.question-details__vote-count').withText('0'));

    //Upvote
    I.click('upvoteBtn');
    I.seeElement(locate('.question-details__vote-count').withText('1'));
    //Cancel upvote
    I.click('upvoteBtn');
    I.seeElement(locate('.question-details__vote-count').withText('0'));
    //Downvote
    I.click('downvoteBtn');
    I.seeElement(locate('.question-details__vote-count').withText('-1'));
    //Cancel downvote
    I.click('downvoteBtn');
    I.seeElement(locate('.question-details__vote-count').withText('0'));
    //Upvote
    I.click('upvoteBtn');
    I.seeElement(locate('.question-details__vote-count').withText('1'));
    //Invert vote
    I.click('downvoteBtn');
    I.seeElement(locate('.question-details__vote-count').withText('-1'));
    //Invert vote
    I.click('upvoteBtn');
    I.seeElement(locate('.question-details__vote-count').withText('1'));
});

Scenario("after logging in, I can upvote an answer", (I) => {
    I.amOnPage(loginPage);
    I.login(uniqueUsername,pwd);

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


    let upvoteAnswer = locate('button').withAttr({name: 'upvoteBtn'}).inside('form').inside('.answer__votes');
    I.click(upvoteAnswer);
    let downvoteAnswer = locate('button').withAttr({name: 'downvoteBtn'}).inside('form').inside('.answer__votes');
    I.click(downvoteAnswer);
});

/*
Scenario("Anonymous user can't vote", (I) => {
    //TODO
    I.click();
});*/