package com.example.duan1.module;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AppData implements IappData {
    public static String[] questionData;
    public static int questionNumber = -1;
    private static final String QUESTION_PATH = "data/question.txt";
    private Context context;
    private QuestionInfor questionInfor;
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
        questionNumber++;
        questionInfor = new QuestionInfor(questionData[questionNumber].split(",\n"));
    }

    @Override
    public ArrayList<String> getFullAnswer() {
        return null;
    }

    @Override
    public Drawable getImageDrawable() {
        Drawable drawable = null;
        try {
            InputStream is = context.getAssets().open(questionInfor.getImagePath());
            drawable = Drawable.createFromStream(is, null);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return drawable;

    }

    @Override
    public String getDescription() {
        return questionInfor.getDescription();
    }

    @Override
    public int getId() {
        return questionInfor.getId();
    }

    @Override
    public String getSuggest() {
        return questionInfor.getSuggest();
    }

    @Override
    public String getKQ() {
        return questionInfor.getKq();
    }

    @Override
    public ArrayList<String> getShortAnswer() {
        return questionInfor.getShortAnswer();
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
