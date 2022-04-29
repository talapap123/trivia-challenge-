package com.talapaneni.trivia.service;

import com.talapaneni.trivia.model.TriviaQuestion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TriviaChallengeService {
    private TriviaQuestionService questionService;
    private List<TriviaQuestion> questions;
    private BufferedReader reader;
    private int score = 0;
    private int questionIndex = 0;
    private String userInput;

    protected void prepareTriviaQuestions() throws IOException, URISyntaxException {
        questions = getQuestionService().readTriviaQuestions();
        shuffleQuestions();
    }

    protected void shuffleQuestions() {
        Collections.shuffle(questions, new Random(questions.size()));
        for(TriviaQuestion question : questions) {
            shuffleQuestionOptions(question);
        }
    }

    protected void shuffleQuestionOptions(TriviaQuestion question) {
        Collections.shuffle(question.getOptions(), new Random(question.getOptions().size()));
    }

    protected void prepareConsoleReader() {
        reader = new BufferedReader(
                new InputStreamReader(System.in));
    }

    protected void displayScore() {
        System.out.println("-------------------");
        System.out.println("Score - " + score);
        System.out.println("-------------------");
    }

    protected String formatQuestion(TriviaQuestion question) {
        StringBuilder builder = new StringBuilder();
        builder.append(++questionIndex + ". " + question.getQuestion());
        builder.append("\n");

        for(int i=0; i<question.getOptions().size(); i++) {
            builder.append( "\t" + (i+1) + ". " + question.getOptions().get(i));
            builder.append("\n");
        }
        builder.append("Please select the options displayed by giving the corresponding number between 1 and " + question.getOptions().size()  + " : ");
        return builder.toString();
    }

    public void playTrivia(boolean skipUserInput) throws IOException, URISyntaxException {
        score = 0;
        questionIndex = 0;
        prepareTriviaQuestions();
        prepareConsoleReader();

        for(TriviaQuestion question : questions) {
            System.out.println(formatQuestion(question));
            if(skipUserInput) {
                userInput = ""+ (question.getOptions().indexOf(question.getAnswer())+1);
            } else {
                userInput = reader.readLine();
            }
            if(question.getOptions().get(Integer.valueOf(userInput)-1).equals(question.getAnswer())) {
                score++;
            } else {
                break;
            }
        }
        displayScore();
    }

    public TriviaQuestionService getQuestionService() {
        if(questionService == null) {
            questionService = new TriviaQuestionService();
        }
        return questionService;
    }

    public List<TriviaQuestion> getQuestions() {
        return questions;
    }

    public int getScore() {
        return score;
    }

    public int getQuestionIndex() {
        return questionIndex;
    }

    public static void main(String[] args) {
        try {
            TriviaChallengeService service = new TriviaChallengeService();
            service.playTrivia(false);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
