package waggoner.com.comedyhackday.questions;

import java.util.ArrayList;

import waggoner.com.comedyhackday.Answer;

/**
 * Created by nathanielwaggoner on 10/24/15.
 */
public class Question {
    String questionText;

    public Question(String questionText, ArrayList<Answer> answers) {
        this.answers = answers;
        this.questionText = questionText;
    }
    ArrayList<Answer> answers;
}
