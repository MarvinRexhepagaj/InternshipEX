package com.session_1.service.inpl;

import com.session_1.model.*;
import com.session_1.service.SurveyService;

import java.time.LocalDateTime;
import java.util.*;

public class SurveyServiceInpl implements SurveyService {

    private Map<String, Survey> surveys = new HashMap<>();
    private List<SurveyResult> surveyResults = new ArrayList<>();


    @Override
    public Map<String, Survey> getSurveys() {
        return surveys;
    }


    @Override
    public boolean validateSurvey(Survey survey) {

        if (survey == null) {
            return false;

        }
        if (survey.getQuestions() == null) {

            return false;
        }
        if (survey.getQuestions().size() < 10) {
            return false;
        }
        if (survey.getQuestions().size() > 40) {
            return false;
        }
        final List<String> addedQuestions = new ArrayList<>();
        for (Question question : survey.getQuestions()) {
            if (addedQuestions.contains(question.getQuestion())) {
                return false;

            }
            addedQuestions.add(question.getQuestion());


        }


        return true;
    }


    @Override
    public boolean addQuestion(Survey survey, Question question) {
        List<Question> questions = survey.getQuestions();
        if (questions.size() >= 40) {
            System.out.println("The survey already has the maximum number of questions.");
            return false;
        }
        questions.add(question);
        survey.setQuestions(questions);
        System.out.println("Question added to survey: " + survey.getTitle());
        return true;
    }

    @Override
    public boolean deleteQuestion(Survey survey, Question question) {
        List<Question> questions = survey.getQuestions();
        if (questions.contains(question)) {
            questions.remove(question);
            System.out.println("Question deleted from survey: " + survey.getTitle());
            return true;
        } else {
            System.out.println("Question not found in survey.");
            return false;
        }
    }


    @Override
    public Survey createNewSurvey() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter survey title:");
        String title = scanner.nextLine();

        System.out.println("Enter survey topic:");
        String topic = scanner.nextLine();

        System.out.println("Enter survey description:");
        String description = scanner.nextLine();

        System.out.println("The survey has to have 10-40 questions");

        List<Question> questions = new ArrayList<>();
        boolean done = false;
        while (!done) {
            System.out.println("Enter a question (type 'done' when finished):");
            String questionText = scanner.nextLine();
            if (questionText.equalsIgnoreCase("done")) {
                done = true;
            } else {
                Question question = new Question(questionText);
                questions.add(question);
            }
        }

        if (questions.size() < 10 || questions.size() > 40) {
            System.out.println("Not the right amount of questions");
            return null;
        }

        Survey survey = new Survey(title, topic, description, questions);
        surveys.put(title, survey); // Add the created survey to the map

