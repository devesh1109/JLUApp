package module.project.androidbraintech.jluapp.activity;



import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import module.project.androidbraintech.jluapp.R;
import module.project.androidbraintech.jluapp.Utilities.MyApplication;
import module.project.androidbraintech.jluapp.Utilities.MySharedPreferences;
import module.project.androidbraintech.jluapp.Utilities.UrlAddressHolder;
import module.project.androidbraintech.jluapp.Utilities.Utils;
import module.project.androidbraintech.jluapp.containers.ContentFaculty;
import module.project.androidbraintech.jluapp.containers.ContentRegisteredStudent;

public class LoginFormActivity extends AppCompatActivity {


    EditText l_name,l_email,l_city;
    Button g_done;
    String name,email,city;
    int type,rtype;

    EditText pform,cform,fform;
    Button pButton,cButton,fButton;
    String value,url;

    TextView t_pros,t_student,t_faculty;
    RelativeLayout rl_pros,rl_stud,rl_faculty;


    ProgressDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent=getIntent();
        type=intent.getIntExtra("type",0);
        if(type==1) {
            setContentView(R.layout.content_guest_form);

            GuestView();




        }else if(type==2){
            setContentView(R.layout.content_registered_form);

           RegisteredView();


        }




    }

    private void RegisteredView() {


        t_pros=(TextView)findViewById(R.id.t_pros);
        t_student=(TextView)findViewById(R.id.t_student);
        t_faculty=(TextView)findViewById(R.id.t_faculty);

        pform=(EditText)findViewById(R.id.pformEditText);
        cform=(EditText)findViewById(R.id.cformEditText);
        fform=(EditText)findViewById(R.id.fformEditText);

        pButton=(Button)findViewById(R.id.pbutton);
        cButton=(Button)findViewById(R.id.cButton);
        fButton=(Button)findViewById(R.id.fButton);

        rl_pros=(RelativeLayout)findViewById(R.id.rl_pros) ;
        rl_stud=(RelativeLayout)findViewById(R.id.rl_current) ;
        rl_faculty=(RelativeLayout)findViewById(R.id.rl_faculty) ;

        rl_pros.getBackground().setAlpha(120);
        rl_stud.getBackground().setAlpha(120);
        rl_faculty.getBackground().setAlpha(120);


        t_pros.setBackground(getResources().getDrawable(R.drawable.darkblueborder));

        rl_pros.setVisibility(View.VISIBLE);
        rl_stud.setVisibility(View.GONE);
        rl_faculty.setVisibility(View.GONE);


        t_pros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                t_pros.setBackground(getResources().getDrawable(R.drawable.darkblueborder));
                t_student.setBackground(getResources().getDrawable(R.drawable.circulerimageview));
                t_faculty.setBackground(getResources().getDrawable(R.drawable.circulerimageview));

                rl_pros.setVisibility(View.VISIBLE);
                rl_stud.setVisibility(View.GONE);
                rl_faculty.setVisibility(View.GONE);


            }
        });

        t_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                t_pros.setBackground(getResources().getDrawable(R.drawable.circulerimageview));
                t_student.setBackground(getResources().getDrawable(R.drawable.darkblueborder));
                t_faculty.setBackground(getResources().getDrawable(R.drawable.circulerimageview));

                rl_pros.setVisibility(View.GONE);
                rl_stud.setVisibility(View.VISIBLE);
                rl_faculty.setVisibility(View.GONE);



            }
        });
        t_faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                t_pros.setBackground(getResources().getDrawable(R.drawable.circulerimageview));
                t_student.setBackground(getResources().getDrawable(R.drawable.circulerimageview));
                t_faculty.setBackground(getResources().getDrawable(R.drawable.darkblueborder));


                rl_pros.setVisibility(View.GONE);
                rl_stud.setVisibility(View.GONE);
                rl_faculty.setVisibility(View.VISIBLE);


            }
        });

        pButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                url=UrlAddressHolder.BASE_ADDRESS+UrlAddressHolder.PROSPECTIVE_FORM;
                value=pform.getText().toString();
                MySharedPreferences.SaveProspective(LoginFormActivity.this,value);
                MySharedPreferences.saveAdapterType(LoginFormActivity.this,2);

                NetworkCallForRegistered();

                rtype=1;


            }
        });


        cButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                url=UrlAddressHolder.BASE_ADDRESS+UrlAddressHolder.CURRENT_STUDENT_FORM;
                value=cform.getText().toString();

                MySharedPreferences.saveAdapterType(LoginFormActivity.this,3);

                NetworkCallForRegistered();

                rtype=2;

            }
        });

        fButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                url=UrlAddressHolder.BASE_ADDRESS+UrlAddressHolder.FACULTY_FORM;
                value=fform.getText().toString();


                MySharedPreferences.saveAdapterType(LoginFormActivity.this,4);

                NetworkCallForRegistered();

                rtype=3;

            }
        });



    }

    private void GuestView() {

        RelativeLayout rlg=(RelativeLayout)findViewById(R.id.rlg);
        rlg.getBackground().setAlpha(150);


        l_name=(EditText)findViewById(R.id.l_name);
        l_email=(EditText)findViewById(R.id.l_email);
        l_city=(EditText)findViewById(R.id.l_city);
        g_done=(Button)findViewById(R.id.btn_done);
        g_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=l_name.getText().toString();
                email=l_email.getText().toString();
                city=l_city.getText().toString();


                if(name.isEmpty() || email.isEmpty() || city.isEmpty()){

                    Log.d("loginform act","empty");

                    Utils.ShowAlertDialog(LoginFormActivity.this,"Error","Please fill the empty fields.");

                }else {

                    Log.d("DATA", name + email + city);
                    MySharedPreferences.SaveLoginGuest(LoginFormActivity.this, name, email, city);
                    MySharedPreferences.saveAdapterType(LoginFormActivity.this, 1);


                    dialog=new ProgressDialog(LoginFormActivity.this);
                    dialog.setMessage("Loading....");
                    dialog.setCancelable(true);
                    dialog.setIndeterminate(true);
                    dialog.show();

                    url = UrlAddressHolder.BASE_ADDRESS + UrlAddressHolder.FEED_GUEST_DATA;

                    StringRequest guestRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                           dialog.hide();
                            try {
                                Log.d("GUESTLOGIN respose", response);
                                JSONObject object = new JSONObject(response);
                                int r=object.getInt("success");

                                if(r==1){

                                    Intent intent = new Intent(LoginFormActivity.this, HomeScreen.class);
                                    startActivity(intent);
                                    finish();

                                }else{

                                    Toast.makeText(LoginFormActivity.this,"Error! please redo the action",Toast.LENGTH_LONG).show();


                                }

                            }catch(Exception e){

                                Toast.makeText(LoginFormActivity.this,"Error! please redo the action",Toast.LENGTH_LONG).show();


                            }




                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            dialog.hide();

                            Log.d("GUESTLOGIN error", error.toString());
                            Toast.makeText(LoginFormActivity.this,"Error! please redo the action",Toast.LENGTH_LONG).show();

                        }

                    }) {

                        @Override
                        protected Map<String, String> getParams() {
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("name", name);
                            map.put("email", email);
                            map.put("city", city);

                            return map;
                        }
                    };


                    if(Utils.checkIfNetworkIsAvailable(LoginFormActivity.this)) {
                        MyApplication.getInstance().addToRequestQueue(guestRequest);
                    }

                }



            }
        });




    }


    void NetworkCallForRegistered(){

        dialog=new ProgressDialog(LoginFormActivity.this);
        dialog.setMessage("Loading....");
        dialog.setCancelable(true);
        dialog.setIndeterminate(true);
        dialog.show();


        Log.d("value",value);

        StringRequest guestRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                dialog.hide();
                try {
                    Log.d("REGISTEREDLOGIN respose", response);
                    JSONObject object = new JSONObject(response);
                    int r=object.getInt("success");

                    if(r==1){

                        if(rtype==1){


                            Log.d("Prospective","success"+response);


                        }else if(rtype==2){

                            String school="",course="";
                            // save students info
                            JSONArray arr=object.getJSONArray("list");
                            JSONObject o=arr.getJSONObject(0);

                            ContentRegisteredStudent info=new ContentRegisteredStudent(o.getInt("sp_id"),o.getString("sp_school"),o.getString("sp_school"),o.getInt("sp_year"),o.getInt("sp_sem"),o.getString("sp_roll_no"),o.getString("sp_name"),o.getString("sp_url"),o.getString("sp_house"),o.getString("sp_contact_no"),o.getString("sp_address"),o.getString("sp_section"),o.getString("sp_attendance"));
                            MySharedPreferences.SaveCurrentStudent(LoginFormActivity.this,info);




                        }else if(rtype==3){

                            JSONArray arr=object.getJSONArray("list");
                            JSONObject o=arr.getJSONObject(0);

                            ContentFaculty info=new ContentFaculty(o.getString("f_fid"),o.getString("f_name"),o.getString("f_designation"),o.getString("f_department"),o.getString("f_school"),o.getString("f_contact"),o.getString("f_email"),o.getString("f_qual"),o.getString("f_desc"),o.getString("f_url"));

                            MySharedPreferences.SaveFaculty(LoginFormActivity.this,info);


                        }




                        Intent intent = new Intent(LoginFormActivity.this, HomeScreen.class);
                        startActivity(intent);
                        finish();

                    }else if(r==2){

                        Log.d("LoginFormActivity","MISSING FIELDS");

                    }else{
                        Toast.makeText(LoginFormActivity.this,"Error! please redo the action",Toast.LENGTH_LONG).show();
                    }

                }catch(Exception e){

                    Toast.makeText(LoginFormActivity.this,"Error! please redo the action",Toast.LENGTH_LONG).show();
                    Log.d("exception",e.toString());


                }




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                dialog.hide();

                Log.d("REGISTEREDLOGIN error", error.toString());
                Toast.makeText(LoginFormActivity.this,"Error! please redo the action",Toast.LENGTH_LONG).show();

            }

        }) {

            @Override
            protected Map<String, String> getParams() {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("text", value);

                return map;
            }
        };


        if(Utils.checkIfNetworkIsAvailable(LoginFormActivity.this)) {
            MyApplication.getInstance().addToRequestQueue(guestRequest);
        }

    }






}




