Feature("login");

Scenario("test valid login", (I) => {
  I.wait(1);
  const uniqueId = new Date().getTime();
  const uniqueUsername = "CodeceptJS-" + uniqueId;
  const uniqueEmail = "CodeceptJS@" + uniqueId + ".ch";
  I.amOnPage("http://stack.ch:9080/stack/register");
  I.register(uniqueUsername, "Codecept", "JS", uniqueEmail, "pwd");

  I.seeInTitle("Questions");

  I.click("Logout");
  I.seeInTitle("Login");
  I.login(uniqueUsername, "pwd");
  I.seeInTitle("Questions");
});

Scenario("test wrong username login", (I) => {
  I.wait(1);
  I.amOnPage("http://stack.ch:9080/stack/login");
  I.login("wrongUsername", "pwd");
  I.seeInTitle("Login");
});

Scenario("test wrong password login", (I) => {
  const uniqueId = new Date().getTime();
  const uniqueUsername = "CodeceptJS-" + uniqueId;
  const uniqueEmail = "CodeceptJS@" + uniqueId + ".ch";
  I.amOnPage("http://stack.ch:9080/stack/login");
  I.login(uniqueUsername, "wrongPwd");
  I.seeInTitle("Login");
  I.wait(1);
});
