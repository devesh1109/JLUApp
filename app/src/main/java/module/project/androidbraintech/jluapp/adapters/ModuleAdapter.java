package module.project.androidbraintech.jluapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import module.project.androidbraintech.jluapp.R;

/**
 * Created by Tushar-PC on 02-12-2016.
 */
public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.myViewHolder> {

    Context context;
    ArrayList<String> subjects=new ArrayList<>();
    LayoutInflater inflater;


    public ModuleAdapter(Context context,   ArrayList<String> subjects){

        this.context=context;
        this.subjects=subjects;
        inflater=LayoutInflater.from(context);


    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View itemView = LayoutInflater.from(parent.getContext()
        ).inflate(R.layout.content_text,parent,false);

        return new myViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {

        holder.textView.setText(subjects.get(position));

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public myViewHolder(View v){
            super(v);
            textView =(TextView)v.findViewById(R.id.text);
        }
    }



}
