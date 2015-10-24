package waggoner.com.comedyhackday.questions;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import waggoner.com.comedyhackday.Answer;
import waggoner.com.comedyhackday.R;
import waggoner.com.comedyhackday.view.CompassView;

/**
 * Created by nathanielwaggoner on 10/24/15.
 */
public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.ViewHolder> implements View.OnTouchListener{
    private Question[] mDataset;
    static int currentQuestion=-1;
    int lastAnswer;
    CompassView cv;
    Context ctx;
    public static ViewHolder expandedView= null;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        @Bind(R.id.question_text) TextView question;
        @Bind(R.id.answersHolder) LinearLayout answersHolder;
        int position;
//        @Bind(R.id.dividing_line) View dividingLine;
        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

        @OnClick(R.id.card_view)
        void expandClick(View v) {

            if(expandedView!=null) {
                expandedView.answersHolder.setVisibility(View.GONE);
//                expandedView.dividingLine.setVisibility(View.GONE);
            }
            expandedView = this;
//            expandedView.dividingLine.setVisibility(View.VISIBLE);
            answersHolder.setVisibility(View.VISIBLE);
            currentQuestion = position;
        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public  QuestionsAdapter(Question[] myDataset, Context ctx, CompassView cv ) {
        mDataset = myDataset;
        this.cv = cv;
        this.ctx = ctx;
        cv.setOnTouchListener(this);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public QuestionsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_card, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.question.setText(mDataset[position].questionText);
        LayoutInflater inflater = LayoutInflater.from(ctx);
        holder.answersHolder.removeAllViews();
        holder.position = position;

        for(final Answer answer: mDataset[position].answers){
            Button answerText = (Button) inflater.inflate(R.layout.answer_text_view, null);
            answerText.setText(answer.text);
            holder.answersHolder.addView(answerText);
            answerText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cv.animateDegree(answer.fucks_given);
                }
            });
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
    int i = 0;
    float viewRotation;
    double fingerRotation;
    double newFingerRotation;
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        final float x = event.getX();
        final float y = event.getY();

        final float xc = cv.getWidth()/2;
        final float yc = cv.getHeight()/2;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                viewRotation = cv.getRotation();
                fingerRotation = Math.toDegrees(Math.atan2(x - xc, yc - y));
                break;
            case MotionEvent.ACTION_MOVE:
                newFingerRotation = Math.toDegrees(Math.atan2(x - xc, yc - y));

                cv.animateDegree((float)(viewRotation + newFingerRotation - fingerRotation),0,0);
                break;
            case MotionEvent.ACTION_UP:
                fingerRotation = newFingerRotation = 0.0f;
                getBestAnswer();
                break;
        }
        return true;
    }

    public void getBestAnswer() {
        if(expandedView!=null) {
            int answerLoc = -1;
            int i = 0;
            float minDistance = Float.MAX_VALUE;
            for (Answer a: mDataset[currentQuestion].answers) {
                float distance = Math.abs(Math.abs(a.fucks_given)-Math.abs(cv.getDegree()));
                if(distance<minDistance) {
                    minDistance  =distance;
                    answerLoc = i;
                }
                i++;
            }
            if(lastAnswer!=-1) {
                Button oldAnswer = (Button)expandedView.answersHolder.getChildAt(lastAnswer);
                oldAnswer.setTextColor(Color.BLACK);
            }
            lastAnswer = answerLoc;
            Button answer = (Button)expandedView.answersHolder.getChildAt(answerLoc);
            answer.setTextColor(Color.RED);
        }

    }
}