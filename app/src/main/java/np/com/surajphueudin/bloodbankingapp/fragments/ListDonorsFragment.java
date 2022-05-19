package np.com.surajphueudin.bloodbankingapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;

import np.com.surajphueudin.bloodbankingapp.R;
import np.com.surajphueudin.bloodbankingapp.beans.DonorsBean;
import np.com.surajphueudin.bloodbankingapp.adapters.SearchedDonationsAdapter;
import np.com.surajphueudin.bloodbankingapp.utility.HandleError;
import np.com.surajphueudin.bloodbankingapp.volley.MySingleton;

public class ListDonorsFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    public ListDonorsFragment() {

    }

    private void handleDataFetching(View view, RecyclerView recyclerView, String address, String blood_groups){
        String url = "http://10.0.2.2:8000/api/v1/en/auth/users/?address=" + address + "&blood_group=" + blood_groups ;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray data = new JSONArray();

                        // Extracting data field which contains actual array of donors
                        try {
                            data = response.getJSONArray("data");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        DonorsBean[] donorsList = new DonorsBean[data.length()];

                        try {
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject jsonObject = data.getJSONObject(i);

                                String name = jsonObject.getString("fullname");
                                String bloodGroup = jsonObject.getString("blood_group");
                                String location = jsonObject.getString("address");
                                String lastDonation = jsonObject.getString("last_donation");
                                String contact = jsonObject.getString("contact");

                                DonorsBean donor = new DonorsBean();
                                donor.setName(name);
                                donor.setBloodGroup(bloodGroup);
                                donor.setLocation(location);
                                donor.setContact(contact);
                                donor.setLastDonation(lastDonation);

                                donorsList[i] = donor;

                            }
                        } catch (Exception w) {
                            Toast.makeText(getActivity(), w.getMessage(), Toast.LENGTH_LONG).show();
                        }

                        adapter = new SearchedDonationsAdapter(donorsList, view.getContext());
                        recyclerView.setAdapter(adapter);
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_list_donors, container, false);
        String address = getArguments().getString("address");
        String blood_groups = getArguments().getString("blood_groups");

        System.out.println(address);
        System.out.println(blood_groups);

        recyclerView = view.findViewById(R.id.donors_list_recycler_view);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        handleDataFetching(view, recyclerView, address, blood_groups);



        return view;
    }
}