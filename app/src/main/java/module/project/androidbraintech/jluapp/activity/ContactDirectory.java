package module.project.androidbraintech.jluapp.activity;

import android.app.DownloadManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
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

import java.util.ArrayList;

import module.project.androidbraintech.jluapp.R;
import module.project.androidbraintech.jluapp.Utilities.SimpleDividerItemDecoration;
import module.project.androidbraintech.jluapp.Utilities.SimpleDividerItemGridDecoration;
import module.project.androidbraintech.jluapp.adapters.ContactDirectoryAdapter;
import module.project.androidbraintech.jluapp.containers.ContentContactList;


public class ContactDirectory extends AppCompatActivity {
    SearchView sv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_dir);



        sv= (SearchView) findViewById(R.id.mSearch);
        RecyclerView rv= (RecyclerView) findViewById(R.id.myRecycler);
        //SET ITS PROPETRIES
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.addItemDecoration(new SimpleDividerItemDecoration(30));
        //ADAPTER
        final ContactDirectoryAdapter adapter=new ContactDirectoryAdapter(this,getPlayers());
        rv.setAdapter(adapter);
        //SEARCH
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String query) {
                //FILTER AS YOU TYPE
                adapter.getFilter().filter(query);
                return false;
            }
        });
    }
    //ADD PLAYERS TO ARRAYLIST
    private ArrayList<ContentContactList> getPlayers()
    {
        ArrayList<ContentContactList> contact=new ArrayList<>();
        ContentContactList p=new ContentContactList();
        p.setName("Pragyanshu");
        p.setPos("Student");
        p.setNum("982746582");
        p.setImg(R.drawable.registered);
        contact.add(p);
        p=new ContentContactList();
        p.setName("Tushar");
        p.setPos("Registrar");
        p.setNum("982746582");
        p.setImg(R.drawable.registered);
        contact.add(p);
        p=new ContentContactList();
        p.setName("Michael Carrick");
        p.setPos("Midfielder");
        p.setNum("982746582");
        p.setImg(R.drawable.registered);
        contact.add(p);
        p=new ContentContactList();
        p.setName("Juan Mata");
        p.setPos("Playmaker");
        p.setNum("982746582");
        p.setImg(R.drawable.registered);
        contact.add(p);
        p=new ContentContactList();
        p.setName("Diego Costa");
        p.setPos("Striker");
        p.setNum("982746582");
        p.setImg(R.drawable.registered);
        contact.add(p);
        p=new ContentContactList();
        p.setName("Oscar");
        p.setPos("Playmaker");
        p.setNum("982746582");
        p.setImg(R.drawable.registered);
        contact.add(p);
        return contact;
    }
}
