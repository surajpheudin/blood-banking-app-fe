package np.com.surajphueudin.bloodbankingapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import np.com.surajphueudin.bloodbankingapp.R;
import np.com.surajphueudin.bloodbankingapp.beans.DonorsBean;
import np.com.surajphueudin.bloodbankingapp.utility.Utility;

public class SearchedDonationsAdapter extends RecyclerView.Adapter<SearchedDonationsViewHolder> {

    DonorsBean[] donors;
    Context context;

    public SearchedDonationsAdapter(DonorsBean[] donors, Context context) {
        this.donors = donors;
        this.context = context;
    }

    @NonNull
    @Override
    public SearchedDonationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View donationsListView = layoutInflater.inflate(R.layout.search_donations_items, parent, false);

        return new SearchedDonationsViewHolder(donationsListView);
    }

    @Override
    public void
    onBindViewHolder(final SearchedDonationsViewHolder viewHolder, final int position) {
        viewHolder.name.setText(donors[position].getName());
        viewHolder.bloodGroup.setText(Utility.toBloodGroupShortcut(donors[position].getBloodGroup()));
        viewHolder.location.setText(donors[position].getLocation());
        viewHolder.lastDonation.setText(Utility.formatDate(donors[position].getLastDonation()));
    }

    @Override
    public int getItemCount() {
        return donors.length;
    }

}