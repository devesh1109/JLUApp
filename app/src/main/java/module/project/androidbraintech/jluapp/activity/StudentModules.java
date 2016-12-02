package module.project.androidbraintech.jluapp.activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ListView;
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


public class StudentModules extends AppCompatActivity {


    RecyclerView mListview;
    String url;

    ArrayList<String> subjects=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_modules);

        mListview=(RecyclerView)findViewById(R.id.m_listView);


        getModules();

        Log.d("School",MySharedPreferences.GetStudentInfo(StudentModules.this).getSp_school());
        Log.d("Course",MySharedPreferences.GetStudentInfo(StudentModules.this).getSp_course());
        Log.d("Sem",String.valueOf(MySharedPreferences.GetStudentInfo(StudentModules.this).getSp_sem()));


    }


    void getModules(){


        url= UrlAddressHolder.BASE_ADDRESS+UrlAddressHolder.STUDENT_MODULES;

        StringRequest request=new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    Log.d("response",response);
                    subjects=new ArrayList<>();

                    JSONObject object=new JSONObject(response);
                    int r=object.getInt("success");
                    if(r==1) {
                        JSONArray list = object.getJSONArray("list");

                        for (int i = 0; i < list.length(); i++) {
                            JSONObject o = list.getJSONObject(i);
                            subjects.add(o.getString("m_subject"));
                            Log.e("SUBJECT "+i,subjects.get(i));

                        }



                        ModuleAdapter adapter=new ModuleAdapter(StudentModules.this,subjects);


                        final LinearLayoutManager linear = new LinearLayoutManager(StudentModules.this);
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
                map.put("m_sid", MySharedPreferences.GetStudentInfo(StudentModules.this).getSp_school());
                map.put("m_cid", MySharedPreferences.GetStudentInfo(StudentModules.this).getSp_course());
                map.put("m_sem", String.valueOf(MySharedPreferences.GetStudentInfo(StudentModules.this).getSp_sem()));

                return map;
            }
        };

        Volley.newRequestQueue(StudentModules.this).add(request);



    }



}
