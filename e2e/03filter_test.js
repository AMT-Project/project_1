Feature("filter");

Scenario("/submitQuestions requires authentication", (I) => {
    I.amOnPage("http://stack.ch:9080/stack/submitQuestion");
    I.see("Login Account");
});

const uniqueId = new Date().getTime();
const uniqueUsername = "CodeceptJS-" + uniqueId;
const uniqueEmail = "CodeceptJS@" + uniqueId + ".ch";

Scenario("/submitQuestions is available after register", (I) => {
    I.amOnPage("http://stack.ch:9080/stack/register");
    I.register(uniqueUsername, "Codecept", "JS", uniqueEmail, "pwd");
    I.see("List of questions");
});

Scenario("/submitQuestions is available after login", (I) => {
    I.amOnPage("http://stack.ch:9080/stack/login");
    I.login(uniqueUsername, "pwd");
    I.see("List of questions");
});
