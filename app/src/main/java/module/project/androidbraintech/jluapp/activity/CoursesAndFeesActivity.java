package module.project.androidbraintech.jluapp.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import module.project.androidbraintech.jluapp.R;
import module.project.androidbraintech.jluapp.Utilities.MyApplication;
import module.project.androidbraintech.jluapp.Utilities.UrlAddressHolder;
import module.project.androidbraintech.jluapp.adapters.CourseAdapter;
import module.project.androidbraintech.jluapp.adapters.SchoolAdapter;
import module.project.androidbraintech.jluapp.containers.CourseItems;
import module.project.androidbraintech.jluapp.containers.SchoolItems;

public class CoursesAndFeesActivity extends AppCompatActivity {

    ListView s_listView,c_listView;

    TextView cnameText,cfeesText,cdurationText;

    ArrayList<SchoolItems> schoolList;
    SchoolItems schoolItems;

    ArrayList<CourseItems> courselist;
    CourseItems courseItems;

    SchoolAdapter schoolAdapter;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_and_fees);




        s_listView=(ListView)findViewById(R.id.s_listView);


         fetchSchoolList();


        s_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                LoadCourseList(schoolAdapter.list.get(position).getS_id());

            }});






    }

    private void LoadCourseList( int s_id) {

        final int id=s_id;

        String url= UrlAddressHolder.BASE_ADDRESS+UrlAddressHolder.COURSE_LIST;



        StringRequest courseListRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                dialog.dismiss();

                try{
                    Log.d("courseList Request",response);
                    JSONObject object=new JSONObject(response);
                    courselist=new ArrayList<CourseItems>();
                    int r=object.getInt("success");
                    if(r==1) {
                        JSONArray list = object.getJSONArray("list");

                        for (int i = 0; i < list.length(); i++) {
                            JSONObject o = list.getJSONObject(i);
                            courseItems = new CourseItems(o.getInt("c_id"), o.getInt("s_id"), o.getString("c_name"), o.getInt("c_duration"), o.getDouble("c_fees"));
                            courselist.add(courseItems);
                        }


                        Dialog cDialog = new Dialog(CoursesAndFeesActivity.this);
                        cDialog.setTitle("Courses");
                        cDialog.setContentView(R.layout.content_course_list);
                        c_listView = (ListView) cDialog.findViewById(R.id.c_listView);
                        final CourseAdapter courseAdapter = new CourseAdapter(CoursesAndFeesActivity.this, courselist);
                        c_listView.setAdapter(courseAdapter);
                        cDialog.show();

                        c_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                Toast.makeText(CoursesAndFeesActivity.this, courseAdapter.list.get(position).getC_name(), Toast.LENGTH_SHORT).show();

                                final Dialog cDetailDialog = new Dialog(CoursesAndFeesActivity.this);
                                cDetailDialog.setTitle("Course Details");
                                cDetailDialog.setContentView(R.layout.content_course_details);
                                cnameText = (TextView) cDetailDialog.findViewById(R.id.cnametext);
                                cfeesText = (TextView) cDetailDialog.findViewById(R.id.cfeestext);
                                cdurationText = (TextView) cDetailDialog.findViewById(R.id.cdurationtext);


                                cnameText.setText(courseAdapter.list.get(position).getC_name());
                                cfeesText.setText(courseAdapter.list.get(position).getC_fees() + "Rupees ");
                                cdurationText.setText(courseAdapter.list.get(position).getC_years() + " Years");

                                cDetailDialog.show();
                                Button okButton = (Button) cDetailDialog.findViewById(R.id.cbuttonOK);
                                okButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        cDetailDialog.dismiss();

                                    }
                                });


                            }
                        });




                    }else{
                        Log.d("courseList RequestELSE",response);

                        Toast.makeText(CoursesAndFeesActivity.this,"Operation Failed !",Toast.LENGTH_LONG).show();
                    }

                }catch(Exception e){
                    Log.d("couseList RequestEXP",e.toString());

                    Toast.makeText(CoursesAndFeesActivity.this,"Operation Failed !",Toast.LENGTH_LONG).show();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                dialog.dismiss();
                Log.d("courseList RequestERROR",error.toString());
                Toast.makeText(CoursesAndFeesActivity.this,"Operation Failed !",Toast.LENGTH_LONG).show();

            }
        }){

            @Override
            protected Map<String, String> getParams() {
                HashMap<String,String> map=new HashMap<String, String>();
                map.put("id",String.valueOf(id));
                return map;
            }
        };


        MyApplication.getInstance().addToRequestQueue(courseListRequest);

        dialog=new ProgressDialog(CoursesAndFeesActivity.this);
        dialog.setMessage("Loading...");
        dialog.setIndeterminate(true);
        dialog.show();






    }

    private void fetchSchoolList() {

        String url= UrlAddressHolder.BASE_ADDRESS+UrlAddressHolder.SCHOOL_LIST;



        StringRequest schoolListRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                dialog.dismiss();

                try{
                    Log.d("schoolList Request",response);
                    JSONObject object=new JSONObject(response);
                    schoolList=new ArrayList<SchoolItems>();
                    int r=object.getInt("success");
                    if(r==1){
                        JSONArray list=object.getJSONArray("list");

                        for(int i=0;i<list.length();i++){
                            JSONObject o=list.getJSONObject(i);
                            schoolItems=new SchoolItems(o.getInt("s_id"),o.getString("s_name"));
                            schoolList.add(schoolItems);
                        }

                        schoolAdapter=new SchoolAdapter(CoursesAndFeesActivity.this,schoolList);
                        s_listView.setAdapter(schoolAdapter);


                    }else{
                        Log.d("schoolList RequestELSE",response);

                        Toast.makeText(CoursesAndFeesActivity.this,"Operation Failed !",Toast.LENGTH_LONG).show();
                    }

                }catch(Exception e){
                    Log.d("schoolList RequestEXP",e.toString());

                    Toast.makeText(CoursesAndFeesActivity.this,"Operation Failed !",Toast.LENGTH_LONG).show();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                dialog.dismiss();
                Log.d("schoolList RequestERROR",error.toString());
                Toast.makeText(CoursesAndFeesActivity.this,"Operation Failed !",Toast.LENGTH_LONG).show();

            }
        });


        MyApplication.getInstance().addToRequestQueue(schoolListRequest);

         dialog=new ProgressDialog(CoursesAndFeesActivity.this);
        dialog.setMessage("Loading...");
        dialog.setIndeterminate(true);
        dialog.show();




    }
}
