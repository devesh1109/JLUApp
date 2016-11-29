package module.project.androidbraintech.jluapp.activity;


import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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

import module.project.androidbraintech.jluapp.R;
import module.project.androidbraintech.jluapp.Utilities.MySingletonForImage;
import module.project.androidbraintech.jluapp.Utilities.SimpleDividerItemDecoration;
import module.project.androidbraintech.jluapp.Utilities.SimpleDividerItemGridDecoration;
import module.project.androidbraintech.jluapp.Utilities.UrlAddressHolder;
import module.project.androidbraintech.jluapp.adapters.WhyJluAdapter;
import module.project.androidbraintech.jluapp.containers.ContentWhyJlu;

public class WhyJluActivity extends AppCompatActivity {
     RecyclerView rv;
     WhyJluAdapter mAdapter;

    private ContentWhyJlu objectSet;
    ArrayList<ContentWhyJlu> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_whyjlu_main);



        rv = (RecyclerView)findViewById(R.id.rv_whyjlu);


        getDataSet();
    }


    public void getDataSet() {

        String url=UrlAddressHolder.BASE_ADDRESS+UrlAddressHolder.WHY_JLU;

        data = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,url , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("KEY_RESPONSE",response);
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonData;
                    int flag = jsonObject.getInt("success");
                    if (flag == 1) {
                        jsonData = jsonObject.getJSONArray("list");
                        for (int j = 0; j < jsonData.length(); j++) {
                            JSONObject jsonRow = jsonData.getJSONObject(j);
                            objectSet = new ContentWhyJlu(jsonRow.getInt("wj_id"), jsonRow.getString("wj_title"), jsonRow.getString("wj_text"), jsonRow.getString("wj_url"));
                            data.add(objectSet);

                        }

                        mAdapter = new WhyJluAdapter(data, WhyJluActivity.this);
                        final LinearLayoutManager linear = new LinearLayoutManager(WhyJluActivity.this);
                        linear.setOrientation(LinearLayoutManager.VERTICAL);
                        rv.setLayoutManager(linear);
                        rv.setNestedScrollingEnabled(false);
                        rv.addItemDecoration(new SimpleDividerItemDecoration(20));
                        rv.setAdapter(mAdapter);

                    } else {
                        Log.d("KEY_ELSE",response);
                        Toast.makeText(WhyJluActivity.this, "Unable to Connect .", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    Log.d("Key_CATCH",e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("KEY_ERROR",error.toString());
                Toast.makeText(WhyJluActivity.this, "Error in Connection." + error, Toast.LENGTH_LONG).show();
            }
        });


        Volley.newRequestQueue(WhyJluActivity.this).add(stringRequest);

    }

}
