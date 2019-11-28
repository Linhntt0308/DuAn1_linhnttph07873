package com.example.duan1.module;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public interface IappData {
    void toNextQuestion();

    ArrayList<String> getFullAnswer();

    Drawable getImageDrawable();

    String getDescription();

    int getId();

    String getSuggest();

    String getKQ();

    ArrayList<String> getShortAnswer();

    ArrayList<String> getSelectedCharacter();

    int getResCharNum();
}
