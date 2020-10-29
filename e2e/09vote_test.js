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
    I.seeElement({name: 'upvoteBtn'});
    I.seeElement({name: 'downvoteBtn'});

    //Anonymous user
    I.click("Logout");
    //TODO
    I.seeInCurrentUrl(questionsPage);
    I.see(questionDescription);
    I.click(questionDescription);
    I.seeInCurrentUrl(singleQuestionPage);

    I.seeElement('.question-details__vote-count');
    I.dontSeeElement('upvoteBtn');
    I.dontSeeElement('downvoteBtn');
});

Scenario("Control vote logic on question", (I) => {
    I.amOnPage(loginPage);
    I.login(uniqueUsername, pwd);

    I.seeInCurrentUrl(questionsPage);
    I.see(questionDescription);
    I.click(myQuestion);
    I.seeInCurrentUrl(singleQuestionPage);

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

Scenario("Votes from multiple users are accounted", async(I) => {
    I.amOnPage(registerPage);
    I.register(uniqueUsername + "doppel", firstName, lastName, uniqueEmail + "doppel", pwd);

    I.see(questionDescription);
    I.click(myQuestion);

    const voteCount = await I.grabTextFrom(locateVoteCount);

    if(Number(voteCount) < 1){
        throw(new Error("No previous vote"));
    }

    I.click('upvoteBtn');
    I.seeElement(locateVoteCount.withText((Number(voteCount) + 1).toString()));
    I.click('downvoteBtn');
    I.seeElement(locateVoteCount.withText((Number(voteCount) - 1).toString()));
});

Scenario("Control vote logic on answer", (I) => {
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

    //TODO complete logic
    I.click();
});


