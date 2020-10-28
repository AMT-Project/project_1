Feature("login");

Scenario("test valid login", (I) => {
    const uniqueId = new Date().getTime();
    const uniqueUsername = "CodeceptJS-" + uniqueId;
    const uniqueEmail = "CodeceptJS@" + uniqueId + ".ch";
    I.amOnPage("http://stack.ch:9080/stack/register");
    I.register(uniqueUsername, "Codecept", "JS", uniqueEmail, "pwd");

    I.see("List of questions");
    I.click("Logout");

    I.see("List of questions");
    I.see("SIGN-IN");
    I.click("Sign-in")
    I.login(uniqueUsername, "pwd");

    I.see("List of questions");
});

Scenario("test wrong username login", (I) => {
    I.amOnPage("http://stack.ch:9080/stack/login");
    I.login("wrongUsername", "pwd");
    I.see("User not found");
});

Scenario("test wrong password login", (I) => {
    const uniqueId = new Date().getTime();
    const uniqueUsername = "CodeceptJS-" + uniqueId;
    const uniqueEmail = "CodeceptJS@" + uniqueId + ".ch";
    I.amOnPage("http://stack.ch:9080/stack/register");
    I.register(uniqueUsername, "Codecept", "JS", uniqueEmail, "pwd");
    I.click("Logout")
    I.amOnPage("http://stack.ch:9080/stack/login");
    I.login(uniqueUsername, "wrongPwd");
    I.see("Credentials verification failed");
});
