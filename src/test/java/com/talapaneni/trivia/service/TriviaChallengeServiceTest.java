package com.talapaneni.trivia.service;

import com.talapaneni.trivia.model.TriviaQuestion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TriviaChallengeServiceTest {

    TriviaChallengeService service;

    @BeforeEach
    public void setup() {
        service = new TriviaChallengeService();
        service = Mockito.spy(service);
    }

    @Test
    public void prepareTriviaQuestionsShouldGetAllTheQuestions() throws IOException, URISyntaxException {
        service.prepareTriviaQuestions();
        assertFalse(service.getQuestions().isEmpty());;
    }

    @Test
    public void shuffleQuestionsShouldShuffleAllTheQuestions() throws IOException, URISyntaxException {
        service.prepareTriviaQuestions();
        Mockito.verify(service, Mockito.times(1)).shuffleQuestions();
    }

    @Test
    public void shuffleQuestionsShouldShuffleAllTheOptions() throws IOException, URISyntaxException {
        service.prepareTriviaQuestions();
        Mockito.verify(service, Mockito.times(service.getQuestions().size())).shuffleQuestionOptions(Mockito.any());
    }

    @Test
    public void formatQuestionShouldFormatTheQuestionAndOptions() {
        TriviaQuestion question = TriviaTestFixtures.getTriviaQuestionFixture();
        String formattedQuestion = service.formatQuestion(question);
        assertEquals(TriviaTestFixtures.FORMATTED_QUESTION_FIXTURE, formattedQuestion);
    }

    @Test
    public void playTriviaShouldCallPrepareTriviaQuestions() throws IOException, URISyntaxException {
        service.playTrivia(true);
        Mockito.verify(service, Mockito.times(1)).prepareTriviaQuestions();
    }

    @Test
    public void playTriviaShouldCallPrepareConsoleReader() throws IOException, URISyntaxException {
        service.playTrivia(true);
        Mockito.verify(service, Mockito.times(1)).prepareConsoleReader();
    }

    @Test
    public void playTriviaShouldCallDisplayScore() throws IOException, URISyntaxException {
        service.playTrivia(true);
        Mockito.verify(service, Mockito.times(1)).displayScore();
    }

    @Test
    public void playTriviaShouldHaveScoreAsZeoAtStart() throws IOException, URISyntaxException {
        assertEquals(0, service.getScore());
        service.playTrivia(true);
    }

    @Test
    public void playTriviaShouldHaveScoreAsQuestionsAnswered() throws IOException, URISyntaxException {
        service.playTrivia(true);
        assertEquals(10, service.getScore());
    }

    @Test
    public void playTriviaShouldHaveQuestionIndexAsZeoAtStart() throws IOException, URISyntaxException {
        assertEquals(0, service.getQuestionIndex());
        service.playTrivia(true);
    }


}
