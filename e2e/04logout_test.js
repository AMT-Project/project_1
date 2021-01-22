Feature("logout");

const registerURL = "/register";

const uniqueId = new Date().getTime();
const uniqueUsername = "4logout_test-" + uniqueId;
const uniqueEmail = "fourlogin@" + uniqueId + ".ch";
const pwd = "passWord123";
const firstName = "Codecept";
const lastName = "JS";

Scenario("Logout after a successful register", ({ I }) => {
    I.amOnPage("http://stack.ch:9080/stack" + registerURL);
    I.register(uniqueUsername, firstName, lastName, uniqueEmail, pwd);
    I.seeInTitle("Questions");
    I.click("Logout");
    I.seeInTitle("Questions");
});
