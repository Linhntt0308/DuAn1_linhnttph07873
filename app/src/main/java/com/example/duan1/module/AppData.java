package com.example.duan1.module;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AppData implements IappData {
    public static String[] questionData;
    public static int questionNumber = -1;
    private static final String QUESTION_PATH = "data/question.txt";
    private Context context;
public  AppData(Context context) throws Exception{
    this.context = context;
    // read question data
    String str = "";
    StringBuffer buf = new StringBuffer();
    InputStream is = context.getAssets().open(QUESTION_PATH);
    try {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        if (is != null) {
            while ((str = reader.readLine()) != null) {
                buf.append(str + "\n");
            }
        }
    } finally {
        try {
            is.close();
        } catch (Throwable ignore) {
        }
    }

    // save question data
    questionData = buf.toString().split("\n\n");
}
    @Override
    public void toNextQuestion() {

    }

    @Override
    public ArrayList<String> getFullAnswer() {
        return null;
    }

    @Override
    public Drawable getImageDrawable() {

        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getSuggest() {
        return null;
    }

    @Override
    public String getKQ() {
        return null;
    }

    @Override
    public ArrayList<String> getShortAnswer() {
        return null;
    }

    @Override
    public ArrayList<String> getSelectedCharacter() {
        return null;
    }

    @Override
    public int getResCharNum() {
   return this.getShortAnswer().size();
    }
}
