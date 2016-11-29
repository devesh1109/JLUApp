package module.project.androidbraintech.jluapp.activity;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.pkmmte.view.CircularImageView;

import module.project.androidbraintech.jluapp.R;
import module.project.androidbraintech.jluapp.Utilities.Utils;


public class LoginActivity extends AppCompatActivity {


    CircularImageView guest,registered;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        RelativeLayout rl=(RelativeLayout)findViewById(R.id.rl_login1);
        rl.getBackground().setAlpha(140);

        guest=(CircularImageView)findViewById(R.id.l_guests);
        registered=(CircularImageView)findViewById(R.id.l_registered);

        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(LoginActivity.this,LoginFormActivity.class);
                intent.putExtra("type",1);
                startActivity(intent);

            }
        });


        registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,LoginFormActivity.class);
                intent.putExtra("type",2);
                startActivity(intent);

            }
        });
    }





}
