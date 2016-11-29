package module.project.androidbraintech.jluapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import module.project.androidbraintech.jluapp.R;
import module.project.androidbraintech.jluapp.containers.CourseItems;
import module.project.androidbraintech.jluapp.containers.SchoolItems;

/**
 * Created by Tushar-PC on 20-08-2016.
 */
public class CourseAdapter extends BaseAdapter {
    public ArrayList<CourseItems> list;
    Context context;
    LayoutInflater mInflator;

    public CourseAdapter(Context context, ArrayList<CourseItems> list) {
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

            convertView=mInflator.inflate(R.layout.content_course,null);
            holder=new ViewHolder();
            holder.textView=(TextView)convertView.findViewById(R.id.textView5);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();

        }

        CourseItems map=list.get(position);
        holder.textView.setText(map.getC_name());



        return convertView;
    }


    static class ViewHolder{
        TextView textView;

    }
}
