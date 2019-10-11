package com.example.vastujsonparsing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String JSON_URL = "https://test-nbfc.vastucorp.com/api/sync-master";
    String created_at,value;
    private List<Model> modelList;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        modelList = new ArrayList<>();

        volleyUsingPost();
    }

    private void volleyUsingPost() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("response",response);

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray jsonArray = jsonObject.getJSONArray("sync");


                    for (int i=0; i<jsonArray.length();i++)
                    {
                        JSONObject object = jsonArray.getJSONObject(i);

                        String id = object.getString("id");
                        Log.d("id",id);


                        String table_name = object.getString("table_name");
                        Log.d("table_name",table_name);

                        JSONArray jsonObjectJSONArray = object.getJSONArray("data_masters");

                        for (int j=0; j<jsonObjectJSONArray.length();j++)
                        {
                            JSONObject arrayJSONObject = jsonObjectJSONArray.getJSONObject(j);
                            created_at = arrayJSONObject.getString("id");
                            Log.d("created_at",created_at);

                            value = arrayJSONObject.getString("value");
                            Log.d("value",value);

                        }

                        Model model = new Model();
                        model.setId(id);
                        model.setTable_name(table_name);
                        model.setData_masters(created_at);
                        model.setValue(value);
                        modelList.add(model);



                    }
                    adapter = new RecyclerAdapter(modelList, MainActivity.this);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            protected Map<String,String> getParams()
            {
                Map<String, String> params = new HashMap<>();
                params.put("id","150");
                params.put("username","chetan.barhate@vastuhfc.com");
                params.put("password","123456");
                params.put("deviceid","184588a53b3cd749");
                params.put("version","2.4.9");
                params.put("app_name","pulse_nbfc");
                params.put("token","eA1T36pwzmM:APA91bG0fhdRn_CdrYo4u4PE2Vsz6yygIxWPoYaPtXF_UvTbnfE7p-MSh_huBA5bb1iIO16-HPD1Sy7UpZjpUDzag8L37Kj6OUV14kgkSZGOd60_6s5OZOT81S7-eiiJLM1GslF5myUw");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
