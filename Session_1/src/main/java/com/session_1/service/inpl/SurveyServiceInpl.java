package com.session_1.service.inpl;

import com.session_1.model.Question;
import com.session_1.model.Survey;
import com.session_1.service.SurveyService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SurveyServiceInpl implements SurveyService {


    @Override
    public List<Survey> getAllSurveys() {
        return null;
    }

    @Override
    public Survey getSurveyById(int id) {
        return null;
    }

    @Override
    public boolean saveSurvey(Survey survey) {
        return false;
    }

    @Override
    public boolean deleteSurveyById(int id) {
        return false;
    }

    @Override
    public boolean validateSurvey(Survey survey) {

        if (survey == null) {
            return false;

        }
        if (survey.getQuestions() == null) {

            return false;
        }
        if (survey.getQuestions().size() < 10){
            return false;
        }
        if (survey.getQuestions().size() > 40) {
            return false;
        }
        final List<String> addedQuestions = new ArrayList<>();
        for (Question question : survey.getQuestions()) {
            if (addedQuestions.contains(question.getQuestion())){
                return false;

            }
            addedQuestions.add(question.getQuestion());



        }


        return true;
    }




    public void addQuestionToSurvey(Survey survey, Question question) {
        List<Question> questions = survey.getQuestions();
        questions.add(question);
        survey.setQuestions(questions);
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
        return survey;
    }



    @Override
    public boolean deleteQuestion(Survey survey, Question question) {
        if (survey == null || question == null || survey.getQuestions() == null) {
            return false;
        }
        return survey.getQuestions().removeIf(q -> q.getQuestion().equals(question.getQuestion()));
    }


}

