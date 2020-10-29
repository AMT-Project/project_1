Feature("registration");

const registerURL = "/register";

const uniqueId = new Date().getTime();
const uniqueUsername1 = "1registration_test1-" + uniqueId;
const uniqueUsername2 = "1registration_test2-" + uniqueId;
const uniqueEmail = "oneregister@" + uniqueId + ".ch";
const pwd = "pwd";
const firstName = "Codecept";
const lastName = "JS";

const emptyStr = "";

const wrongEmail = "CodeceptJS.ch"

Scenario("test valid registration", (I) => {
    I.amOnPage("http://stack.ch:9080/stack" + registerURL);
    I.register(uniqueUsername1, firstName, lastName, uniqueEmail, pwd);
    I.see("List of questions");
});

Scenario("test missing username registration", (I) => {
    I.amOnPage("http://stack.ch:9080/stack" + registerURL);
    I.register(emptyStr, firstName, lastName, uniqueEmail, pwd);
    I.see("Username is mandatory");
});

// TODO: Maybe add tests for every missing inputs

Scenario("test wrongly formatted email registration", (I) => {
    I.amOnPage("http://stack.ch:9080/stack" + registerURL);
    I.register(uniqueUsername2, firstName, lastName, wrongEmail, pwd);
    I.see("Email is misformatted");
});
