package waggoner.com.comedyhackday.questions;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import waggoner.com.comedyhackday.R;
import waggoner.com.comedyhackday.view.CompassView;


/**
 * Created by nathanielwaggoner on 10/24/15.
 */
public class QuestionsList extends Fragment {
    Question[] dataSet;

    //= new String[] {"Who is that man?","Why did marion barry have to die?","What should I do when a duck poops on me?","Can I eat...?","I was offered drugs and I should?","I ran into my ex and...?"};
    @Bind(R.id.my_recycler_view)
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    @Bind(R.id.compass_view)
    CompassView cView;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.questions_layout, container, false);
        ButterKnife.bind(this, view);
        dataSet = QuestionFactory.generateDataSet(this.getActivity(), "gtfo.txt");
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        // specify an adapter (see also next example)
        mAdapter = new QuestionsAdapter(dataSet,this.getActivity(), cView);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override public void onResume() {
        super.onResume();
        cView.animateDegree(180f, 1000, 1f);
    }
}
