package np.com.surajphueudin.bloodbankingapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import np.com.surajphueudin.bloodbankingapp.R;
import np.com.surajphueudin.bloodbankingapp.beans.DonorsBean;

public class RecentDonationsAdapter extends RecyclerView.Adapter<RecentDonationsViewHolder> {

    DonorsBean[] donors;
    Context context;

    public RecentDonationsAdapter(DonorsBean[] donors, Context context) {
        this.donors = donors;
        this.context = context;
    }

    @NonNull
    @Override
    public RecentDonationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View donationsListView = layoutInflater.inflate(R.layout.your_or_recent_donations_items, parent, false);

        return new RecentDonationsViewHolder(donationsListView);
    }

    @Override
    public void
    onBindViewHolder(final RecentDonationsViewHolder viewHolder, final int position) {
        viewHolder.name.setText(donors[position].getName());
        viewHolder.bloodGroup.setText(donors[position].getBloodGroup());
        viewHolder.date.setText(donors[position].getDate());
        viewHolder.time.setText(donors[position].getTime());
        viewHolder.location.setText(donors[position].getLocation());
    }

    @Override
    public int getItemCount() {
        return donors.length;
    }

}