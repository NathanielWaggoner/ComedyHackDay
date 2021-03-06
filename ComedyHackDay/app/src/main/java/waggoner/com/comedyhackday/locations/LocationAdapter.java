package waggoner.com.comedyhackday.locations;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import waggoner.com.comedyhackday.R;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {
    private Location[] mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        public interface OnClickListener {
            void onClickListener(int position);
        }
        @Bind(R.id.location_name) TextView location_name;
        @Bind(R.id.card_view) CardView location_card;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

        private OnClickListener mCl;

        public void setOnClickListener(OnClickListener cl) {
            mCl = cl;
        }

        @OnClick(R.id.card_view)
        public void tapped() {
            mCl.onClickListener(getAdapterPosition());
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public LocationAdapter(Location[] myDataset) {
        mDataset = myDataset;
    }

    private LocationList.LocationSelectedListener mLocationSelectedListener;

    public void setOnLocationSelectedListener(LocationList.LocationSelectedListener listener) {
        mLocationSelectedListener = listener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public LocationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.location_card, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.location_name.setText(mDataset[position].getName());
        holder.setOnClickListener(new ViewHolder.OnClickListener() {
            @Override
            public void onClickListener(int position) {
                mLocationSelectedListener.onLocationSelected(mDataset[position]);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}