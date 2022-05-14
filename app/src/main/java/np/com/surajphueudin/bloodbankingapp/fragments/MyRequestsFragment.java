package np.com.surajphueudin.bloodbankingapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import np.com.surajphueudin.bloodbankingapp.R;
import np.com.surajphueudin.bloodbankingapp.adapters.MyRequestAdapter;
import np.com.surajphueudin.bloodbankingapp.adapters.RequestDonorAdapter;
import np.com.surajphueudin.bloodbankingapp.beans.RequesterBean;

public class MyRequestsFragment extends Fragment {

    RequesterBean[] names={
            new RequesterBean("Suraj Pheudin", "B+", "21 Jestha", "3:00 pm", "New Baneshwor, Kathmandu", "4 months ago", "9888888888"),
            new RequesterBean("Joshep Nayab", "A+", "3 March", "5:12pm", "Godawari, Lalitpur","2 months ago", "9888888888"),
    };

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    MyRequestAdapter adapter;

    public MyRequestsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_requests, container, false);

        recyclerView = view.findViewById(R.id.request_recycler_view);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MyRequestAdapter(names, view.getContext());
        recyclerView.setAdapter(adapter);

        return view;
    }
}
