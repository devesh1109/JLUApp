package module.project.androidbraintech.jluapp.activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import module.project.androidbraintech.jluapp.R;
import module.project.androidbraintech.jluapp.Utilities.MySharedPreferences;
import module.project.androidbraintech.jluapp.Utilities.UrlAddressHolder;
import module.project.androidbraintech.jluapp.containers.ContentFaculty;
import module.project.androidbraintech.jluapp.containers.ContentRegisteredStudent;

public class TimeTableActivity extends AppCompatActivity {

    ImageView image;
    ContentRegisteredStudent Sinfo;
    ContentFaculty Finfo;
    String file,url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);

        image=(ImageView)findViewById(R.id.timetable);

        if(MySharedPreferences.getAdapterType(TimeTableActivity.this)==3){
            Sinfo= MySharedPreferences.GetStudentInfo(TimeTableActivity.this);

            file=Sinfo.getSp_school()+Sinfo.getSp_course()+Sinfo.getSp_sem()+Sinfo.getSp_section()+".png";

            url= UrlAddressHolder.BASE_ADDRESS+UrlAddressHolder.TIMETABLE+file;

        }else if(MySharedPreferences.getAdapterType(TimeTableActivity.this)==4) {

            Finfo=MySharedPreferences.getFacultyInfo(TimeTableActivity.this);

            url=UrlAddressHolder.BASE_ADDRESS+UrlAddressHolder.TIMETABLE+Finfo.getFid();


        }



        CallTimeTable();




    }

    private void CallTimeTable() {



        ImageRequest req=new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {

                Log.d("image req","success");

                image.setImageBitmap(response);

            }
        }, 0, 0, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("image req","error"+error.toString());
            }
        });


        RequestQueue q= Volley.newRequestQueue(TimeTableActivity.this);
        q.add(req);

    }
}
