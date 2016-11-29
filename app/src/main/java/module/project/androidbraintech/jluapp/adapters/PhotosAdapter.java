package module.project.androidbraintech.jluapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import module.project.androidbraintech.jluapp.R;
import module.project.androidbraintech.jluapp.Utilities.MySingletonForImage;
import module.project.androidbraintech.jluapp.containers.ContainerCampusLife;


/**
 * Created by apple on 22/08/16.
 */

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {



    private ImageLoader imageLoader;
    private Context context;

    //List of superHeroes
    List<ContainerCampusLife> picList;

    public PhotosAdapter(List<ContainerCampusLife> picList, Context context){
        super();
        //Getting all the photos
        this.picList = picList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_view_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ContainerCampusLife photo = picList.get(position);

        imageLoader = MySingletonForImage.getInstance(context).getImageLoader();
       // imageLoader.get(photo.getImgUrl(), ImageLoader.getImageListener(holder.imageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));


        holder.imageView.setImageUrl(photo.getImgUrl(), imageLoader);
        holder.pic_title.setText(photo.getPic_title());
        holder.pic_text.setText(String.valueOf(photo.getPic_text()));



    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public NetworkImageView imageView;
        public TextView pic_title;
        public TextView pic_text;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    //  perform your action here
              //      Intent intent = new Intent(MainActivity.this, fullScreenActivity.class);
                //    startActivity(intent);
                }
            });
            imageView = (NetworkImageView) itemView.findViewById(R.id.img);
            pic_title = (TextView) itemView.findViewById(R.id.pic_title);
            pic_text= (TextView) itemView.findViewById(R.id.pic_text);

        }
    }

    /*public ViewHolder(View view) {
        super(view);
        mNetworkImageView = (NetworkImageView) view.findViewById(R.id.img);
        pic_title = (TextView) view.findViewById(R.id.pic_title);
        pic_text= (TextView) view.findViewById(R.id.pic_text);

    }

    //List of Photos
    private List<Photos> picList;

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_view_card,parent,false);
        PhotosAdapter.ViewHolder myViewHolder = new PhotosAdapter.MyViewHolder(itemView);
        return myViewHolder;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_view_card, parent, false);
        RecyclerView.ViewHolder viewHolder = new RecyclerView.ViewHolder();
        return viewHolder;

    }

    public PhotosAdapter(List<Photos> picList, Context context){
        super();
        this.picList = picList;
        this.context = context;
    }
*/
    /*@Override
    public PhotosAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_view_card,parent,false);
        PhotosAdapter.MyViewHolder myViewHolder = new PhotosAdapter.MyViewHolder(itemView);
        return myViewHolder;

    }*/

    /*@Override
    public void onBindViewHolder(PhotosAdapter.MyViewHolder holder, int position) {

        Photos photos = picList.get(position);

        imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
        imageLoader.get(photos.getImgUrl(),imageLoader.getImageListener(holder.mNetworkImageView,R.mipmap.ic_launcher,R.mipmap.ic_launcher));

        holder.mNetworkImageView.setImageUrl(photos.getImgUrl(), imageLoader);
        holder.pic_title.setText(photos.getPic_title());
        holder.pic_text.setText(photos.getPic_text());
        //holder.img.setImageDrawable(null);
       // ImageLoader imageLoader = MySingletonForImage.getInstance(context).getImageLoader();
        //holder.mNetworkImageView.setImageUrl(photos.getImgUrl(),imageLoader);
    }*/
    @Override
    public int getItemCount() {
        return picList.size();
    }
}
