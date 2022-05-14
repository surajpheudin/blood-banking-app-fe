package np.com.surajphueudin.bloodbankingapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import np.com.surajphueudin.bloodbankingapp.R;

public class RequestsFragment extends Fragment {

    public RequestsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_requests, container, false);

        RequestsNotSigninFragment firstFragment = new RequestsNotSigninFragment();
        RequestsIfSigninFragment secondFragment = new RequestsIfSigninFragment();

        getParentFragmentManager().beginTransaction().replace(R.id.request_container, firstFragment).commit();


        if (true) {
            getParentFragmentManager().beginTransaction().replace(R.id.request_container, secondFragment).commit();
        }


        return view;
    }
}