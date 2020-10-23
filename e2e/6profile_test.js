Feature("Profile");

const stackURL = "http://stack.ch:9080/stack";
const profileURL = "/profile";

const uniqueId = new Date().getTime();
const firstName = "Codecept";
const lastName = "JS";
const uniqueUsername = "6profile_test-" + uniqueId;
const uniqueEmail = "sixprofile@" + uniqueId + ".ch";
const pwd = "pwd";

Scenario("after registering, I can see my own profile page", (I) => {
    I.amOnPage(stackURL + "/register");
    I.register(uniqueUsername, firstName, lastName, uniqueEmail, pwd);

    const userFullName = firstName + " " + lastName;
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
