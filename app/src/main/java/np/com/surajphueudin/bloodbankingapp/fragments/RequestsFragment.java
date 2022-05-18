package np.com.surajphueudin.bloodbankingapp.fragments;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import np.com.surajphueudin.bloodbankingapp.R;
import np.com.surajphueudin.bloodbankingapp.sqlite.MyDbHelper;

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

        MyDbHelper db = new MyDbHelper(getContext());
        Cursor cursor = db.selectTokenData();

        String token = "";

        while (cursor.moveToNext()) {
            token = cursor.getString(4);
        }

        if (token.equals("")) {
            getParentFragmentManager().beginTransaction().replace(R.id.request_container, firstFragment).commit();

        } else {
            getParentFragmentManager().beginTransaction().replace(R.id.request_container, secondFragment).commit();

        }

        return view;
    }
}