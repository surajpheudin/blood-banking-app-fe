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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import np.com.surajphueudin.bloodbankingapp.HomepageActivity;
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
        TextView name = view.findViewById(R.id.text_name);
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
                            name.setText(response.getJSONObject("data").getString("fullname"));
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


    private void changeAvailability(Boolean available,String token, View view) {
        HashMap<String, Boolean> body = new HashMap<String, Boolean>();
        body.put("available", available);



        String url = "http://10.0.2.2:8000/api/v1/en/auth/update-profile/";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.PUT, url, new JSONObject(body), new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {

                        MyDbHelper db = new MyDbHelper(getContext());

                        Cursor cursor = db.selectTokenData();

                        String uid = "";

                        while (cursor.moveToNext()) {
                            uid = cursor.getString(1);
                        }

                        if(!uid.equals("")){
                            handleDataFetching(view, uid);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        // TODO: Handle error
                        System.out.println(volleyError.toString());
                        HandleError.handleVolleyError(volleyError, getContext());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("Authorization", "Token " + token);
                return headers;
            }
        };

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);



        TextView logoutBtn = view.findViewById(R.id.logout_btn);
        SwitchCompat availableSwitch = view.findViewById(R.id.switch_available);

        MyDbHelper db = new MyDbHelper(getContext());

        Cursor cursor = db.selectTokenData();

        String uid = "";
        String token = ""
;
        while (cursor.moveToNext()) {
            uid = cursor.getString(1);
            token = cursor.getString(4);

        }

        if(!uid.equals("")){
            handleDataFetching(view, uid);
        }

        String finalToken = token;

        availableSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                changeAvailability(b, finalToken, view);
            }
        });




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