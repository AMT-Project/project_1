module.exports = function() {

  const stackURL = "http://stack.ch:9080/stack";
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
      this.fillField('Write your answer', answer);
      this.click("#submitAnswer");
    },

    submitComment: function(comment) {
      this.fillField('content', comment);
      this.click("Submit");
    },

    submitCommentAnswer: function(commentAns) {
      this.fillField('#commentAnswer', commentAns);
      this.click("#submitCommentAnswer");
    },



  });
}
