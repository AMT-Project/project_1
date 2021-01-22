Feature("question");

const submitQuestionURL = "/submitQuestion";

const uniqueId = new Date().getTime();
const uniqueUsername = "5question_test-" + uniqueId;
const uniqueEmail = "fivequestion@" + uniqueId + ".ch";
const pwd = "passWord123";

Scenario("/submitQuestion redirects to /login for anonymous user", ({ I }) => {
    I.amOnPage("http://stack.ch:9080/stack" + submitQuestionURL);
    I.seeInCurrentUrl("/login");
});

Scenario("Submit question and redirect to /questions to display", ({ I }) => {
    const title = "titre";
    const description = "description";

    I.amOnPage("http://stack.ch:9080/stack/register");
    I.register(uniqueUsername, "Codecept", "JS", uniqueEmail, pwd);

    I.click("New Question");
    I.seeInCurrentUrl(submitQuestionURL);

    I.fillField('title', title);
    I.fillField('description', description);
    I.click("Submit");

    //I.seeInCurrentUrl("/questions"); TODO
    I.seeInCurrentUrl("/stack/");
    I.see(title.toUpperCase());
    I.see(description);
    I.see(uniqueUsername);
});

Scenario("Cannot submit without filling title and description", ({ I }) => {
    I.amOnPage("http://stack.ch:9080/stack/login");
    I.login(uniqueUsername,pwd)

    I.click("New Question");
    I.seeInCurrentUrl(submitQuestionURL);
    I.fillField('title', "titre only");
    I.click("Submit");
    I.seeInCurrentUrl(submitQuestionURL);

    I.clearField('title');
    I.fillField('description', "description");
    I.click("Submit");
    I.seeInCurrentUrl(submitQuestionURL);
});
