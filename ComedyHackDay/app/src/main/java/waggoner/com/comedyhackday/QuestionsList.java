package waggoner.com.comedyhackday;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import waggoner.com.comedyhackday.view.CompassView;

/**
 * Created by nathanielwaggoner on 10/24/15.
 */
public class QuestionsList extends Fragment {
    String[] dataSet = new String[] {"Who is that man?","Why did marion barry have to die?"};
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    @Bind(R.id.compass_view)
    CompassView cView;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.questions_layout, container, false);
        ButterKnife.bind(this, view);
        mRecyclerView = ButterKnife.findById(view,R.id.my_recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        // specify an adapter (see also next example)
        mAdapter = new QuestionsAdapter(dataSet);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override public void onResume() {
        super.onResume();
        cView.animateDegree(180f, 1000, 1f);
    }
}