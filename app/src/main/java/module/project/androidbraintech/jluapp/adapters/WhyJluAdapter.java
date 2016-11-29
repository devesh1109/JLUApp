package module.project.androidbraintech.jluapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import module.project.androidbraintech.jluapp.R;
import module.project.androidbraintech.jluapp.Utilities.MySingletonForImage;
import module.project.androidbraintech.jluapp.containers.ContentWhyJlu;

/**
 * Created by Devesh 1 on 04-08-2016.
 */

    public class WhyJluAdapter extends RecyclerView.Adapter<WhyJluAdapter.myViewHolder>{
    private ArrayList<ContentWhyJlu> data;
    private Context context;

        public class myViewHolder extends RecyclerView.ViewHolder{
            public NetworkImageView mNetworkImageView;
            public TextView title,text;
           public myViewHolder(View v){
            super(v);
            mNetworkImageView = (NetworkImageView) v.findViewById(R.id.image);
            title = (TextView)v.findViewById(R.id.title);
            text =(TextView)v.findViewById(R.id.text);
        }
    }

    public WhyJluAdapter(ArrayList<ContentWhyJlu> data, Context context)
    {
        this.data = data;
        this.context = context;
    }


    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()
        ).inflate(R.layout.cardview_list,parent,false);

        return new myViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {


        try {
            ContentWhyJlu object = data.get(position);

            final ImageLoader mImageLoader = MySingletonForImage.getInstance(context).getImageLoader();
            holder.mNetworkImageView.setImageUrl(object.getWj_url(), mImageLoader);
            holder.title.setText(object.getWj_title());
            holder.text.setText(object.getWj_text());

        }catch (Exception e){

            Log.e("OnBindExcp",e.toString());
        }

    }

    @Override
    public int getItemCount() {
         return data.size();

    }
}


