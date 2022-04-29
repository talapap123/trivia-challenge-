package com.talapaneni.trivia.service;

import com.talapaneni.trivia.model.TriviaQuestion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;

import static com.talapaneni.trivia.service.TriviaTestFixtures.QUESTION_FIXTURE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class TriviaQuestionServiceTest {
    TriviaQuestionService service;

    @BeforeEach
    public void setup() {
        service = new TriviaQuestionService();
    }

    @Test
    void readTriviaQuestionsURLShouldNotBeBullWhenValidFileNameIsGiven() {
        URL resource = service.readTriviaQuestionsURL("TriviaQuestions.txt");
        assertNotNull(resource);
    }

    @Test
    void readTriviaQuestionsURLShouldBeBullWhenInValidFileNameIsGiven() {
        URL resource = service.readTriviaQuestionsURL("TriviaQuestions1.txt");
        assertNull(resource);
    }

    @Test
    void readTriviaQuestionsShouldGiveValidPathWhenValidFileNameIsGiven() throws URISyntaxException {
        Path path = service.readTriviaQuestions("TriviaQuestions.txt");

        assertNotNull(path);
    }

    @Test
    void readTriviaQuestionsShouldGiveNullPathWhenValidFileNameIsGiven() throws URISyntaxException {
        Path path = service.readTriviaQuestions("TriviaQuestions1.txt");
        assertNull(path);
    }

    @Test
    void readTriviaQuestionsAsStringShouldNotGiveEmptyListWhenFileIsNotEmpty() throws IOException, URISyntaxException {
        List<String> questions = service.readTriviaQuestionsAsString();
        assertFalse(questions.isEmpty());
    }

    @Test
    void readTriviaQuestionsAsStringShouldGiveAllTheQuestionsWhenFileIsNotEmpty() throws IOException, URISyntaxException {
        List<String> questions = service.readTriviaQuestionsAsString();
        assertEquals(10, questions.size());
    }

    @Test
    void readTriviaQuestionsShouldNotGiveEmptyListWhenFileIsNotEmpty() throws IOException, URISyntaxException {
        List<TriviaQuestion> questions = service.readTriviaQuestions();
        assertFalse(questions.isEmpty());
    }

    @Test
    void readTriviaQuestionsShouldGiveAllTheQuestionsWhenFileIsNotEmpty() throws IOException, URISyntaxException {
        List<TriviaQuestion> questions = service.readTriviaQuestions();
        assertEquals(10, questions.size());
    }

    @Test
    void buildTriviaQuestionShouldSplitQuestionWhenDataIsValid() {
        TriviaQuestion question = service.buildTriviaQuestion(QUESTION_FIXTURE);
        assertEquals("Question 1?", question.getQuestion());
    }

    @Test
    void buildTriviaQuestionShouldSplitAnswerWhenDataIsValid() {
        TriviaQuestion question = service.buildTriviaQuestion(QUESTION_FIXTURE);
        assertEquals("Answer 1", question.getAnswer());
    }

    @Test
    void buildTriviaQuestionShouldSplitOptionsWhenDataIsValid() {
        TriviaQuestion question = service.buildTriviaQuestion(QUESTION_FIXTURE);
        assertEquals("Answer 1", question.getOptions().get(0));
        assertEquals("Answer 2", question.getOptions().get(1));
        assertEquals("Answer 3", question.getOptions().get(2));
    }
}
