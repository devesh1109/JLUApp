package module.project.androidbraintech.jluapp.adapters;


import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

import module.project.androidbraintech.jluapp.R;
import module.project.androidbraintech.jluapp.Utilities.CustomFilter;
import module.project.androidbraintech.jluapp.Utilities.ItemClickListener;
import module.project.androidbraintech.jluapp.containers.ContentContactList;

public class ContactDirectoryAdapter extends RecyclerView.Adapter<MyHolder> implements Filterable {
    Context c;
    public ArrayList<ContentContactList> players;
    ArrayList<ContentContactList> filterList;

    CustomFilter filter;
    public ContactDirectoryAdapter(Context ctx, ArrayList<ContentContactList> players)
    {
        this.c=ctx;
        this.players=players;
        this.filterList=players;
    }






    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //CONVERT XML TO VIEW ONBJ
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,null);
        //HOLDER
        MyHolder holder=new MyHolder(v);
        return holder;
    }
    //DATA BOUND TO VIEWS
    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        //BIND DATA
        holder.posTxt.setText(players.get(position).getPos());
        holder.nameTxt.setText(players.get(position).getName());
        holder.numTxt.setText(players.get(position).getNum());
        //IMPLEMENT CLICK LISTENET
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Snackbar.make(v,players.get(pos).getName(),Snackbar.LENGTH_SHORT).show();
            }
        });
    }
    //GET TOTAL NUM OF PLAYERS
    @Override
    public int getItemCount() {
        return players.size();
    }

    @Override
    public Filter getFilter() {
        if(filter==null)
        {
            filter=new CustomFilter(filterList,this);
        }
        return filter;
    }
    //RETURN FILTER OBJ

}
 class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //OUR VIEWS

    TextView nameTxt,posTxt,numTxt;
    private ItemClickListener itemClickListener;


    public MyHolder(View itemView) {
        super(itemView);

        this.nameTxt= (TextView) itemView.findViewById(R.id.nameTxt);
        this.posTxt= (TextView) itemView.findViewById(R.id.posTxt);
        this.numTxt= (TextView) itemView.findViewById(R.id.numText);
        itemView.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v,getLayoutPosition());
    }
    public void setItemClickListener(ItemClickListener ic)
    {
        this.itemClickListener=ic;
    }
}
