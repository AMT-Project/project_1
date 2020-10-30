Feature("Vote");

const stackURL = "http://stack.ch:9080/stack";
const profileURL = "/profile";
const loginPage = stackURL + "/login";
const questionsPage = stackURL + "/questions";
const singleQuestionPage = stackURL + "/question";
const registerPage = stackURL + "/register";
const submitQuestionURL = stackURL + "/submitQuestion";

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

const myQuestion = locate(".questions-list__list-element").withText(questionTitle);
const locateVoteCount = locate('.question-details__vote-count');
const locateAloneVoteCount = locate('.question-details__vote-count--alone')
const upvoteBtn = {name: 'upvoteBtn'};
const downvoteBtn = {name: 'downvoteBtn'};


const upvoteAnswer = locate('button').withAttr(upvoteBtn).inside('.answers-list__answer--row');
const downvoteAnswer = locate('button').withAttr(downvoteBtn).inside('.answers-list__answer--row');
const locateVoteCountAnswer = locate('.question-details__vote-count').inside(".answers-list__answer--row");


Scenario("Only logged users can vote", (I) => {
    //Logged user
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
    I.seeElement(upvoteBtn);
    I.seeElement(downvoteBtn);

    //Anonymous user
    I.click("Logout");
    I.seeInCurrentUrl(questionsPage);
    I.see(questionDescription);
    I.click(questionDescription);
    I.seeInCurrentUrl(singleQuestionPage);

    I.seeElement(locateAloneVoteCount);
    I.dontSeeElement(upvoteBtn);
    I.dontSeeElement(downvoteBtn);
});

Scenario("Control vote logic on question", (I) => {
    I.amOnPage(loginPage);
    I.login(uniqueUsername, pwd);

    I.seeInCurrentUrl(questionsPage);
    I.see(questionDescription);
    I.click(myQuestion);
    I.seeInCurrentUrl(singleQuestionPage);

    I.seeElement(locateVoteCount.withText('0'));

    //Upvote
    I.click(upvoteBtn);
    I.seeElement(locateVoteCount.withText('1'));
    //Cancel upvote
    I.click(upvoteBtn);
    I.seeElement(locateVoteCount.withText('0'));

    //Downvote
    I.click(downvoteBtn);
    I.seeElement(locateVoteCount.withText('-1'));
    //Cancel downvote
    I.click(downvoteBtn);
    I.seeElement(locateVoteCount.withText('0'));

    //Upvote
    I.click(upvoteBtn);
    I.seeElement(locateVoteCount.withText('1'));
    //Invert vote
    I.click(downvoteBtn);
    I.seeElement(locateVoteCount.withText('-1'));
    //Invert vote
    I.click(upvoteBtn);
    I.seeElement(locateVoteCount.withText('1'));
});

Scenario("Votes from multiple users are accounted", async(I) => {
    I.amOnPage(registerPage);
    I.register(uniqueUsername + "doppel", firstName, lastName, uniqueEmail + "doppel", pwd);

    I.see(questionDescription);
    I.click(myQuestion);

    const voteCount = await I.grabTextFrom(locateVoteCount);

    if(Number(voteCount) < 1){
        throw(new Error("No previous vote"));
    }

    I.click(upvoteBtn);
    I.seeElement(locateVoteCount.withText((Number(voteCount) + 1).toString()));
    I.click(downvoteBtn);
    I.seeElement(locateVoteCount.withText((Number(voteCount) - 1).toString()));
});

Scenario("Control vote logic on answer", (I) => {
    I.amOnPage(loginPage);
    I.login(uniqueUsername,pwd);

    I.see(questionDescription);
    I.click(myQuestion);

    I.see("Reply with an answer");
    I.fillField('Write your answer', answer);
    I.click("#submitAnswer");

    I.seeInCurrentUrl(singleQuestionPage);
    I.see(questionDescription);
    I.see(answer);


    I.seeElement(locateVoteCountAnswer.withText('0'));

    //Upvote
    I.click(upvoteAnswer);
    I.seeElement(locateVoteCountAnswer.withText('1'));
    //Cancel upvote
    I.click(upvoteAnswer);
    I.seeElement(locateVoteCountAnswer.withText('0'));

    //Downvote
    I.click(downvoteAnswer);
    I.seeElement(locateVoteCountAnswer.withText('-1'));
    //Cancel downvote
    I.click(downvoteAnswer);
    I.seeElement(locateVoteCountAnswer.withText('0'));

    //Upvote
    I.click(upvoteAnswer);
    I.seeElement(locateVoteCountAnswer.withText('1'));
    //Invert vote
    I.click(downvoteAnswer);
    I.seeElement(locateVoteCountAnswer.withText('-1'));
    //Invert vote
    I.click(upvoteAnswer);
    I.seeElement(locateVoteCountAnswer.withText('1'));

});
