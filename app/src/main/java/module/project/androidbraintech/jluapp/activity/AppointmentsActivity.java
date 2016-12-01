package module.project.androidbraintech.jluapp.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import module.project.androidbraintech.jluapp.R;
import module.project.androidbraintech.jluapp.Utilities.MyApplication;
import module.project.androidbraintech.jluapp.Utilities.MySharedPreferences;
import module.project.androidbraintech.jluapp.Utilities.UrlAddressHolder;
import module.project.androidbraintech.jluapp.Utilities.Utils;
import module.project.androidbraintech.jluapp.containers.ContentAppointments;
import module.project.androidbraintech.jluapp.containers.ContentFaculty;
import module.project.androidbraintech.jluapp.containers.ContentRegisteredStudent;

public class AppointmentsActivity extends AppCompatActivity {


    ListView aListView;
    String url;
    ArrayList<ContentAppointments> aList;
    ContentAppointments ainfo;
    FApointmentAdapter adapter;

    TextView fname,sname;
    EditText subject;
    Button date,submit;
    String fac_name;
    String formattedDate;
    int myYear;
    int myMonth;
    int myDay;
    String submiturl;
    String fac_id;
     String setAppointmentURL;
     String aid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);


       aListView=(ListView)findViewById(R.id.aListView);

        Calendar cal = Calendar.getInstance();
        myYear = cal.get(Calendar.YEAR);
        myMonth = cal.get(Calendar.MONTH);
        myDay = cal.get(Calendar.DAY_OF_MONTH);

        getAppointmentList();







    }

     void getAppointmentList() {

         url= UrlAddressHolder.BASE_ADDRESS+UrlAddressHolder.APPOINTMENTS;

         StringRequest request=new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
             @Override
             public void onResponse(String response) {

                 try {

                     Log.d("response",response);

                     JSONObject object=new JSONObject(response);
                     aList=new ArrayList<ContentAppointments>();
                     int r=object.getInt("success");
                     if(r==1) {
                         JSONArray list = object.getJSONArray("list");

                         for (int i = 0; i < list.length(); i++) {
                             JSONObject o = list.getJSONObject(i);
                             ainfo = new ContentAppointments(o.getString("a_aid"),o.getString("a_sid"),o.getString("a_fid"),o.getString("a_sname"),o.getString("a_fname"),o.getString("a_subject"),o.getString("a_date"),o.getString("fdate"));
                             aList.add(ainfo);
                         }

                         adapter=new FApointmentAdapter(aList);
                         aListView.setAdapter(adapter);




                     }else{

                         Log.d("else error",response);
                     }


                 }catch (Exception e){


                 }


             }
         }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {

             }
         }){

             @Override
             protected Map<String, String> getParams() {
                 HashMap<String, String> map = new HashMap<String, String>();
                 map.put("id", MySharedPreferences.getFacultyInfo(AppointmentsActivity.this).getFid());

                 return map;
             }
         };

         Volley.newRequestQueue(AppointmentsActivity.this).add(request);





     }


    public  class FApointmentAdapter extends BaseAdapter {
        public ArrayList<ContentAppointments> list;
        Context context;
        LayoutInflater mInflator;

        public FApointmentAdapter( ArrayList<ContentAppointments> list) {

            this.list = list;
            mInflator=LayoutInflater.from(AppointmentsActivity.this);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;

            if(convertView==null){

                convertView=mInflator.inflate(R.layout.content_appointments,null);
                holder=new ViewHolder();
                holder.sname=(TextView)convertView.findViewById(R.id.sname);
                holder.subject=(TextView)convertView.findViewById(R.id.subject);
                holder.sdate=(TextView)convertView.findViewById(R.id.sdate);
                holder.gbutton=(Button) convertView.findViewById(R.id.gbutton);
                holder.fdate=(TextView)convertView.findViewById(R.id.fdate);
                convertView.setTag(holder);
            }else{
                holder=(ViewHolder)convertView.getTag();

            }

            final  ContentAppointments map=list.get(position);
            holder.sname.setText(map.getA_sname());
            holder.subject.setText(map.getA_subject());
            holder.sdate.setText(map.getA_date());
            holder.fdate.setText(map.getFdate());

            holder.gbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    aid=map.getA_id();
                    ChooseDate();


                }
            });



            return convertView;
        }


        public  class ViewHolder{
            TextView sname,subject,sdate,fdate;
            Button gbutton;

        }
    }


    void ChooseDate() {

        // 1 for work exp     2 for certificate    3 for education
        final Calendar mCalendar = Calendar.getInstance();

        DatePickerDialog dpd = new DatePickerDialog(AppointmentsActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        mCalendar.set(Calendar.YEAR, year);
                        mCalendar.set(Calendar.MONTH, monthOfYear);
                        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        formattedDate = df.format(mCalendar.getTime());

                        Log.e("DATE>>","  "+String.valueOf(mCalendar.getTime()));


                        SetAppointment();





                    }
                }, myYear, myMonth, myDay);
        dpd.show();

    }

    void SetAppointment(){

        setAppointmentURL=UrlAddressHolder.BASE_ADDRESS+UrlAddressHolder.SET_APPOINTMENT;

        StringRequest guestRequest = new StringRequest(Request.Method.POST, setAppointmentURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    Log.d("REGISTEREDLOGIN respose", response);
                    JSONObject object = new JSONObject(response);
                    int r=object.getInt("success");

                    if(r==1) {

                        Toast.makeText(AppointmentsActivity.this,"Success !",Toast.LENGTH_LONG).show();


                    }else {

                        Toast.makeText(AppointmentsActivity.this,"Failed !",Toast.LENGTH_LONG).show();


                    }




                }catch(Exception e){

                    Toast.makeText(AppointmentsActivity.this,"Error! please redo the action",Toast.LENGTH_LONG).show();
                    Log.d("exception",e.toString());


                }




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {



                Log.d("REGISTEREDLOGIN error", error.toString());
                Toast.makeText(AppointmentsActivity.this,"Error! please redo the action",Toast.LENGTH_LONG).show();

            }

        }) {

            @Override
            protected Map<String, String> getParams() {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("fdate", formattedDate);
                map.put("a_id",aid);

                return map;
            }
        };


        if(Utils.checkIfNetworkIsAvailable(AppointmentsActivity.this)) {
            MyApplication.getInstance().addToRequestQueue(guestRequest);
        }

    }







}


