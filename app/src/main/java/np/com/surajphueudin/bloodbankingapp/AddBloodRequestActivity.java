package np.com.surajphueudin.bloodbankingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import np.com.surajphueudin.bloodbankingapp.fragments.DatePickerFragment;
import np.com.surajphueudin.bloodbankingapp.fragments.TimePickerFragment;

public class AddBloodRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_blood_request);

        String[] country = {"India", "USA", "China", "Japan", "Other"};

        Spinner locations = findViewById(R.id.spinner);
        Button openDatePickerBtn = findViewById(R.id.date_picker_btn);
        Button openTimePickerBtn = findViewById(R.id.time_picker_btn);

        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, country);

        locations.setAdapter(adapter);

        openDatePickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        openTimePickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

    }
}