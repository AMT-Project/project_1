Feature("filter");

const submitQuestionURL = "/submitQuestion";
const registerURL = "/register";
const loginURL = "/login";

const uniqueId = new Date().getTime();
const uniqueUsername = "3filter_test-" + uniqueId;
const uniqueEmail = "threefilter@" + uniqueId + ".ch";
const pwd = "passWord123";
const firstName = "Codecept";
const lastName = "JS";

Scenario("/submitQuestions requires authentication", (I) => {
    I.amOnPage("http://stack.ch:9080/stack" + submitQuestionURL);
    I.see("Login Account");
});

Scenario("/submitQuestions is available after register", (I) => {
    I.amOnPage("http://stack.ch:9080/stack" + registerURL);
    I.register(uniqueUsername, firstName, lastName, uniqueEmail, pwd);
    I.see("List of questions");
});

Scenario("/submitQuestions is available after login", (I) => {
    I.amOnPage("http://stack.ch:9080/stack" + loginURL);
    I.login(uniqueUsername, pwd);
    I.see("List of questions");
});
