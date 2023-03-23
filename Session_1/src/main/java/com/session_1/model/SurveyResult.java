package com.session_1.model;

import java.time.LocalDateTime;
import java.util.Map;

public class SurveyResult {

    private Question question;
    private Candidate candidate;

    private LocalDateTime dateTaken;

    private Map<Question,Answer> result;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public LocalDateTime getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(LocalDateTime dateTaken) {
        this.dateTaken = dateTaken;
    }

    public Map<Question, Answer> getResult() {
        return result;
    }

    public void setResult(Map<Question, Answer> result) {
        this.result = result;
    }
}
