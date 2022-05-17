package np.com.surajphueudin.bloodbankingapp.utility;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

public class HandleError {

    public static void handleVolleyError(VolleyError volleyError, Context context) {
        System.out.println(volleyError.toString());

        if (volleyError instanceof TimeoutError || volleyError instanceof NoConnectionError) {
            Toast.makeText(context, "No Connection/Communication Error!", Toast.LENGTH_SHORT).show();

        } else if (volleyError instanceof AuthFailureError) {
            Toast.makeText(context, "Authentication/ Auth Error!", Toast.LENGTH_SHORT).show();
        } else if (volleyError instanceof ServerError) {
            Toast.makeText(context, "Server Error!", Toast.LENGTH_SHORT).show();
        } else if (volleyError instanceof NetworkError) {
            Toast.makeText(context, "Network Error!", Toast.LENGTH_SHORT).show();
        } else if (volleyError instanceof ParseError) {
            Toast.makeText(context, "Parse Error!", Toast.LENGTH_SHORT).show();
        }
    }
}

