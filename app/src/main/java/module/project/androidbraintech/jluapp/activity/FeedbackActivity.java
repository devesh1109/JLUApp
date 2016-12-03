package module.project.androidbraintech.jluapp.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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

public class FeedbackActivity extends AppCompatActivity {

    TextInputLayout fb_name,fb_title,fb_message;
    EditText fb_name_txt,fb_title_txt,fb_message_txt;
    Button buttonSend;
    private static final String POST_URL = UrlAddressHolder.BASE_ADDRESS+UrlAddressHolder.FEEDBACK;

    public static final String KEY_NAME = "fb_name";
    public static final String KEY_TITLE = "fb_title";
    public static final String KEY_MESSAGE = "fb_message";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_feedback_jlu);

        fb_name = (TextInputLayout) findViewById(R.id.fb_name);
        fb_name_txt = (EditText) findViewById(R.id.fb_name_txt);
        fb_title = (TextInputLayout) findViewById(R.id.fb_title);
        fb_title_txt = (EditText) findViewById(R.id.fb_title_txt);
        fb_message = (TextInputLayout) findViewById(R.id.fb_message);
        fb_message_txt = (EditText) findViewById(R.id.fb_message_txt);


        fb_name.setHint("Name");
        fb_title.setHint("Subject");
        fb_message.setHint("Message");
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
        final String  final_name = fb_name_txt.getText().toString().trim();
        final String  final_title = fb_title_txt.getText().toString().trim();
        final String  final_message = fb_message_txt.getText().toString().trim();

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


                                Toast.makeText(FeedbackActivity.this,"Sent. Thankyou for your time.",Toast.LENGTH_LONG).show();
                                fb_name_txt.setText(null);
                                fb_title_txt.setText(null);
                                fb_message_txt.setText(null);

                            } else {
                                Log.d("KEY_ELSE",response);
                                Toast.makeText(FeedbackActivity.this, "Not sent. Check your network Connection", Toast.LENGTH_LONG).show();
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
                params.put(KEY_TITLE,final_title);
                params.put(KEY_MESSAGE,final_message);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }




}
