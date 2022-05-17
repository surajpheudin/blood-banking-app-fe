package np.com.surajphueudin.bloodbankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import np.com.surajphueudin.bloodbankingapp.utility.HandleError;
import np.com.surajphueudin.bloodbankingapp.utility.Utility;
import np.com.surajphueudin.bloodbankingapp.volley.MySingleton;

public class SignupActivity extends AppCompatActivity {

    public void sendVerificationEmail(HashMap<String, String> body, EditText[] inputFields, Spinner[] spinnerFields) {
        String url = "http://10.0.2.2:8000/api/v1/en/auth/verify-email/";

        HashMap<String, String> data = new HashMap<String, String>();
        data.put("email", body.get("email"));

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, new JSONObject(data), new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        // Reset Input Fields
                        for (EditText field : inputFields) {
                            field.setText("");
                        }
                        ;
                        for (Spinner field : spinnerFields) {
                            field.setSelection(0);
                        }
                        ;

                        Intent intent = new Intent(SignupActivity.this, EmailVerificationActivity.class);
                        intent.putExtra("body", body);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        // TODO: Handle error
                        System.out.println(volleyError.toString());
                        HandleError.handleVolleyError(volleyError, SignupActivity.this);
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(SignupActivity.this).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        TextView signinLink = findViewById(R.id.signin_link);
        Button signupContinueButton = findViewById(R.id.signup_continue_btn);
        Spinner locations = findViewById(R.id.address_spinner);
        Spinner bloodGroups = findViewById(R.id.blood_group_spinner);
        EditText inputEmail = findViewById(R.id.input_email);
        EditText inputUsername = findViewById(R.id.input_username);
        EditText inputFullname = findViewById(R.id.input_fullname);
        EditText inputContact = findViewById(R.id.input_contact);
        EditText inputPassword = findViewById(R.id.input_password);
        EditText inputConfirmPassword = findViewById(R.id.input_confirm_password);

        EditText[] inputFields = {inputEmail, inputUsername, inputFullname, inputContact, inputPassword, inputConfirmPassword};
        Spinner[] spinnerFiedls = {locations, bloodGroups};

        ArrayAdapter<String> addressAdapter = new ArrayAdapter<String>(SignupActivity.this, android.R.layout.simple_spinner_dropdown_item, Utility.getNepalDistricts());
        ArrayAdapter<String> bloodGroupAdapter = new ArrayAdapter<String>(SignupActivity.this, android.R.layout.simple_spinner_dropdown_item, Utility.
                getBloodGroups());

        locations.setAdapter(addressAdapter);
        bloodGroups.setAdapter(bloodGroupAdapter);

        signinLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        signupContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString();
                String username = inputUsername.getText().toString();
                String fullname = inputFullname.getText().toString();
                String contact = inputContact.getText().toString();
                String password = inputPassword.getText().toString();
                String confirmPassword = inputConfirmPassword.getText().toString();
                String address = locations.getSelectedItem().toString();
                String bloodGroup = bloodGroups.getSelectedItem().toString();


                if (email.equals("") || username.equals("") || fullname.equals("") || contact.equals("") || password.equals("") || confirmPassword.equals("")) {
                    Toast.makeText(SignupActivity.this, "Please provide all required fields", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(SignupActivity.this, "Password and confirm password didn't match", Toast.LENGTH_SHORT).show();

                } else {
                    HashMap<String, String> params = new HashMap<String, String>();
                    params.put("email", email);
                    params.put("username", username);
                    params.put("fullname", fullname);
                    params.put("contact", contact);
                    params.put("password", password);
                    params.put("confirm_password", confirmPassword);
                    params.put("address", address);
                    params.put("blood_group", Utility.toReverseBloodGroupShortcut(bloodGroup));

                    sendVerificationEmail(params, inputFields, spinnerFiedls);
                }

            }
        });
    }
}