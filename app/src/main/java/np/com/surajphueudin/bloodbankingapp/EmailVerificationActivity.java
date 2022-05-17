package np.com.surajphueudin.bloodbankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import np.com.surajphueudin.bloodbankingapp.volley.MySingleton;

public class EmailVerificationActivity extends AppCompatActivity {

    public void registerUser(HashMap<String, String> body, EditText[] inputFields) {
        String url = "http://10.0.2.2:8000/api/v1/en/auth/register/";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, new JSONObject(body), new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        // Reset Input Fields
                        for (EditText field : inputFields) {
                            field.setText("");
                        }
                        ;
                        Intent intent = new Intent(EmailVerificationActivity.this, HomepageActivity.class);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        // TODO: Handle error
                        System.out.println(volleyError.toString());
                        HandleError.handleVolleyError(volleyError, EmailVerificationActivity.this);
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
        MySingleton.getInstance(EmailVerificationActivity.this).addToRequestQueue(jsonObjectRequest);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);

        Button registerBtn = findViewById(R.id.register_btn);
        EditText inputCode = findViewById(R.id.input_code);
        EditText[] inputFields = {inputCode};

        Intent intent = getIntent();
        HashMap<String, String> body = (HashMap<String, String>) intent.getSerializableExtra("body");

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                body.put("token", inputCode.getText().toString());
                System.out.println(body);

                if (body.get("token").equals("")) {
                    Toast.makeText(EmailVerificationActivity.this, "Verification code can't be empty", Toast.LENGTH_SHORT).show();
                }else if(body.get("token").length() != 6){
                    Toast.makeText(EmailVerificationActivity.this, "Verification code must be of 6 digits", Toast.LENGTH_SHORT).show();
                }
                else {
                    registerUser(body, inputFields);

                }
            }
        });


    }
}