package com.example.duan1.module;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public interface appData  {
    void toNextQuestion();
    Drawable getImageDrawable();
    int getId();

    String getSuggest();

    String getKQ();

    ArrayList<String> getShortAnswer();
    //

    void setT();
    void tl();
    void goToSupportDialog();
    void setIvMusic();
    void fullScreen();

    void goToItemDialog();
}
