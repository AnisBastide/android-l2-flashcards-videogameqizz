package com.theo.videogameqizz;

public class Answer {
    private String goodAnswer;
    private String otherAnswerOne;
    private String otherAnswerTwo;

    public String getGoodAnswer() {
        return goodAnswer;
    }

    public String getOtherAnswerOne() {
        return otherAnswerOne;
    }

    public String getOtherAnswerTwo() {
        return otherAnswerTwo;
    }

    public Answer(String goodAnswer, String otherAnswerOne, String otherAnswerTwo) {
        this.goodAnswer = goodAnswer;
        this.otherAnswerOne = otherAnswerOne;
        this.otherAnswerTwo = otherAnswerTwo;
    }
}
