package com.session_1.model;

import java.util.List;

public class Survey {

    private String title;
    private String topic;
    private String description;

    private List<Question> questions;

    public Survey(String title, String topic, String description, List<Question> questions) {
        this.title = title;
        this.topic = topic;
        this.description = description;
        this.questions = questions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
