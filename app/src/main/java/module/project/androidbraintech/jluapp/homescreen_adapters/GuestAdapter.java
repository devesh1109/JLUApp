package module.project.androidbraintech.jluapp.homescreen_adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import module.project.androidbraintech.jluapp.R;
import module.project.androidbraintech.jluapp.Utilities.Utils;
import module.project.androidbraintech.jluapp.activity.CampusLifeActivity;
import module.project.androidbraintech.jluapp.activity.CampusNewsActivity;
import module.project.androidbraintech.jluapp.activity.CityOfBhopalActivity;
import module.project.androidbraintech.jluapp.activity.ContactDirectory;
import module.project.androidbraintech.jluapp.activity.CoursesAndFeesActivity;
import module.project.androidbraintech.jluapp.activity.EnquiryActivity;
import module.project.androidbraintech.jluapp.activity.WhyJluActivity;
import module.project.androidbraintech.jluapp.activity.howToReachUs;
import module.project.androidbraintech.jluapp.containers.ContentHomeScreenItems;

/**
 * Created by Tushar-PC on 19-08-2016.
 */
public class GuestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    LayoutInflater mInflator;
    ArrayList<ContentHomeScreenItems> list=new ArrayList<ContentHomeScreenItems>();
    ContentHomeScreenItems map,m;


   public GuestAdapter(Context context){
        this.context=context;
        mInflator=LayoutInflater.from(context);
        list=setAdapterList();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=mInflator.inflate(R.layout.content_hs,null);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        map=list.get(position);

        ((ViewHolder)holder).textView.setText(map.getText());
        ((ViewHolder)holder).image.setImageResource(map.getImage());




        ((ViewHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                m=list.get(holder.getAdapterPosition());
                Toast.makeText(context,m.getId()+"  "+m.getText(),Toast.LENGTH_SHORT).show();

                if(m.getId()==1){

                    //WHY JLU

                    if(Utils.checkIfNetworkIsAvailable(context)) {
                        Intent intent = new Intent(context, WhyJluActivity.class);
                        context.startActivity(intent);
                    }



                }else if (m.getId()==2){
                    //COURSES AND FEES

                    if(Utils.checkIfNetworkIsAvailable(context)) {
                        Intent intent = new Intent(context, CoursesAndFeesActivity.class);
                        context.startActivity(intent);
                    }


                }else if (m.getId()==3){
                    //CAMPUS NEWS

                    if(Utils.checkIfNetworkIsAvailable(context)) {
                        Intent intent = new Intent(context, CampusNewsActivity.class);
                        context.startActivity(intent);
                    }



                }else if (m.getId()==4){
                    //VIDEOS



                }else if (m.getId()==5){
                    //CAMPUS LIFE

                    if(Utils.checkIfNetworkIsAvailable(context)) {
                        Intent intent = new Intent(context, CampusLifeActivity.class);
                        context.startActivity(intent);
                    }



                }else if (m.getId()==6){
                    //ENQUIRY


                    if(Utils.checkIfNetworkIsAvailable(context)) {
                        Intent intent = new Intent(context, EnquiryActivity.class);
                        context.startActivity(intent);
                    }



                }else if (m.getId()==7){
                    //CITY OF BHOPAL

                    if(Utils.checkIfNetworkIsAvailable(context)) {
                        Intent intent = new Intent(context, CityOfBhopalActivity.class);
                        context.startActivity(intent);
                    }


                }else if (m.getId()==8){
                    //HOW TO REACH US
                    Intent intent = new Intent(context, howToReachUs.class);
                    context.startActivity(intent);



                }

            }
        });




    }



    @Override
    public int getItemCount() {
        return list.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView textView;

        public ViewHolder(View view) {
            super(view);

            image=(ImageView)view.findViewById(R.id.cimage);
            textView=(TextView)view.findViewById(R.id.ctext);

        }
    }



    ArrayList<ContentHomeScreenItems> setAdapterList(){

        ContentHomeScreenItems content;
        ArrayList<ContentHomeScreenItems> list=new ArrayList<ContentHomeScreenItems>();

        content=new ContentHomeScreenItems(1,"Why JLU",R.drawable.whyjlu);
        list.add(content);

        content=new ContentHomeScreenItems(2,"Courses and Fees",R.drawable.fee);
        list.add(content);

        content=new ContentHomeScreenItems(3,"Campus News",R.drawable.news);
        list.add(content);

        content=new ContentHomeScreenItems(4,"Videos",R.drawable.play);
        list.add(content);

        content=new ContentHomeScreenItems(5,"Campus Life",R.drawable.life);
        list.add(content);

        content=new ContentHomeScreenItems(6,"Enquiry",R.drawable.enq);
        list.add(content);

        content=new ContentHomeScreenItems(7,"City Of Bhopal",R.drawable.bpl);
        list.add(content);

        content=new ContentHomeScreenItems(8,"How To Reach Us",R.drawable.imgg11);
        list.add(content);

        return list;

    }
}
