package np.com.surajphueudin.bloodbankingapp.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import np.com.surajphueudin.bloodbankingapp.R;

public class HistoryFragment extends Fragment {

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        TabLayout tab = view.findViewById(R.id.tab_layout);

        RecentDonationsFragment firstFragment = new RecentDonationsFragment();
        YourDonationsFragment secondFragment = new YourDonationsFragment();

        getParentFragmentManager().beginTransaction().replace(R.id.tab_container, firstFragment).commit();

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        getParentFragmentManager().beginTransaction().replace(R.id.tab_container, firstFragment).commit();
                        break;

                    case 1:
                        getParentFragmentManager().beginTransaction().replace(R.id.tab_container, secondFragment).commit();
                        break;

                    default:
                        Toast.makeText(view.getContext(), "Wrong Item", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        return view;
    }
}