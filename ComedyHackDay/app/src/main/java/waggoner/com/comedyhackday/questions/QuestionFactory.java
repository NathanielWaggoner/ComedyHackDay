package waggoner.com.comedyhackday.questions;

import java.util.ArrayList;

import waggoner.com.comedyhackday.Answer;

/**
 * Created by nathanielwaggoner on 10/24/15.
 */
public class QuestionFactory {

    public static Question[] generateDataSet(){
        ArrayList<Answer> q1Answers= new ArrayList<>();
        q1Answers.add(new Answer("A Duck",0));
        q1Answers.add(new Answer("Sonic the Hedgehog",50));
        q1Answers.add(new Answer("Your name is Robert Paulson",100));
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question("Who am I?",q1Answers));
        questions.add(new Question("Who am I?",q1Answers));
        return questions.toArray(new Question[questions.size()]);
    }
}
