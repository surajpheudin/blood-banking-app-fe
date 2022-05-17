package np.com.surajphueudin.bloodbankingapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import np.com.surajphueudin.bloodbankingapp.R;
import np.com.surajphueudin.bloodbankingapp.beans.DonorsBean;
import np.com.surajphueudin.bloodbankingapp.adapters.RecentDonationsAdapter;

public class RecentDonationsFragment extends Fragment {
    DonorsBean[] names = {
            new DonorsBean("Random Person", "B+", "21 Jestha", "3:00 pm", "New Baneshwor, Kathmandu", "4 months ago", "9888888888"),
            new DonorsBean("Hello World", "A+", "3 March", "5:12pm", "Godawari, Lalitpur", "2 months ago", "9888888888"),
    };

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    RecentDonationsAdapter adapter;

    public RecentDonationsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_your_donations, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecentDonationsAdapter(names, view.getContext());
        recyclerView.setAdapter(adapter);

        return view;
    }
}