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
import module.project.androidbraintech.jluapp.activity.ContactDirectory;
import module.project.androidbraintech.jluapp.activity.CoursesAndFeesActivity;
import module.project.androidbraintech.jluapp.activity.StudentProfileActivity;
import module.project.androidbraintech.jluapp.activity.TimeTableActivity;
import module.project.androidbraintech.jluapp.containers.ContentHomeScreenItems;

/**
 * Created by Tushar-PC on 19-08-2016.
 */
public class CurrentStudentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    LayoutInflater mInflator;
    ArrayList<ContentHomeScreenItems> list=new ArrayList<ContentHomeScreenItems>();
    ContentHomeScreenItems map,m;


   public CurrentStudentsAdapter(Context context){
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

                    //STUDENT PROFILE
                    Intent intent=new Intent(context, StudentProfileActivity.class);
                    context.startActivity(intent);


                }else if(m.getId()==2){

                    //your course and modules

                }else if(m.getId()==3){

                    //your attendance

                }else if(m.getId()==4){

                    //TIME TABLE

                    Intent intent=new Intent(context, TimeTableActivity.class);
                    context.startActivity(intent);




                }else if(m.getId()==5){



                    //campus alerts



                }else if(m.getId()==6){

                    //CAMPUS NEWS

                    if(Utils.checkIfNetworkIsAvailable(context)) {
                        Intent intent = new Intent(context, CampusNewsActivity.class);
                        context.startActivity(intent);
                    }



                }else if(m.getId()==7){

                    //house point table videos





                }else if(m.getId()==8){



                    //videos


                }else if(m.getId()==9){

                    //CAMPUS LIFE

                    if(Utils.checkIfNetworkIsAvailable(context)) {
                        Intent intent = new Intent(context, CampusLifeActivity.class);
                        context.startActivity(intent);
                    }




                }else if(m.getId()==10){


                    //faculty appointment


                }else if(m.getId()==11){



                    //your feedback

                }else if(m.getId()==12){


                    ///placement news



                }else if(m.getId()==13){

                   // chats



                }else if(m.getId()==14){


                    //campus map



                }else if(m.getId()==15){


                    //contact directory
                    if(Utils.checkIfNetworkIsAvailable(context)) {
                        Intent intent = new Intent(context, ContactDirectory.class);
                        context.startActivity(intent);
                    }


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

        content=new ContentHomeScreenItems(1,"Student Profile",R.drawable.guestimage);
        list.add(content);

        content=new ContentHomeScreenItems(2,"Your Course And Modules",R.drawable.fee);
        list.add(content);

        content=new ContentHomeScreenItems(3,"Your Attendance",R.drawable.guestimage);
        list.add(content);

        content=new ContentHomeScreenItems(4,"Your Time Table",R.drawable.guestimage);
        list.add(content);

        content=new ContentHomeScreenItems(5,"Campus Alerts",R.drawable.guestimage);
        list.add(content);

        content=new ContentHomeScreenItems(6,"Campus News",R.drawable.news);
        list.add(content);

        content=new ContentHomeScreenItems(7,"House Point Tables",R.drawable.life);
        list.add(content);

        content=new ContentHomeScreenItems(8,"Videos",R.drawable.play);
        list.add(content);

        content=new ContentHomeScreenItems(9,"Campus Life",R.drawable.life);
        list.add(content);

        content=new ContentHomeScreenItems(10,"Faculty Appointment",R.drawable.guestimage);
        list.add(content);

        content=new ContentHomeScreenItems(11,"Your FeedBack",R.drawable.imgg11);
        list.add(content);

        content=new ContentHomeScreenItems(12,"Placement News",R.drawable.guestimage);
        list.add(content);

        content=new ContentHomeScreenItems(13,"Chat",R.drawable.guestimage);
        list.add(content);

        content=new ContentHomeScreenItems(14,"Campus Map",R.drawable.guestimage);
        list.add(content);

        content=new ContentHomeScreenItems(15,"Contact Directory",R.drawable.guestimage);
        list.add(content);


        return list;

    }
}
