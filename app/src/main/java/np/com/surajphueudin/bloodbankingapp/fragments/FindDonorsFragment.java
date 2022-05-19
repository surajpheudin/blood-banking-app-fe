package np.com.surajphueudin.bloodbankingapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.button.MaterialButtonToggleGroup;

import java.util.ArrayList;

import np.com.surajphueudin.bloodbankingapp.R;
import np.com.surajphueudin.bloodbankingapp.utility.Utility;


public class FindDonorsFragment extends Fragment {

    public FindDonorsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_donors, container, false);

        Spinner locations = view.findViewById(R.id.spinner);
        Button button = view.findViewById(R.id.search_donor_btn);
        MaterialButtonToggleGroup bloodGroupBtnGroup = view.findViewById(R.id.toggle_button_group);
        MaterialButtonToggleGroup bloodGroupBtnGroupTwo = view.findViewById(R.id.toggle_button_group_two);
        final String[] address = {""};
        ArrayList<String> blood_groups = new ArrayList<String>();

        locations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] districts = Utility.getNepalDistricts();
                address[0] = districts[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                address[0] = "";

            }
        });

        bloodGroupBtnGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                Button btn = view.findViewById(checkedId);

                String element = Utility.toReverseBloodGroupShortcut(btn.getText().toString());

                if (isChecked) {
                    blood_groups.add(element);
                } else {
                    blood_groups.remove(element);
                }
            }
        });

        bloodGroupBtnGroupTwo.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                Button btn = view.findViewById(checkedId);

                String element = Utility.toReverseBloodGroupShortcut(btn.getText().toString());

                if (isChecked) {
                    blood_groups.add(element);
                } else {
                    blood_groups.remove(element);
                }
            }
        });


        ArrayAdapter adapter = new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_dropdown_item, Utility.getNepalDistricts());

        locations.setAdapter(adapter);


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ListDonorsFragment fragment = new ListDonorsFragment();
                Bundle args = new Bundle();

                String blood_groups_string = "";

                for (String i : blood_groups) {
                    blood_groups_string = blood_groups_string + i + ",";
                }

                if (address[0].equals("None")) {
                    args.putString("address", "");
                } else {
                    args.putString("address", address[0]);
                }

                args.putString("blood_groups", blood_groups_string);
                fragment.setArguments(args);

                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        });

        return view;
    }
}