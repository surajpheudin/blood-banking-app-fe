package np.com.surajphueudin.bloodbankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import np.com.surajphueudin.bloodbankingapp.sqlite.MyDbHelper;
import np.com.surajphueudin.bloodbankingapp.utility.HandleError;
import np.com.surajphueudin.bloodbankingapp.volley.MySingleton;

public class LoginActivity extends AppCompatActivity {

    private void postLogin(HashMap<String, String> body, EditText[] inputFields, MyDbHelper myDbHelper) {
        String url = "http://10.0.2.2:8000/api/v1/en/auth/login/";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, new JSONObject(body), new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        // Reset Input Fields
                        for (EditText field : inputFields) {
                            field.setText("");
                        }
                        ;
                        String id, fullname, email, token;
                        try {
                            id = response.getJSONObject("data").getString("id");
                            fullname = response.getJSONObject("data").getString("fullname");
                            email = response.getJSONObject("data").getString("email");
                            token = response.getJSONObject("data").getString("token");

                            myDbHelper.insertTokenData(id, fullname, email, token);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Intent intent = new Intent(LoginActivity.this, HomepageActivity.class);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        // TODO: Handle error
                        System.out.println(volleyError.toString());
                        HandleError.handleVolleyError(volleyError, LoginActivity.this);
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
        MySingleton.getInstance(LoginActivity.this).addToRequestQueue(jsonObjectRequest);
    }

    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        MyDbHelper myDbHelper = new MyDbHelper(this);

        TextView signupLink = findViewById(R.id.signup_link);
        Button loginButton = findViewById(R.id.login_btn);
        EditText emailInput = findViewById(R.id.input_email);
        EditText passwordInput = findViewById(R.id.input_password);

        EditText[] inputFields = {emailInput, passwordInput};

        signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();

                if (email.equals("") && password.equals("")) {
                    Toast.makeText(LoginActivity.this, "Email and password are required", Toast.LENGTH_SHORT).show();
                } else if (email.equals("")) {
                    Toast.makeText(LoginActivity.this, "Email is required", Toast.LENGTH_SHORT).show();
                } else if (password.equals("")) {
                    Toast.makeText(LoginActivity.this, "Password is required", Toast.LENGTH_SHORT).show();
                } else {
                    HashMap<String, String> params = new HashMap<String, String>();

                    // on below line we are passing our key
                    // and value pair to our parameters.
                    params.put("email", email);
                    params.put("password", password);
                    postLogin(params, inputFields, myDbHelper);
                }
            }
        });
    }
}