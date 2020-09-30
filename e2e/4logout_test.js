Feature("logout");

Scenario("Logout after a successful register", (I) => {
  I.wait(1);
  const uniqueId = new Date().getTime();
  const uniqueUsername = "CodeceptJS-" + uniqueId;
  const uniqueEmail = "CodeceptJS@" + uniqueId + ".ch";
  I.amOnPage("http://stack.ch:9080/stack/register");
  I.register(uniqueUsername, "Codecept", "JS", uniqueEmail, "pwd");
  I.seeInTitle("Questions");
  I.click("Logout");
  I.seeInTitle("Login");
});