        return survey;
    }


    @Override
    public void editSurvey() {
        Scanner scanner = new Scanner(System.in);
        Map<String, Survey> surveys = getSurveys();
        if (surveys == null || surveys.isEmpty()) {
            System.out.println("There are no surveys to edit");
            return;
        }
        System.out.println("Choose a survey to edit:");
        int i = 1;
        for (String title : surveys.keySet()) {
            System.out.println(i + ") " + title);
            i++;
        }
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        i = 1;
        Survey surveyToEdit = null;
        String titleToEdit = "";
        for (String title : surveys.keySet()) {
            if (i == choice) {
                surveyToEdit = surveys.get(title);
                titleToEdit = title;
                break;
            }
            i++;
        }
        if (surveyToEdit == null) {
            System.out.println("Invalid choice");
            return;
        }
        boolean done = false;
        while (!done) {
            System.out.println("Survey title: " + titleToEdit);
            System.out.println("Survey topic: " + surveyToEdit.getTopic());
            System.out.println("Survey description: " + surveyToEdit.getDescription());
            System.out.println("Survey questions:");
            List<Question> questions = surveyToEdit.getQuestions();
            i = 1;
            for (Question question : questions) {
                System.out.println(i + ") " + question.getQuestion());
                i++;
            }
            System.out.println("1) Add question");
            System.out.println("2) Delete question");
            System.out.println("3) Save and exit");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            switch (choice) {
                case 1:
                    System.out.println("Enter question:");
                    String questionText = scanner.nextLine();
                    Question question = new Question(questionText);
                    addQuestion(surveyToEdit, question); // pass in surveyToEdit instead of surveyTitle
                    if (surveyToEdit.getQuestions().size() > 40) {
                        System.out.println("Too many questions. Please delete some.");
                    }
                    break;
                case 2:
                    System.out.println("Enter question number to delete:");
                    int questionNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    if (questionNumber < 1 || questionNumber > questions.size()) {
                        System.out.println("Invalid question number");
                    } else {
                        Question questionToDelete = questions.get(questionNumber - 1);
                        deleteQuestion(surveyToEdit, questionToDelete);
                        if (surveyToEdit.getQuestions().size() < 10) {
                            System.out.println("Not enough questions. Please add some.");
                        }
                    }
                    break;
                case 3:
                    if (surveyToEdit.getQuestions().size() < 10) {
                        System.out.println("Not enough questions. Please add some.");
                        break;
                    }
                    if (surveyToEdit.getQuestions().size() > 40) {
                        System.out.println("Too many questions. Please delete some.");
                        break;
                    }
                    surveys.put(titleToEdit, surveyToEdit);
                    System.out.println("Survey saved successfully");
                    done = true;
                    break;
            }
        }
    }


    @Override
    public void addCandidate(Candidate candidate, Survey survey, Map<Question, Answer> answers) {
        SurveyResult surveyResult = new SurveyResult();
        surveyResult.setCandidate(candidate);
        surveyResult.setResult(answers);
        surveyResult.setDateTaken(LocalDateTime.now());
        surveyResults.add(surveyResult);
    }

    @Override
    public void takeSurvey(Survey survey) {
        Scanner scanner = new Scanner(System.in);

        // Create a map to store the candidate's answers
        Map<Question, Answer> answers = new HashMap<>();

        // Prompt the candidate to answer each question
        for (Question question : survey.getQuestions()) {
            System.out.println(question.getQuestion());
            Set<Answer> validAnswers = Answer.getValidAnswers();
            int i = 1;
            for (Answer answer : validAnswers) {
                System.out.println(i + ". " + answer.toString());
                i++;
            }
            System.out.println(i + ". Skip this question");
            boolean validAnswer = false;
            Answer answer = null;
            while (!validAnswer) {
                String answerText = scanner.nextLine();
                try {
                    int choice = Integer.parseInt(answerText);
                    if (choice == i) {
                        break;  // skip this question
                    }
                    answer = Answer.getValidAnswers().toArray(new Answer[0])[choice - 1];
                    validAnswer = true;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid answer. Please enter a number.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid answer. Please enter a number between 1 and " + i);
                }
            }
            answers.put(question, answer);
        }

        // Prompt the candidate to enter their name, email address, and phone number
        System.out.println("Please enter your first name:");
        String firstName = scanner.nextLine();
        System.out.println("Please enter your last name:");
        String lastName = scanner.nextLine();
        System.out.println("Please enter your email address:");
        String email = scanner.nextLine();
        System.out.println("Please enter your phone number:");
        String phoneNumber = scanner.nextLine();

        // Create a new candidate object
        Candidate candidate = new Candidate(firstName, lastName, email, phoneNumber);

        // Add the survey results to the surveyResults list
        addCandidate(candidate, survey, answers);

        System.out.println("Thank you for taking the survey!");
    }



    @Override
    public void printSurveyResults() {
        for (SurveyResult surveyResult : surveyResults) {
            System.out.println("Candidate: " + surveyResult.getCandidate().getFirstName() + surveyResult.getCandidate().getLastName());
            System.out.println("Date taken: " + surveyResult.getDateTaken());
            System.out.println("Survey results:");
            for (Question question : surveyResult.getResult().keySet()) {
                Answer answer = surveyResult.getResult().get(question);
                System.out.println(question.getQuestion() + ": " + answer);
            }
            System.out.println();
        }
    }





}









