package com.talapaneni.trivia.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TriviaQuestion {
    private String question;
    private String answer;
    private List<String> options;

    @Override
    public String toString() {
        return "TriviaQuestion{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", options=" + options +
                '}';
    }
}
