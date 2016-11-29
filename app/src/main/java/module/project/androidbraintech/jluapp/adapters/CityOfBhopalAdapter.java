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
import module.project.androidbraintech.jluapp.containers.ContentCityOfBhopal;

/**
 * Created by apple on 24/09/16.
 */

public class CityOfBhopalAdapter extends RecyclerView.Adapter<CityOfBhopalAdapter.myViewHolder>{
    private ArrayList<ContentCityOfBhopal> data;
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

    public CityOfBhopalAdapter(ArrayList<ContentCityOfBhopal> data, Context context)
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
            ContentCityOfBhopal object = data.get(position);
            ImageLoader mImageLoader = MySingletonForImage.getInstance(context).getImageLoader();
            holder.mNetworkImageView.setImageUrl(object.getCob_url(), mImageLoader);
            holder.title.setText(object.getCob_title());
            holder.text.setText(object.getCob_text());
        }catch (Exception e){

            Log.e("COB bind view error",e.toString());
        }

    }

    @Override
    public int getItemCount() {
        return data.size();

    }
}