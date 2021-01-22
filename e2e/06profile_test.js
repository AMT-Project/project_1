Feature("Profile");

const stackURL = "http://stack.ch:9080/stack";
const profileURL = "/profile";
const loginPage = stackURL + "/login";
const questionsPage = stackURL + "/";
const registerPage = stackURL + "/register";

const uniqueId = new Date().getTime();
const firstName = "Codecept";
const lastName = "JS";
const userFullName = firstName + " " + lastName;
const uniqueUsername = "6profile_test-" + uniqueId;
const uniqueEmail = "sixprofile@" + uniqueId + ".ch";
const pwd = "passWord123";

const newFirstName = "Apache";
const newLastName = "JMeter";
const newUserFullName = newFirstName + " " + newLastName;
const newUniqueUsername = "6newprofile_test-" + uniqueId;
const newUniqueEmail = "newsixprofile@" + uniqueId + ".ch";
const newPwd = "newPWD123";

const questionTitle = "How to browse stack.ch?";
const questionDescription = "I am really lost there, any help?";
const questionTitle2 = "How do I uppercase in java?";
const questionDescription2 = "genuine question";

Scenario("after registering, I can see my own profile page", ({ I }) => {
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

Scenario("After posting a question, I see my question statistic has changed", ({ I }) => {
    I.amOnPage(loginPage);
    I.login(uniqueUsername, pwd);

    I.submitQuestion(questionTitle, questionDescription);

    I.click("#profile");
    I.seeInCurrentUrl(profileURL);

    I.see("1");
});

Scenario("I see that my question statistic is the same when I logged back in", ({ I }) => {
    I.amOnPage(loginPage);
    I.login(uniqueUsername, pwd);

    I.submitQuestion(questionTitle, questionDescription);

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

Scenario("I see questions from other users as anon", ({ I }) => {
    I.amOnPage(questionsPage);

    I.see("List of questions");
});

Scenario("After editing my user info, I see my updated profile and I can login with new credentials", ({ I }) => {
    I.amOnPage(loginPage);
    I.login(uniqueUsername, pwd);

    I.click(userFullName);
    I.see(userFullName + "'s " + "profile");
    I.see("Username : " + uniqueUsername);
    I.see("First name : " + firstName);
    I.see("Last name : " + lastName);
    I.see("Email : " + uniqueEmail);

    I.fillField("username", newUniqueUsername);
    I.fillField("email", newUniqueEmail);
    I.fillField("firstName", newFirstName);
    I.fillField("lastName", newLastName);
    I.fillField("oldPassword", pwd);
    I.fillField("newPassword", newPwd);
    I.fillField("repeatPassword", newPwd);
    I.click("Update");

    I.seeInCurrentUrl(profileURL);

    I.see("Username : " + newUniqueUsername);
    I.see("First name : " + newFirstName);
    I.see("Last name : " + newLastName);
    I.see("Email : " + newUniqueEmail);

    I.click("Logout");

    I.amOnPage(loginPage);
    I.login(newUniqueUsername, newPwd);
    I.see(newUserFullName);
});