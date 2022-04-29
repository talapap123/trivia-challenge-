package com.talapaneni.trivia.service;

import com.talapaneni.trivia.model.TriviaQuestion;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Create a multiple-choice trivia application.
 *
 * Read questions, answers, and distractors (wrong answers) from a file.
 * When a player starts a game
 * Choose questions at random.
 * Display the answer and distractors in a random order.
 * Track the number of correct answers.
 * End the game when the player selects a wrong answer.
 * Constraint
 * Use Java to develop this application
 * Write this file using a file database or local data file rather than a key-value store or a relational database.
 * There is no time limit to complete this exercise - this is chance to demonstrate your great coding and testing skills!
 */
public class TriviaQuestionService {

    /**
     *
     * @param fileName
     * @return
     */
    public URL readTriviaQuestionsURL(String fileName) {
        return TriviaQuestionService.class.getClassLoader().getResource(fileName);
    }

    /**
     *
     * @param fileName
     * @return
     * @throws URISyntaxException
     */
    public Path readTriviaQuestions(String fileName) throws URISyntaxException {
        URL url = readTriviaQuestionsURL(fileName);
        return url == null ? null : Paths.get(url.toURI());
    }

    /**
     *
     * @return
     * @throws IOException
     * @throws URISyntaxException
     */
    public List<String> readTriviaQuestionsAsString() throws IOException, URISyntaxException {
        return Files.readAllLines(readTriviaQuestions("TriviaQuestions.txt"));
    }

    /**
     *
     * @return
     * @throws IOException
     * @throws URISyntaxException
     */
    public List<TriviaQuestion> readTriviaQuestions() throws IOException, URISyntaxException {
        List<TriviaQuestion> questions = new ArrayList<>();
        List<String> questionsList = readTriviaQuestionsAsString();
        for(String question : questionsList) {
            if(question.trim().isEmpty()) {
                continue;
            } else {
                questions.add(buildTriviaQuestion(question));
            }
        }
        return questions;
    }

    /**
     *
     * @param data
     * @return
     */
    public TriviaQuestion buildTriviaQuestion(String data) {
        String[] split = data.split(";");

        List<String> distractions = new ArrayList<>();
        distractions.add(split[1]);
        for(int i=2; i<split.length; i++) {
            distractions.add(split[i]);
        }

        return TriviaQuestion.builder()
                .question(split[0])
                .answer(split[1])
                .options(distractions).build();
    }
}
