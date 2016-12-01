package module.project.androidbraintech.jluapp.activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import module.project.androidbraintech.jluapp.Utilities.SchoolAndCourseResolver;
import module.project.androidbraintech.jluapp.Utilities.UrlAddressHolder;
import module.project.androidbraintech.jluapp.Utilities.Utils;
import module.project.androidbraintech.jluapp.containers.ContentFaculty;

public class FacultyProfileActivity extends AppCompatActivity {

    TextView fid, name, designation, department, school, contact, email, qualification, description;
    CircularImageView fimage;
    String url;

    ContentFaculty info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_profile);

        fimage=(CircularImageView) findViewById(R.id.f_image);
        fimage.bringToFront();


        fid=(TextView)findViewById(R.id.f_id);
        name=(TextView)findViewById(R.id.f_name);
        designation=(TextView)findViewById(R.id.f_designation);
        department=(TextView)findViewById(R.id.f_department);
        school=(TextView)findViewById(R.id.f_school);
        contact=(TextView)findViewById(R.id.f_contact);
        email=(TextView)findViewById(R.id.f_email);
        qualification=(TextView)findViewById(R.id.f_qualifications);
        description=(TextView)findViewById(R.id.f_description);

        RelativeLayout rl=(RelativeLayout)findViewById(R.id.RLF);
        rl.getBackground().setAlpha(180);

        info = MySharedPreferences.getFacultyInfo(FacultyProfileActivity.this);


        fid.setText(info.getFid());
        name.setText(info.getName());
        designation.setText(info.getDesignation());
        department.setText(info.getDepartment());
        school.setText(SchoolAndCourseResolver.GetSchoolName(info.getSchool()));
        contact.setText(info.getContact());
        email.setText(info.getEmail());
        qualification.setText(info.getQualification());
        description.setText(info.getDescription());
        url=info.getUrl();


        if(Utils.checkIfNetworkIsAvailable(FacultyProfileActivity.this)){
            CallImage();
        }






    }

    private void CallImage() {


        ImageRequest req=new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {

                Log.d("image req","success");

                fimage.setImageBitmap(response);

            }
        }, 0, 0, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("image req","error"+error.toString());
            }
        });


        RequestQueue q= Volley.newRequestQueue(FacultyProfileActivity.this);
        q.add(req);


    }

}
