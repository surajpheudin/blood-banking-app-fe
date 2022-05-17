package np.com.surajphueudin.bloodbankingapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;

import com.google.android.material.button.MaterialButtonToggleGroup;

import np.com.surajphueudin.bloodbankingapp.R;


public class FindDonorsFragment extends Fragment {

    public FindDonorsFragment() {
        // Required empty public constructor
    }

    final String[] LOCATIONS
            = {
            "achham",
            "arghakhanchi",
            "baglung",
            "baitadi",
            "bajhang",
            "bajura",
            "banke",
            "bara",
            "bardiya",
            "bhaktapur",
            "bhojpur",
            "chitwan",
            "dadeldhura",
            "dailekh",
            "dang deukhuri",
            "darchula",
            "dhading",
            "dhankuta",
            "dhanusa",
            "dholkha",
            "dolpa",
            "doti",
            "gorkha",
            "gulmi",
            "humla",
            "ilam",
            "jajarkot",
            "jhapa",
            "jumla",
            "kailali",
            "kalikot",
            "kanchanpur",
            "kapilvastu",
            "kaski",
            "kathmandu",
            "kavrepalanchok",
            "khotang",
            "lalitpur",
            "lamjung",
            "mahottari",
            "makwanpur",
            "manang",
            "morang",
            "mugu",
            "mustang",
            "myagdi",
            "nawalparasi",
            "nuwakot",
            "okhaldhunga",
            "palpa",
            "panchthar",
            "parbat",
            "parsa",
            "pyuthan",
            "ramechhap",
            "rasuwa",
            "rautahat",
            "rolpa",
            "rukum",
            "rupandehi",
            "salyan",
            "sankhuwasabha",
            "saptari",
            "sarlahi",
            "sindhuli",
            "sindhupalchok",
            "siraha",
            "solukhumbu",
            "sunsari",
            "surkhet",
            "syangja",
            "tanahu",
            "taplejung",
            "terhathum",
            "udayapur"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_donors, container, false);

        Spinner locations = view.findViewById(R.id.spinner);
        Button button = view.findViewById(R.id.search_donor_btn);
        MaterialButtonToggleGroup bloodGroupBtnGroup = view.findViewById(R.id.toggle_button_group);


        ArrayAdapter adapter = new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_dropdown_item, LOCATIONS);

        locations.setAdapter(adapter);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListDonorsFragment()).commit();
            }
        });

        return view;
    }
}