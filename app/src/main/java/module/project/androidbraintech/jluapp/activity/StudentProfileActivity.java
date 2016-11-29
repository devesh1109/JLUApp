package module.project.androidbraintech.jluapp.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.pkmmte.view.CircularImageView;

import module.project.androidbraintech.jluapp.R;
import module.project.androidbraintech.jluapp.Utilities.MySharedPreferences;
import module.project.androidbraintech.jluapp.Utilities.UrlAddressHolder;
import module.project.androidbraintech.jluapp.Utilities.Utils;
import module.project.androidbraintech.jluapp.containers.ContentRegisteredStudent;

public class StudentProfileActivity extends AppCompatActivity {

    CircularImageView simage;
    TextView id,name,school ,course,year,sem,house,contactmo,address;
    String url;

   ContentRegisteredStudent info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);


        simage=(CircularImageView) findViewById(R.id.s_image);
        simage.bringToFront();

        id=(TextView)findViewById(R.id.s_id);
        name=(TextView)findViewById(R.id.s_name);
        school=(TextView)findViewById(R.id.sp_school);
        course=(TextView)findViewById(R.id.sp_course);
        year=(TextView)findViewById(R.id.sp_year);
        sem=(TextView)findViewById(R.id.sp_sem);
        house=(TextView)findViewById(R.id.sp_house);
        contactmo=(TextView)findViewById(R.id.sp_contact_no);
        address=(TextView)findViewById(R.id.sp_address);

        RelativeLayout rl=(RelativeLayout)findViewById(R.id.RL);
       rl.getBackground().setAlpha(Utils.ALPHA_Value);

        info =MySharedPreferences.GetStudentInfo(StudentProfileActivity.this);

        id.setText(info.getSp_roll_no());
        name.setText(info.getSp_name());
        school.setText(" "+info.getSp_school());
        course.setText(" "+info.getSp_course());
        year.setText(" "+info.getSp_year());
        sem.setText(" "+info.getSp_sem() );
        house.setText(info.getSp_house());
        contactmo.setText(info.getSp_contact_no());
        address.setText(info.getSp_address());
        url=info.getSp_url();


        // students section missing

        if(Utils.checkIfNetworkIsAvailable(StudentProfileActivity.this)){
            CallImage();
        }











    }

    private void CallImage() {


        url= UrlAddressHolder.BASE_ADDRESS+UrlAddressHolder.STUDENTS_PROFILE_DIR+url;
        ImageRequest req=new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {

                Log.d("image req","success");

                simage.setImageBitmap(response);

            }
        }, 0, 0, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("image req","error"+error.toString());
            }
        });


        RequestQueue q=Volley.newRequestQueue(StudentProfileActivity.this);
        q.add(req);


    }


}
