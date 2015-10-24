package waggoner.com.comedyhackday.questions;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import waggoner.com.comedyhackday.Answer;
import waggoner.com.comedyhackday.locations.Location;

/**
 * Created by nathanielwaggoner on 10/24/15.
 */
public class QuestionFactory {

    public static String QUESTION_START ="Q:";
    public static String LOCATION_START ="Location: ";
    public static String ANSWER_START ="A:";
    public static int fucksOffset_start =3;
    public static int fucksOffset_end = 6;
    public static String TAG = QuestionFactory.class.getName();
    public static Question[] generateDataSet(Context ctx, String fileName, Location location){

        ArrayList<Question> questions = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(ctx.getAssets().open(fileName)));

            // do reading, usually loop until end of file reading
            String mLine;
            Question q;
            ArrayList<Answer> answers = new ArrayList<>();
            String theLocaiton = "";
            while ((mLine = reader.readLine()) != null) {
                if(mLine.startsWith(LOCATION_START)) {
                    theLocaiton = mLine.replace(LOCATION_START,"");
                }
                if(!theLocaiton.equals(location.getName())) {
                    continue;
                }

                if(mLine.startsWith(QUESTION_START)) {
                    answers = new ArrayList<>();
                    q = new Question(mLine,answers);
                    questions.add(q);
                } else if(mLine.startsWith(ANSWER_START)) {
                    float fucksGiven = 180f*(Integer.valueOf(mLine.substring(fucksOffset_start,fucksOffset_end))/100f);
                    String answer = mLine.substring(fucksOffset_end+1);
                    answers.add(new Answer("A:"+answer,fucksGiven));
                }
            }
        } catch(IOException e) {
            Log.e(TAG, Log.getStackTraceString(e));
        }
        return questions.toArray( new Question[questions.size()]);
    }
}
