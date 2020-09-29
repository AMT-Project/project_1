Feature("filter");

Scenario("/questions requires authentication", (I) => {
  I.wait(1);
  I.amOnPage("http://stack.ch:9080/stack/questions");
  I.seeInTitle("Login");
});

const uniqueId = new Date().getTime();
const uniqueUsername = "CodeceptJS-" + uniqueId;
const uniqueEmail = "CodeceptJS@" + uniqueId + ".ch";

Scenario("/questions is available after register", (I) => {
  I.wait(1);
  I.amOnPage("http://stack.ch:9080/stack/register");
  I.register(uniqueUsername, "Codecept", "JS", uniqueEmail, "pwd");
  I.seeInTitle("Questions");
});

Scenario("/questions is available after login", (I) => {
  I.wait(1);
  I.amOnPage("http://stack.ch:9080/stack/login");
  I.login(uniqueUsername, "pwd");
  I.seeInTitle("Questions");
});
