package np.com.surajphueudin.bloodbankingapp.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

import np.com.surajphueudin.bloodbankingapp.LoginActivity;
import np.com.surajphueudin.bloodbankingapp.R;

public class RequestsIfSigninFragment extends Fragment {
    private TabLayout tab;

    public RequestsIfSigninFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_requests_if_signin, container, false);
        tab = view.findViewById(R.id.tab_layout);

        ReceivedRequestsFragment firstFragment = new ReceivedRequestsFragment();
        MyRequestsFragment secondFragment = new MyRequestsFragment();


        getParentFragmentManager().beginTransaction().replace(R.id.request_tab_container, firstFragment).commit();

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        getParentFragmentManager().beginTransaction().replace(R.id.request_tab_container, firstFragment).commit();
                        break;

                    case 1:
                        getParentFragmentManager().beginTransaction().replace(R.id.request_tab_container, secondFragment).commit();
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