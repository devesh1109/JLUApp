package module.project.androidbraintech.jluapp.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

import module.project.androidbraintech.jluapp.R;
import module.project.androidbraintech.jluapp.Utilities.UrlAddressHolder;

public class EnquiryActivity extends AppCompatActivity {

    TextInputLayout enq_name,enq_subject,enq_city,enq_message,enq_mail;
    EditText enq_name_txt,enq_subject_txt,enq_city_txt,enq_message_txt,enq_mail_txt;
    Button buttonSend;
    private static final String POST_URL = UrlAddressHolder.BASE_ADDRESS+UrlAddressHolder.ENQUIRY;

    public static final String KEY_NAME = "enq_name";
    public static final String KEY_SUBJECT = "enq_subject";
    public static final String KEY_CITY = "enq_city";
    public static final String KEY_MESSAGE = "enq_message";
    public static final String KEY_EMAIL = "enq_email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_enquiry_jlu);

        enq_name = (TextInputLayout) findViewById(R.id.enq_name);
        enq_name_txt = (EditText) findViewById(R.id.enq_name_txt);
        enq_subject = (TextInputLayout) findViewById(R.id.enq_subject);
        enq_subject_txt = (EditText) findViewById(R.id.enq_subject_txt);
        enq_city = (TextInputLayout) findViewById(R.id.enq_city);
        enq_city_txt = (EditText) findViewById(R.id.enq_city_txt);
        enq_message = (TextInputLayout) findViewById(R.id.enq_message);
        enq_message_txt = (EditText) findViewById(R.id.enq_message_txt);
        enq_mail=(TextInputLayout)findViewById(R.id.enq_mail);
        enq_mail_txt=(EditText)findViewById(R.id.enq_email_txt);

        enq_name.setHint("Name");
        enq_subject.setHint("Subject");
        enq_city.setHint("City");
        enq_message.setHint("Message");
        enq_mail.setHint("Email Id");
        buttonSend = (Button)findViewById(R.id.buttonSend);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postInfo();
            }
        });
    }

    public void postInfo()
    {
        final String  final_name = enq_name_txt.getText().toString().trim();
        final String  final_subject = enq_subject_txt.getText().toString().trim();
        final String  final_city = enq_city_txt.getText().toString().trim();
        final String  final_message = enq_message_txt.getText().toString().trim();
        final String  final_mail=enq_mail_txt.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, POST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.d("KEY_RESPONSE",response);
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonData;
                            int flag = jsonObject.getInt("success");
                            if (flag == 1) {
                                enq_name_txt.setText(null);
                                enq_subject_txt.setText(null);
                                enq_city_txt.setText(null);
                                enq_message_txt.setText(null);
                                enq_mail_txt.setText(null);

                                Toast.makeText(EnquiryActivity.this,"Sent. We will contact you shortly",Toast.LENGTH_LONG).show();


                            } else {
                                Log.d("KEY_ELSE",response);
                                Toast.makeText(EnquiryActivity.this, "Not sent. Check your network Connection", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            Log.d("Key_CATCH",e.toString());
                        }
                    }
                },new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ERROR",error.toString());
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_NAME,final_name);
                params.put(KEY_SUBJECT,final_subject);
                params.put(KEY_CITY,final_city);
                params.put(KEY_MESSAGE,final_message);
                params.put(KEY_EMAIL,final_mail);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }




}
