package com.example.ecommerce.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static com.android.volley.toolbox.Volley.newRequestQueue;

public class productapi extends AppCompatActivity {

StringRequest strreq ;
String pid ;
String pname ;
ListView mylist ;
RequestQueue q  ;
ArrayAdapter<String>  adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productapi);

        q = newRequestQueue(this) ;
        loadproducts() ;
    }

    void loadproducts()
    {

        strreq = new StringRequest(Request.Method.GET, Config.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mylist = (ListView) findViewById(R.id.listview);
                        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
                        mylist.setAdapter(adapter);
                        try {

                            JSONObject jobj = new JSONObject(response);
                            JSONArray cp = jobj.getJSONArray("products");
                            for (int i = 0; i < cp.length(); i++) {
                                try {
                                    JSONObject c = cp.getJSONObject(i);
                                    String name = c.getString("Name");
                                    int id = c.getInt("ID");
                                    adapter.add(id + ": " + name);
                                } catch (JSONException e) {
                                    Toast.makeText(productapi.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }

                        } catch (JSONException ex) {
                            Toast.makeText(productapi.this, ex.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(productapi.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }
        ) ;

        int socketTimeout = 30000; // 30 seconds. You can change it
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        strreq.setRetryPolicy(policy);

        q.add(strreq) ;
    }
}
