package waggoner.com.comedyhackday.locations;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import waggoner.com.comedyhackday.R;


public class LocationList extends Fragment {

    public interface  LocationSelectedListener {
        void onLocationSelected(Location locationName);
    }

    Location[] dataSet = LocationFactory.getLocations();

    @Bind(R.id.location_list_view)
    RecyclerView mRecyclerView;

    LocationAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    public static LocationList createInstance() {
        return new LocationList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.location_list, container, false);
        ButterKnife.bind(this, view);

        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new LocationAdapter(dataSet);
        mAdapter.setOnLocationSelectedListener(ls);
        mRecyclerView.setAdapter(mAdapter);


        return view;
    }

    LocationSelectedListener ls;

    public void setOnLocationSelectedListener(LocationSelectedListener lsl) {
        ls = lsl;

        if (mAdapter != null) {
            mAdapter.setOnLocationSelectedListener(ls);
        }
    }
}
