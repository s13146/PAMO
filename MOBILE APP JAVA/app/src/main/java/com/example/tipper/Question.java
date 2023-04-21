package com.example.tipper;

public class Question {

    private String question;
    private String[] answers;
    private int correctAnswerIndex;

    public Question(String question, String[] answers, int correctAnswerIndex) {
        this.question = question;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    // Getters and setters

    public String getQuestion() {
        return question;
    }

    public void setQuestionId(String questionId) {
        this.question = questionId;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public void setCorrectAnswerIndex(int correctAnswerIndex) {
        this.correctAnswerIndex = correctAnswerIndex;
    }
}
