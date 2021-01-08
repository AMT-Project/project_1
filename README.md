# S7ack Application

Stack is a question and answer site for all, based on the famous programming Q&A website Stack Overflow. It's built using the MVC pattern on the server side, using Java EE APIs (Servlets, JSPs, JSTL, JDBC), runs on Open Liberty Application server and runs in a Dockerised environment.

#### Students

| Student          | GitHub username |
| ---------------- | --------------- |
| Ludovic Bonzon   | bonzonlu        |
| Claire Delhomme  | Kleeer          |
| Pablo Mercado    | pabloheigvd     |
| Vitor Vaz Afonso | vitorva         |

## Prerequisites

Do not forget to edit your `/etc/hosts` file to add:
```
127.0.0.1	stack.ch
```

# Pull image and run
You can `cd` into `/scripts` folder and run:
```bash
./pullAndRun.sh
```
Open: stack.ch:9080/stack/
# Dev

## Package and deploy website
### Use application authentification key
...such that the application interacts with the gamification engine. Modify in `src/main/liberty/config/server.env`:
```
APP_KEY=<your gamification application authentification key>
```
You can `cd` into `/scripts` folder and run:
```bash
  ./runDocker.sh
```
Open: stack.ch:9080/stack/
## Testing

### End-to-end tests
You can `cd` into `/scripts` folder and run:
```bash
./runTestsLocally.sh
```
After testing, you can still consult it at: stack.ch:9080/stack/

**Note**: You may want to rerun the script since the script assumes you already have downloaded *openliberty/open-liberty:kernel-java11-openj9-ubi* image. The script waits 30s for the image to be built and launches the server. 
### JMeter
We have a load test plan you can open at `jmeter/stack_test_plan.jmx` with JMeter.

Before starting the test, deploy a website by `cd`'ing into `/scripts` folder and run:
```bash
./pullAndRun.sh
```
And checkout out the votes at stack.ch:9080/stack/

## Application

### Pages list

- Homepage and questions' list **(/questions)**
- User authentication
  - Use login page **(/login)**
  - User registration page **(/register)**
  - Profile page **(/profile)**
- Question details page with answers and comments **(/question?uuid=)**
- Question submission page **(/submitQuestion)**
- Statistics page **(/Statistics)**

### User behaviours

#### Anonymous

An anonymous user can access the homepage ans see the list of questions, with reduced question content, he can click on a question to see its details (full question content, answers to the question and comments to both questions and answers). He can see the global statistics page and obviously can register as a new user to enjoy all the apps' functionalities.

#### Registered user

Once an anonymous user is registered, he can log in. He has then access to the same elements as an anonymous user and can see a profile page of his user infos, with statistics on how many questions and answers he has given. He can also ask new questions and the most interesting part, he can now reply to existing questions, comment on questions and answers and also vote for questions and answers.

### Votes policy

A user must be logged-in to be able to vote. Only supported entities are questions and answers, a user can't vote on a comment. 

A user can only vote once on an entity, once he has made a vote, he can cancel it by re-clicking the vote button or can switch to the opposite vote by clicking the opposite vote button. Vote count can be negative.

Similarly as on YouTube where Youtubers can upvote their own videos, users can vote for their own questions and answers. Unlike the real StackOverflow, votes do not currently change how questions or answers are ordered in the list, we sort them by date.

We have a load test plan with JMeter for votes, ensuring that there's no issue with concurrency while multiple users vote on an entity.

### Navigation of large amount of data

We choose not to implement a tagging mechanism, or a searching bar, the only navigation mechanism that we implemented is the pagination on questions' list and question details for answers. We set the pagination to 3 elements per page (as we have very few records in the DB for now).

### Password policy

Passwords are hashed when stored in the DB, and must be at least 8 characters long, include an uppercase, a lowercase letter and a number.

# Possible improvements
To run integration tests, you need an instance of the website before running IT tests inside intelliJ. You can deploy this website by `cd`'ing in `\scripts` and run:
```bash
  ./runDocker.sh
```
then run the tests.

Through our various test plans, we didn't find any major issues or problems, but there is always things to tweak and improve, other than the bugs that might have made their way through, we can think of the following improvements :

- Adding more tests (especially JMeter load tests, but all kinds of tests are always welcome, and also tests using Arquillian are not yet implemented) and thus improving coverage.
- Factoring JSP components into fragments (we didn't have the time to do that before the project_1 deadline).
- Implementing a tagging mechanism nor a search bar for easier navigation through large amount of data.
- Improving the CSS.

# What has been added since the presentation

Password policy has been updated.
