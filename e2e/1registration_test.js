Feature("registration");

Scenario("test valid registration", (I) => {
  const uniqueId = new Date().getTime();
  const uniqueUsername = "CodeceptJS-" + uniqueId;
  const uniqueEmail = "CodeceptJS@" + uniqueId + ".ch";
  I.amOnPage("http://stack.ch:9080/stack/register");
  I.register(uniqueUsername, "Codecept", "JS", uniqueEmail, "pwd");
  I.seeInTitle("Questions");
  I.wait(1);
});

Scenario("test missing username registration", (I) => {
  I.wait(1);
  const uniqueId = new Date().getTime();
  const uniqueUsername = "CodeceptJS-" + uniqueId;
  const uniqueEmail = "CodeceptJS@" + uniqueId + ".ch";
  I.amOnPage("http://stack.ch:9080/stack/register");
  I.register("", "Codecept", "JS", uniqueEmail, "pwd");
  I.seeInTitle("Register");
});

// TODO: Add tests for every missing inputs

Scenario("test wrongly formatted email registration", (I) => {
  I.wait(1);
  const uniqueId = new Date().getTime();
  const wrongEmail = "CodeceptJS" + uniqueId + ".ch";
  I.amOnPage("http://stack.ch:9080/stack/register");
  I.register("", "Codecept", "JS", wrongEmail, "pwd");
  I.seeInTitle("Register");
});
