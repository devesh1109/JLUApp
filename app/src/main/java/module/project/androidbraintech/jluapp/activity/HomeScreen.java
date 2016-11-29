package module.project.androidbraintech.jluapp.activity;


import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import module.project.androidbraintech.jluapp.R;
import module.project.androidbraintech.jluapp.Utilities.MySharedPreferences;
import module.project.androidbraintech.jluapp.Utilities.SimpleDividerItemGridDecoration;
import module.project.androidbraintech.jluapp.homescreen_adapters.CurrentStudentsAdapter;
import module.project.androidbraintech.jluapp.homescreen_adapters.FacultyAdapter;
import module.project.androidbraintech.jluapp.homescreen_adapters.GuestAdapter;
import module.project.androidbraintech.jluapp.homescreen_adapters.ProspectiveStudentsAdapter;

public class HomeScreen extends AppCompatActivity {

    int adapter_type;
    RecyclerView hs_recyclerView;
    GuestAdapter guestAdapter;
    ProspectiveStudentsAdapter pAdapter;
    CurrentStudentsAdapter cAdapter;
    FacultyAdapter fAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_home_screen);

        adapter_type= MySharedPreferences.getAdapterType(HomeScreen.this);

        hs_recyclerView=(RecyclerView)findViewById(R.id.hs_recyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(HomeScreen.this,2);
        hs_recyclerView.setLayoutManager(layoutManager);
        hs_recyclerView.addItemDecoration(new SimpleDividerItemGridDecoration(
                30
        ));
        hs_recyclerView.setItemAnimator(new DefaultItemAnimator());

        if (adapter_type==1){

            Log.d("ADAPTER  ","GUEST");
            guestAdapter = new GuestAdapter(HomeScreen.this);
            hs_recyclerView.setAdapter(guestAdapter);
        }else if(adapter_type==2){
            Log.d("ADAPTER  ","PROSP");
            pAdapter=new ProspectiveStudentsAdapter(HomeScreen.this);
            hs_recyclerView.setAdapter(pAdapter);

        }else if(adapter_type==3){

            Log.d("ADAPTER  ","STUDENT");
            cAdapter=new CurrentStudentsAdapter(HomeScreen.this);
            hs_recyclerView.setAdapter(cAdapter);

        }else if(adapter_type==4){

            Log.d("ADAPTER  ","FACULTY");
            fAdapter=new FacultyAdapter(HomeScreen.this);
            hs_recyclerView.setAdapter(fAdapter);

        }






    }


}
