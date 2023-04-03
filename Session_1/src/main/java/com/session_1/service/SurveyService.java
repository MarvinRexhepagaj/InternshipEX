package com.session_1.service;

import com.session_1.model.Answer;
import com.session_1.model.Candidate;
import com.session_1.model.Question;
import com.session_1.model.Survey;

import java.util.Map;

public interface SurveyService {


    Map<String, Survey> getSurveys();






    abstract void editSurvey();




    boolean validateSurvey(Survey survey);

    boolean addQuestion(Survey survey, Question question);

    boolean deleteQuestion(Survey survey, Question question);

    Survey createNewSurvey();

    void addCandidate(Candidate candidate, Survey survey, Map<Question, Answer> answers);


    void takeSurvey(Survey survey);


    void printSurveyResults();


}







