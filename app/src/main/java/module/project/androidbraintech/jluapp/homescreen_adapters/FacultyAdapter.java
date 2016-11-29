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
import module.project.androidbraintech.jluapp.activity.CoursesAndFeesActivity;
import module.project.androidbraintech.jluapp.activity.StudentProfileActivity;
import module.project.androidbraintech.jluapp.activity.TimeTableActivity;
import module.project.androidbraintech.jluapp.containers.ContentHomeScreenItems;

/**
 * Created by Tushar-PC on 19-08-2016.
 */
public class FacultyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    LayoutInflater mInflator;
    ArrayList<ContentHomeScreenItems> list=new ArrayList<ContentHomeScreenItems>();
    ContentHomeScreenItems map,m;


   public FacultyAdapter(Context context){
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




                }else if(m.getId()==2){


                }else if(m.getId()==3){


                }else if(m.getId()==4){



                }else if(m.getId()==5){


                    //CAMPUS NEWS

                    if(Utils.checkIfNetworkIsAvailable(context)) {
                        Intent intent = new Intent(context, CoursesAndFeesActivity.class);
                        context.startActivity(intent);
                    }



                }else if(m.getId()==6){





                }else if(m.getId()==7){


                    //CAMPUS LIFE

                    if(Utils.checkIfNetworkIsAvailable(context)) {
                        Intent intent = new Intent(context, CampusLifeActivity.class);
                        context.startActivity(intent);
                    }




                }else if(m.getId()==8){





                }else if(m.getId()==9){

                    //CAMPUS LIFE

                    if(Utils.checkIfNetworkIsAvailable(context)) {
                        Intent intent = new Intent(context, CampusLifeActivity.class);
                        context.startActivity(intent);
                    }




                }else if(m.getId()==10){




                }else if(m.getId()==11){




                }else if(m.getId()==12){





                }else if(m.getId()==13){




                }else if(m.getId()==14){





                }else if(m.getId()==15){





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

        content=new ContentHomeScreenItems(1,"Faculty Profile",R.drawable.guestimage);
        list.add(content);

        content=new ContentHomeScreenItems(2,"Your Modules",R.drawable.guestimage);
        list.add(content);

        content=new ContentHomeScreenItems(3,"Your Time Table",R.drawable.guestimage);
        list.add(content);

        content=new ContentHomeScreenItems(4,"Campus Alerts",R.drawable.guestimage);
        list.add(content);

        content=new ContentHomeScreenItems(5,"Campus News",R.drawable.guestimage);
        list.add(content);

        content=new ContentHomeScreenItems(6,"House Point Tables",R.drawable.guestimage);
        list.add(content);

        content=new ContentHomeScreenItems(7,"Campus Life",R.drawable.guestimage);
        list.add(content);

        content=new ContentHomeScreenItems(8,"Appointments",R.drawable.guestimage);
        list.add(content);

        content=new ContentHomeScreenItems(9,"Your Feedback",R.drawable.guestimage);
        list.add(content);

        content=new ContentHomeScreenItems(10,"Chat",R.drawable.guestimage);
        list.add(content);

        content=new ContentHomeScreenItems(11,"Campus Map",R.drawable.guestimage);
        list.add(content);

        content=new ContentHomeScreenItems(12,"Contact Directory",R.drawable.guestimage);
        list.add(content);


        return list;

    }
}
