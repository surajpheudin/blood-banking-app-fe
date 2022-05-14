package np.com.surajphueudin.bloodbankingapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import np.com.surajphueudin.bloodbankingapp.R;

public class FindDonorsFragment extends Fragment {

    public FindDonorsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_donors, container, false);
        String[] country = { "India", "USA", "China", "Japan", "Other"};

        Spinner locations = view.findViewById(R.id.spinner);
        Button button = view.findViewById(R.id.search_donor_btn);

        ArrayAdapter aa = new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_dropdown_item,country);

        locations.setAdapter(aa);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListDonorsFragment()).commit();
            }
        });

        return view;
    }
}