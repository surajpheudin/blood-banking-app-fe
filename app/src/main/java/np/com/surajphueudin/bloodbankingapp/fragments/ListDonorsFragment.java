package np.com.surajphueudin.bloodbankingapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import np.com.surajphueudin.bloodbankingapp.R;
import np.com.surajphueudin.bloodbankingapp.beans.DonorsBean;
import np.com.surajphueudin.bloodbankingapp.adapters.SearchedDonationsAdapter;

public class ListDonorsFragment extends Fragment {
    DonorsBean[] names={
            new DonorsBean("Suraj Pheudin", "B+", "21 Jestha", "3:00 pm", "New Baneshwor, Kathmandu", "4 months ago", "9888888888"),
            new DonorsBean("Joshep Nayab", "A+", "3 March", "5:12pm", "Godawari, Lalitpur","2 months ago", "9888888888"),
    };
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    public ListDonorsFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view =  inflater.inflate(R.layout.fragment_list_donors,container,false);

        recyclerView = view.findViewById(R.id.donors_list_recycler_view);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new SearchedDonationsAdapter(names,view.getContext());
        recyclerView.setAdapter(adapter);
        return view;
    }
}