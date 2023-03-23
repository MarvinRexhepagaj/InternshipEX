package com.session_1.service;

import com.session_1.model.Question;
import com.session_1.model.Survey;

import java.util.List;

public interface SurveyService {

    List<Survey> getAllSurveys();
    Survey getSurveyById(int id);
    boolean saveSurvey(Survey survey);
    boolean deleteSurveyById(int id);




    boolean validateSurvey(Survey survey);




    Survey createNewSurvey();

    boolean deleteQuestion(Survey survey, Question question);


}


