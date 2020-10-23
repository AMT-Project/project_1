Feature("registration");

Scenario("test valid registration", (I) => {
    const uniqueId = new Date().getTime();
    const uniqueUsername = "CodeceptJS-" + uniqueId;
    const uniqueEmail = "CodeceptJS@" + uniqueId + ".ch";
    I.amOnPage("http://stack.ch:9080/stack/register");
    I.register(uniqueUsername, "Codecept", "JS", uniqueEmail, "pwd");
    I.see("List of questions");
});

Scenario("test missing username registration", (I) => {
    const uniqueId = new Date().getTime();
    const uniqueEmail = "CodeceptJS@" + uniqueId + ".ch";
    I.amOnPage("http://stack.ch:9080/stack/register");
    I.register("", "Codecept", "JS", uniqueEmail, "pwd");
    I.see("Username is mandatory");
});

// TODO: Maybe add tests for every missing inputs

Scenario("test wrongly formatted email registration", (I) => {
    const uniqueId = new Date().getTime();
    const uniqueUsername = "CodeceptJS-" + uniqueId;
    const wrongEmail = "CodeceptJS" + uniqueId + "ch";
    I.amOnPage("http://stack.ch:9080/stack/register");
    I.register(uniqueUsername, "Codecept", "JS", wrongEmail, "pwd");
    I.see("Email is misformatted");
});
