package com.session_1.model;

import java.util.EnumSet;
import java.util.Set;

public enum Answer {
    AGREE,
    SLIGHTLY_AGREE,
    SLIGHTLY_DISAGREE,
    DISAGREE;

    public static Set<Answer> getValidAnswers() {
        return EnumSet.allOf(Answer.class);
    }


}
