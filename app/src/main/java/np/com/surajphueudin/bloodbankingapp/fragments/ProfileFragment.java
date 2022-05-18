package np.com.surajphueudin.bloodbankingapp.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import np.com.surajphueudin.bloodbankingapp.LoginActivity;
import np.com.surajphueudin.bloodbankingapp.R;
import np.com.surajphueudin.bloodbankingapp.adapters.SearchedDonationsAdapter;
import np.com.surajphueudin.bloodbankingapp.beans.DonorsBean;
import np.com.surajphueudin.bloodbankingapp.sqlite.MyDbHelper;
import np.com.surajphueudin.bloodbankingapp.utility.HandleError;
import np.com.surajphueudin.bloodbankingapp.utility.Utility;
import np.com.surajphueudin.bloodbankingapp.volley.MySingleton;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }
    private void handleDataFetching(View view, String uid){
        TextView bloodGroup = view.findViewById(R.id.text_blood_group);
        TextView noOfDonations = view.findViewById(R.id.text_no_of_dontaions);
        TextView lastDonation = view.findViewById(R.id.text_last_donation);
        SwitchCompat available = view.findViewById(R.id.switch_available);

        String url = "http://10.0.2.2:8000/api/v1/en/auth/user/" + uid + "/";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response.toString());

                        try {
                            bloodGroup.setText(Utility.toBloodGroupShortcut(response.getJSONObject("data").getString("blood_group")));
                            noOfDonations.setText("0");
                            lastDonation.setText(Utility.formatDate(response.getJSONObject("data").getString("last_donation")));
                            available.setChecked(Boolean.parseBoolean(response.getJSONObject("data").getString("available")));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        // TODO: Handle error
                        System.out.println(volleyError.toString());
                        HandleError.handleVolleyError(volleyError, getContext());
                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);



        TextView logoutBtn = view.findViewById(R.id.logout_btn);

        MyDbHelper db = new MyDbHelper(getContext());

        Cursor cursor = db.selectTokenData();

        String uid = "";

        while (cursor.moveToNext()) {
            uid = cursor.getString(1);
        }

        if(!uid.equals("")){
            handleDataFetching(view, uid);
        }


        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDbHelper db = new MyDbHelper(getContext());
                try {
                    db.deleteTokenData();
                }catch (Exception e){
                    e.printStackTrace();
                }

                Intent intent = new Intent(view.getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}