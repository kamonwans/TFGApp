package com.project.finalyear.thaispellinggame.model;

/**
 * Created by kamonwan on 2/9/2018.
 */

public class GameFive {
    String word;
    String type;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GameFive() {

    }

    public GameFive(String word, String type) {
        this.word = word;
        this.type = type;
    }
}
