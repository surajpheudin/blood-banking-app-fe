package np.com.surajphueudin.bloodbankingapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import np.com.surajphueudin.bloodbankingapp.LoginActivity;
import np.com.surajphueudin.bloodbankingapp.R;

public class RequestsNotSigninFragment extends Fragment {
    public RequestsNotSigninFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
         View view = inflater.inflate(R.layout.fragment_requests_not_signin, container, false);

        TextView signinLink = view.findViewById(R.id.signin_link);
        signinLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

         return view;
    }
}