Feature("Profile");

const stackURL = "http://stack.ch:9080/stack";
const profileURL = "/profile";
const loginPage = stackURL + "/login";
const questionsPage = stackURL + "/questions";
const registerPage = stackURL + "/register";

const uniqueId = new Date().getTime();
const firstName = "Codecept";
const lastName = "JS";
const userFullName = firstName + " " + lastName;
const uniqueUsername = "6profile_test-" + uniqueId;
const uniqueEmail = "sixprofile@" + uniqueId + ".ch";
const pwd = "pwd";

const questionTitle = "How to browse stack.ch?";
const questionDescription = "I am really lost there, any help?";
const questionTitle2 = "How do I uppercase in java?";
const questionDescription2 = "genuine question";

Scenario("after registering, I can see my own profile page", (I) => {
    I.amOnPage(registerPage);
    I.register(uniqueUsername, firstName, lastName, uniqueEmail, pwd);

    I.click(userFullName);

    I.amOnPage(stackURL + profileURL);
    I.seeInCurrentUrl(profileURL);

    I.see(uniqueUsername);
    I.see(firstName);
    I.see(lastName);
    I.see(uniqueEmail);
    I.see("Questions asked");
    I.see("Answers given");
    I.see(userFullName + "'s " + "profile");
    I.see("0");
});

Scenario("After posting a question, I see my question statistic has changed", (I) => {
    I.amOnPage(loginPage);
    I.login(uniqueUsername, pwd);

    I.click("New Question");
    I.fillField("title", questionTitle);
    I.fillField("description", questionDescription);
    I.click("Submit");

    I.click("#profile");
    I.seeInCurrentUrl(profileURL);

    I.see("1");
});

Scenario("I see that my question statistic is the same when I logged back in", (I) => {
    I.amOnPage(loginPage);
    I.login(uniqueUsername, pwd);

    I.click("New Question");
    I.fillField("title", questionTitle2);
    I.fillField("description", questionDescription2);
    I.click("Submit");

    I.seeInTitle("Questions");

    I.click("#profile");
    I.seeInCurrentUrl(profileURL);
    I.see("2");

    I.click("Logout");

    I.amOnPage(loginPage);
    I.login(uniqueUsername, pwd);

    I.see("List of questions");
    I.click("#profile");
    I.seeInCurrentUrl(profileURL);

    I.see("2");
});

Scenario("I see questions from other users as anon", (I) => {
    I.amOnPage(questionsPage);

    I.see("List of questions");
});

Scenario("Questions from other users are not shown on my profile page", (I) => {
    I.amOnPage(registerPage);
    I.register(uniqueUsername + "ha", firstName, lastName, uniqueEmail + "e", pwd);

    I.click("#profile");
    I.seeInCurrentUrl(profileURL);

    I.dontSee(questionTitle);
    I.dontSee(questionDescription);
    I.dontSee(questionTitle2);
    I.dontSee(questionDescription2);
});
