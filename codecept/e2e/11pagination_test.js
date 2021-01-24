Feature("Pagination");

const stackURL = "http://stack.ch:9080/stack";
const questionsPage = stackURL + "/questions";
const registerPage = stackURL + "/register";
const submitQuestionURL = stackURL + "/submitQuestion";

const uniqueId = new Date().getTime();
const firstName = "Codecept";
const lastName = "JS";
const uniqueUsername = "11pagination_test-" + uniqueId;
const uniqueEmail = "elevenprofile@" + uniqueId + ".ch";
const pwd = "passWord123";

const questionTitle = "How to browse stack.ch?";
const questionDescription = "I am really lost there, any help?";

Scenario("Pagination on question list", ({ I }) => {
    I.amOnPage(registerPage);
    I.register(uniqueUsername, firstName, lastName, uniqueEmail, pwd);

    for(let i = 0; i < 10; ++i){
        I.click("New Question");
        I.seeInCurrentUrl(submitQuestionURL);

        I.fillField('title', questionTitle);
        I.fillField('description', questionDescription);
        I.click("Submit");
    }

    I.seeInCurrentUrl(questionsPage);
    I.seeElement(".pagination");
    I.dontSee("Previous");
    I.click("2");
    I.seeInCurrentUrl("?currentPage=2");
    I.click("Next");
    I.seeInCurrentUrl("?currentPage=3");
    I.click("Previous");
    I.seeInCurrentUrl("?currentPage=2");
    //Go to last page
    I.click(locate(".page-link").at(-2));
    I.dontSee("Next");
});