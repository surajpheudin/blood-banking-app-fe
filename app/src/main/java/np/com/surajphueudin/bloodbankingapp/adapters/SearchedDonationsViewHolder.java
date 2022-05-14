package np.com.surajphueudin.bloodbankingapp.adapters;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import np.com.surajphueudin.bloodbankingapp.R;

public class SearchedDonationsViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    TextView bloodGroup;
    TextView location;
    TextView lastDonation;
    Button contact;


    SearchedDonationsViewHolder(View itemView) {
        super(itemView);

        name = (TextView) itemView.findViewById(R.id.name);
        bloodGroup = (TextView) itemView.findViewById(R.id.blood_group);
        location = (TextView) itemView.findViewById(R.id.location);
        lastDonation = (TextView) itemView.findViewById(R.id.lastDonation);
        contact = (Button) itemView.findViewById(R.id.contact);
    }
}