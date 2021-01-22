// in this file you can append custom step methods to 'I' object

module.exports = function() {

  const stackURL = "http://stack.ch:9080/stack";
  const questionsPage = stackURL + "/";
  const registerPage = stackURL + "/register";
  const submitQuestionURL = stackURL + "/submitQuestion";


  return actor({

    login: function(username, password) {
      this.fillField('username', username);
      this.fillField('password', password);
      this.click('Login');
    },

    register: function(username, firstName, lastName, email, password) {
      this.fillField('username', username);
    this.fillField('firstName', firstName);
    this.fillField('lastName', lastName);
    this.fillField('email', email);
    this.fillField('password', password);
    this.click('Register');
    },

    submitQuestion: function(questionTitle, questionDescription) {
      this.click("New Question");
      this.seeInCurrentUrl(submitQuestionURL);

      this.fillField('title', questionTitle);
      this.fillField('description', questionDescription);
      this.click("Submit");
    },

    submitAnswer: function(answer) {
      I.fillField('Write your answer', answer);
      I.click("#submitAnswer");
    }



  });
}
