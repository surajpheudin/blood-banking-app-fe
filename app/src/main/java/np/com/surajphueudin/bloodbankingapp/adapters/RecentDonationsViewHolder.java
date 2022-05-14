package np.com.surajphueudin.bloodbankingapp.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import np.com.surajphueudin.bloodbankingapp.R;

public class RecentDonationsViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    TextView bloodGroup;
    TextView date;
    TextView time;
    TextView location;

    RecentDonationsViewHolder(View itemView) {
        super(itemView);

        name = (TextView) itemView.findViewById(R.id.name);
        bloodGroup = (TextView) itemView.findViewById(R.id.blood_group);
        date = (TextView) itemView.findViewById(R.id.date);
        time = (TextView) itemView.findViewById(R.id.time);
        location = (TextView) itemView.findViewById(R.id.location);
    }
}