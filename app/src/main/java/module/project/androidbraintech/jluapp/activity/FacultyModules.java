package module.project.androidbraintech.jluapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import module.project.androidbraintech.jluapp.R;
import module.project.androidbraintech.jluapp.Utilities.MySharedPreferences;
import module.project.androidbraintech.jluapp.Utilities.SimpleDividerItemDecoration;
import module.project.androidbraintech.jluapp.Utilities.UrlAddressHolder;
import module.project.androidbraintech.jluapp.adapters.ModuleAdapter;

public class FacultyModules extends AppCompatActivity {

    RecyclerView mListview;
    String url;

    ArrayList<String> subjects=new ArrayList<>();
    LayoutInflater inflater;
    Spinner semList;

    String[] sem={"1","2","3","4","5","6","7","8"};

    String semNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_modules);

        mListview=(RecyclerView)findViewById(R.id.f_listView);
        semList=(Spinner)findViewById(R.id.spinner);
        inflater=LayoutInflater.from(FacultyModules.this);


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sem);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semList.setAdapter(dataAdapter);

       semList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               semNo=sem[position];
               getModules();
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });





    }

    void getModules(){


        url= UrlAddressHolder.BASE_ADDRESS+UrlAddressHolder.STUDENT_MODULES;

        StringRequest request=new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    Log.d("response",response);

                    JSONObject object=new JSONObject(response);
                    int r=object.getInt("success");
                    if(r==1) {
                        JSONArray list = object.getJSONArray("list");

                        for (int i = 0; i < list.length(); i++) {
                            JSONObject o = list.getJSONObject(i);
                            subjects.add(o.getString("m_subject"));

                        }



                        ModuleAdapter adapter=new ModuleAdapter(FacultyModules.this,subjects);

                        final LinearLayoutManager linear = new LinearLayoutManager(FacultyModules.this);
                        linear.setOrientation(LinearLayoutManager.VERTICAL);
                        mListview.setLayoutManager(linear);
                        mListview.setNestedScrollingEnabled(false);
                        mListview.addItemDecoration(new SimpleDividerItemDecoration(20));

                        mListview.setAdapter(adapter);

                    }else{

                        Log.d("else error",response);
                    }


                }catch (Exception e){

                    Log.d("Exception",e.toString());
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error",error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map=new HashMap<String, String>();
                map.put("m_sid", MySharedPreferences.getFacultyInfo(FacultyModules.this).getSchool());
                map.put("m_cid", MySharedPreferences.GetStudentInfo(FacultyModules.this).getSp_course());
                map.put("m_sem",semNo);

                return map;
            }
        };

        Volley.newRequestQueue(FacultyModules.this).add(request);



    }


}
