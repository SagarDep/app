package com.stackerz.app;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ed on 19/11/14.
 */
public class NovaJSON extends Activity {

    String novaJSON;
    String nova;
    String auth;
    RequestQueue queue = null;

    public static NovaJSON parser = null;

    public static NovaJSON shared(){
        if (parser  == null){
            parser  = new NovaJSON();
        }
        return parser ;
    }

    public String getNovaJSON() {
        return novaJSON;
    }

    public void setNovaJSON(String novaJSON) {
        this.novaJSON = novaJSON;
    }

    public String getNova() {
        return nova;
    }

    public void setNova(String nova) {
        this.nova = nova;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String receiveData (String novaURL, String authToken){
        setNova(novaURL);
        setAuth(authToken);
        getJSON();
        getNovaJSON();
        return novaJSON;
    }

    public void getJSON() {
        //SharedPreferences shPref = new ObscuredSharedPreferences(this, this.getSharedPreferences("Login_Credentials", Context.MODE_PRIVATE));
        //authToken = shPref.getString("AuthToken",authToken);
        //novaURL = shPref.getString("NovaURL",novaURL);
        final String authToken = getAuth();
        String novaURL = getNova();
        novaURL = novaURL+"/servers";


        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, novaURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Nova on Response", response.toString());
                        setNovaJSON(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d("Nova on Error", "Error: " + error.getMessage());
                        setNovaJSON(error.toString());
                    }
                }
        ) {
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("X-Auth-Token", authToken);
                params.put("User-Agent", "stackerz");
                params.put("Accept", "application/json");
                params.put("Content-Type", "application/json; charset=utf-8");
                return params;
            }

        };


        queue = VolleySingleton.getInstance(this).getRequestQueue();
        //VolleySingleton.getInstance(this).addToRequestQueue(getRequest);
        queue.add(getRequest);
    }
}


/**
        //RequestQueue queue = Volley.newRequestQueue(this);
        //queue.add(getRequest);
        RequestQueue queue = VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        //VolleySingleton.getInstance(this).addToRequestQueue(getRequest);
        queue.add(getRequest);
    }

 JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(novaURL,
 new Response.Listener<JSONArray>(){
@Override
public void onResponse(JSONArray response) {
Log.d("Nova", response.toString());
setNovaJSON(response.toString());
}
}, new Response.ErrorListener() {
@Override
public void onErrorResponse(VolleyError error) {
VolleyLog.d("Nova", "Error: " + error.getMessage());
Log.d("Nova", error.toString());
}
}
 ){
 public Map<String, String> getHeaders() throws AuthFailureError {
 Map<String, String> params = new HashMap<String, String>();
 params.put("X-Auth-Token", authToken);
 params.put("User-Agent", "stackerz");
 params.put("Accept", "application/json");
 params.put("Content-Type", "application/json; charset=utf-8");
 return params;
 }
 };

        StringRequest getRequest = new StringRequest(Request.Method.GET,
                novaURL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("Nova", response.toString());
                setNovaJSON(response.toString());


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Nova", "Error: " + error.getMessage());
            }
        }) {
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("X-Auth-Token", authToken);
                params.put("User-Agent", "stackerz");
                params.put("Accept", "application/json");
                params.put("Content-Type", "application/json; charset=utf-8");
                return params;
            }
        };**/


