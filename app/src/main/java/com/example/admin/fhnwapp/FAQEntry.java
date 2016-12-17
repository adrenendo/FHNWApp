package com.example.admin.fhnwapp;

import android.util.Log;

/**
 * Created by admin on 17.12.2016.
 */
public class FAQEntry {


    private String question;
    private String answer;

    private final String TAG = "FAQEntry";


    public FAQEntry(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }
}
