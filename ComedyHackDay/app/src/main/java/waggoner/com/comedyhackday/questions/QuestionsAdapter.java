package waggoner.com.comedyhackday.questions;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import waggoner.com.comedyhackday.Answer;
import waggoner.com.comedyhackday.R;

/**
 * Created by nathanielwaggoner on 10/24/15.
 */
public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.ViewHolder> {
    private Question[] mDataset;

    Context ctx;
    public static ScrollView expandedScroll= null;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        @Bind(R.id.question_text) TextView question;
        @Bind(R.id.answersHolder) LinearLayout answersHolder;
        @Bind(R.id.answers_scroll) ScrollView answersScroll;
        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

        @OnClick(R.id.card_view)
        void expandClick(View v) {
            Log.e("XappTest", "XappTest");
            if(expandedScroll!=null) {
                expandedScroll.setVisibility(View.GONE);
            }
            expandedScroll = answersScroll;
            answersScroll.setVisibility(View.VISIBLE);
        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public  QuestionsAdapter(Question[] myDataset, Context ctx) {
        mDataset = myDataset;
        this.ctx = ctx;
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
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.question.setText(mDataset[position].questionText);
        for(Answer answer: mDataset[position].answers){
            TextView answerText = new TextView(ctx);
            answerText.setText(answer.text);
            holder.answersHolder.addView(answerText);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}