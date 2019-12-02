package com.example.duan1.module;

import java.util.ArrayList;
import java.util.Arrays;

public class QuestionInfor {
    private ArrayList<String> fullAnswer;
    private String imagePath;
    private String description;
    private int id;
    private String suggest;
    private ArrayList<String> shortAnswer;
    private String kq;

    public QuestionInfor(String[] str) {
        fullAnswer = new ArrayList<>(Arrays.asList(str[0].split(":")[1].split(",")));
        imagePath = str[1].split(":")[1];
        description = str[2].split(":")[1];
        id = Integer.parseInt(str[3].split(":")[1]);
        suggest = str[4].split(":")[1];
        shortAnswer = new ArrayList<>(Arrays.asList(str[5].split(":")[1].split(",")));
        kq = str[5].split(":")[1].replace(",","");
    }

    public ArrayList<String> getFullAnswer() {
        return fullAnswer;
    }

    public String getKq() {
        return kq;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getSuggest() {
        return suggest;
    }

    public ArrayList<String> getShortAnswer() {
        return shortAnswer;
    }
}
