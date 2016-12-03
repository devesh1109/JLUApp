package module.project.androidbraintech.jluapp.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
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
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import module.project.androidbraintech.jluapp.R;
import module.project.androidbraintech.jluapp.Utilities.MySharedPreferences;
import module.project.androidbraintech.jluapp.Utilities.UrlAddressHolder;
import module.project.androidbraintech.jluapp.containers.ContentFaculty;

public class TakeAppointmentActivity extends AppCompatActivity {

    ListView facultyListView;
    String url;
    ArrayList<ContentFaculty> fList;
    ContentFaculty finfo;
    ApointmentAdapter adapter;

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

    Dialog cDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_appointment);

        facultyListView=(ListView)findViewById(R.id.fListView);

        Calendar cal = Calendar.getInstance();
        myYear = cal.get(Calendar.YEAR);
        myMonth = cal.get(Calendar.MONTH);
        myDay = cal.get(Calendar.DAY_OF_MONTH);

        getFacultyList();






    }

    private void getFacultyList() {


        url= UrlAddressHolder.BASE_ADDRESS+UrlAddressHolder.FACULTYLIST;

        StringRequest request=new StringRequest(Request.Method.GET,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    Log.d("response",response);

                    JSONObject object=new JSONObject(response);
                    fList=new ArrayList<ContentFaculty>();
                    int r=object.getInt("success");
                    if(r==1) {
                        JSONArray list = object.getJSONArray("list");

                        for (int i = 0; i < list.length(); i++) {
                            JSONObject o = list.getJSONObject(i);
                            finfo = new ContentFaculty(o.getString("f_fid"),o.getString("f_name"),"",o.getString("f_department"),"","","","","","","");
                            fList.add(finfo);
                        }

                        adapter=new ApointmentAdapter(fList);
                        facultyListView.setAdapter(adapter);




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
        });

        Volley.newRequestQueue(TakeAppointmentActivity.this).add(request);



    }


    public class ApointmentAdapter extends BaseAdapter {
        public ArrayList<ContentFaculty> list;
        Context context;
        LayoutInflater mInflator;

        public ApointmentAdapter( ArrayList<ContentFaculty> list) {

            this.list = list;
            mInflator=LayoutInflater.from(TakeAppointmentActivity.this);
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

                convertView=mInflator.inflate(R.layout.content_faculty_appointments,null);
                holder=new ViewHolder();
                holder.name=(TextView)convertView.findViewById(R.id.fname);
                holder.dep=(TextView)convertView.findViewById(R.id.fdes);
                convertView.setTag(holder);
            }else{
                holder=(ViewHolder)convertView.getTag();

            }

            final  ContentFaculty map=list.get(position);
            holder.name.setText(map.getName());
            holder.dep.setText(map.getDepartment());

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    fac_name=map.getName();
                    fac_id=map.getFid();
                    TakeAppointmentDialog();


                }
            });


            return convertView;
        }


        public  class ViewHolder{
            TextView name,dep;

        }
    }

     void TakeAppointmentDialog() {


         cDialog = new Dialog(TakeAppointmentActivity.this);
         cDialog.setTitle("Fill up the form");
         cDialog.setContentView(R.layout.content_take_appointment);
         cDialog.show();

         sname=(TextView)cDialog.findViewById(R.id.sname);
         fname=(TextView)cDialog.findViewById(R.id.fname);
         subject=(EditText)cDialog.findViewById(R.id.subject);
         date=(Button)cDialog.findViewById(R.id.date);
         submit=(Button)cDialog.findViewById(R.id.submit);


         sname.setText(MySharedPreferences.GetStudentInfo(TakeAppointmentActivity.this).getSp_name());
         fname.setText(fac_name);
         date.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 ChooseDate();

             }
         });

         submit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 SubmitAppointment();

             }
         });



     }

     void SubmitAppointment() {

         submiturl=UrlAddressHolder.BASE_ADDRESS+UrlAddressHolder.SUBMIT_APPOINTMENT;

         StringRequest submitreq = new StringRequest(Request.Method.POST, submiturl,
                 new Response.Listener<String>() {
                     @Override
                     public void onResponse(String response) {

                         cDialog.hide();
                         try {

                             Log.d("KEY_RESPONSE",response);
                             JSONObject jsonObject = new JSONObject(response);
                             JSONArray jsonData;
                             int flag = jsonObject.getInt("success");
                             if (flag == 1) {


                                 Toast.makeText(TakeAppointmentActivity.this,"Sent. We will contact you shortly",Toast.LENGTH_LONG).show();


                             } else {
                                 Log.d("KEY_ELSE",response);
                                 Toast.makeText(TakeAppointmentActivity.this, "Not sent. Check your network Connection", Toast.LENGTH_LONG).show();
                             }
                         } catch (JSONException e) {
                             Log.d("Key_CATCH",e.toString());
                         }
                     }
                 },new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {
                 Log.d("ERROR",error.toString());
                 cDialog.hide();
             }
         }){
             @Override
             protected Map<String,String> getParams(){
                 Map<String,String> params = new HashMap<String, String>();
                 params.put("fname",fname.getText().toString());
                 params.put("sname",sname.getText().toString());
                 params.put("sid",MySharedPreferences.GetStudentInfo(TakeAppointmentActivity.this).getSp_id()+"");
                 params.put("fid",fac_id);
                 params.put("subject",subject.getText().toString());
                 params.put("date",formattedDate);
                 return params;
             }

         };

         RequestQueue requestQueue = Volley.newRequestQueue(this);
         requestQueue.add(submitreq);




    }


    void ChooseDate() {

        // 1 for work exp     2 for certificate    3 for education
        final Calendar mCalendar = Calendar.getInstance();

        DatePickerDialog dpd = new DatePickerDialog(TakeAppointmentActivity.this,
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


                        date.setText(formattedDate);


                    }
                }, myYear, myMonth, myDay);
        dpd.show();

    }
}
