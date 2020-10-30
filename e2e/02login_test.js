Feature("login");

const loginURL = "/login";
const registerURL = "/register";

const uniqueId = new Date().getTime();
const uniqueUsername = "2login_test-" + uniqueId;
const uniqueEmail = "twologin@" + uniqueId + ".ch";
const pwd = "passWord123";
const firstName = "Codecept";
const lastName = "JS";

const wrongUsername = "wrongusername";
const wrongPwd = "wrongpwd";

Scenario("test valid login", (I) => {
    I.amOnPage("http://stack.ch:9080/stack" + registerURL);
    I.register(uniqueUsername, firstName, lastName, uniqueEmail, pwd);

    I.see("List of questions");
    I.click("Logout");

    I.see("List of questions");
    I.see("SIGN-IN");
    I.click("Sign-in")
    I.login(uniqueUsername, pwd);

    I.see("List of questions");
});

Scenario("test wrong username login", (I) => {
    I.amOnPage("http://stack.ch:9080/stack" + loginURL);
    I.login(wrongUsername, pwd);
    I.see("User not found");
});

Scenario("test wrong password login", (I) => {
    I.amOnPage("http://stack.ch:9080/stack" + loginURL);
    I.login(uniqueUsername, wrongPwd);
    I.see("Credentials verification failed");
});
