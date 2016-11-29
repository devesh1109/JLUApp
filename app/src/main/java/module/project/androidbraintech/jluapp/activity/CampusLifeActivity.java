package module.project.androidbraintech.jluapp.activity;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
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
import java.util.List;

import module.project.androidbraintech.jluapp.R;
import module.project.androidbraintech.jluapp.Utilities.SimpleDividerItemDecoration;
import module.project.androidbraintech.jluapp.Utilities.SimpleDividerItemGridDecoration;
import module.project.androidbraintech.jluapp.Utilities.UrlAddressHolder;
import module.project.androidbraintech.jluapp.adapters.CityOfBhopalAdapter;
import module.project.androidbraintech.jluapp.adapters.PhotosAdapter;
import module.project.androidbraintech.jluapp.containers.ContainerCampusLife;
import module.project.androidbraintech.jluapp.containers.ContentCityOfBhopal;

public class CampusLifeActivity extends AppCompatActivity {

    private RecyclerView rv;
    private RecyclerView.Adapter mAdapter;
    public static final String JSON_URL = UrlAddressHolder.BASE_ADDRESS+UrlAddressHolder.CAMPUS_LIFE;
    private ContentCityOfBhopal objectSet;
    ArrayList<ContentCityOfBhopal> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_life);

        rv = (RecyclerView) findViewById(R.id.rv);
        final LinearLayoutManager linear = new LinearLayoutManager(getApplicationContext());
        linear.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(linear);
        rv.setNestedScrollingEnabled(false);
        rv.addItemDecoration(new SimpleDividerItemDecoration( 50));
        getDataSet();
    }
    public void getDataSet() {

        data = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {
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
                            objectSet = new ContentCityOfBhopal(jsonRow.getInt("cl_id"), jsonRow.getString("cl_title"), jsonRow.getString("cl_text"), jsonRow.getString("cl_url"));
                            data.add(objectSet);

                        }
                        mAdapter = new CityOfBhopalAdapter(data, CampusLifeActivity.this);
                        rv.setAdapter(mAdapter);

                    } else {
                        Log.d("KEY_ELSE",response);
                        Toast.makeText(CampusLifeActivity.this, "Unable to Connect .", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    Log.d("Key_CATCH",e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("KEY_ERROR",error.toString());
                Toast.makeText(CampusLifeActivity.this, "Error in Connection." + error, Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(CampusLifeActivity.this);
        requestQueue.add(stringRequest);

    }


}
