package com.talapaneni.trivia.service;

import com.talapaneni.trivia.model.TriviaQuestion;

public class TriviaTestFixtures {
    public static final String QUESTION_FIXTURE = "Question 1?;Answer 1;Answer 2;Answer 3";
    public static final String FORMATTED_QUESTION_FIXTURE = "1. Question 1?\n\t1. Answer 1\n\t2. Answer 2\n\t3. Answer 3\nPlease select the options displayed by giving the corresponding number between 1 and 3 : ";

    /**
     * Builds the TriviaQuestion fixture
     * @return
     */
    public static TriviaQuestion getTriviaQuestionFixture() {
        return new TriviaQuestionService().buildTriviaQuestion(QUESTION_FIXTURE);
    }


}
