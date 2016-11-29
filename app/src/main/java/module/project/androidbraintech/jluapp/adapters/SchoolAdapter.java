package module.project.androidbraintech.jluapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import module.project.androidbraintech.jluapp.R;
import module.project.androidbraintech.jluapp.containers.SchoolItems;

/**
 * Created by Tushar-PC on 20-08-2016.
 */
public class SchoolAdapter extends BaseAdapter {

    public ArrayList<SchoolItems> list;
    Context context;
    LayoutInflater mInflator;

    public SchoolAdapter(Context context, ArrayList<SchoolItems> list) {
        this.context = context;
        this.list = list;
         mInflator=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView==null){

            convertView=mInflator.inflate(R.layout.content_school_list,null);
            holder=new ViewHolder();
            holder.textView=(TextView)convertView.findViewById(R.id.textView4);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();

        }

       SchoolItems map=list.get(position);
        holder.textView.setText(map.getS_name());



        return convertView;
    }


    static class ViewHolder{
        TextView textView;

    }
}
