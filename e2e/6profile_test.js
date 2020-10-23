Feature("Profile");

const stackURL = "http://stack.ch:9080/stack";
const profileURL = "/profile";
const loginPage = stackURL + "/login";

const uniqueId = new Date().getTime();
const firstName = "Codecept";
const lastName = "JS";
const userFullName = firstName + " " + lastName;
const uniqueUsername = "6profile_test-" + uniqueId;
const uniqueEmail = "sixprofile@" + uniqueId + ".ch";
const pwd = "pwd";

Scenario("after registering, I can see my own profile page", (I) => {
    I.amOnPage(stackURL + "/register");
    I.register(uniqueUsername, firstName, lastName, uniqueEmail, pwd);

    I.click(userFullName);

    I.amOnPage(stackURL + profileURL);
    I.seeInCurrentUrl(profileURL);

    I.see(uniqueUsername);
    I.see(firstName);
    I.see(lastName);
    I.see(uniqueEmail);
    I.see("Answers given");
    I.see("Asked questions list");
    I.see(userFullName + "'s " + "profile")
    I.see("0")
});

Scenario("After posting a question, I see my question on my profile page", (I) => {
    I.amOnPage(loginPage);
    I.login(uniqueUsername, pwd);

    I.click("New Question");
    const questionTitle = "How to browse stack.ch?"
    I.fillField("title", questionTitle);
    const questionDescription = "I am really lost there, any help?"
    I.fillField("description", questionDescription);
    I.click("Submit");

    I.click(userFullName);

    I.see(questionTitle.toUpperCase());
    I.see(questionDescription);
});
