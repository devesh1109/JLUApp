package module.project.androidbraintech.jluapp.Utilities;

import android.widget.Filter;

import java.util.ArrayList;

import module.project.androidbraintech.jluapp.adapters.ContactDirectoryAdapter;
import module.project.androidbraintech.jluapp.containers.ContentContactList;

/**
 * Created by Devesh 1 on 17-11-2016.
 */
public class CustomFilter extends Filter{
    ContactDirectoryAdapter adapter;
    ArrayList<ContentContactList> filterList;
    public CustomFilter(ArrayList<ContentContactList> filterList,ContactDirectoryAdapter adapter)
    {
        this.adapter=adapter;
        this.filterList=filterList;
    }
    //FILTERING OCURS
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();
        //CHECK CONSTRAINT VALIDITY
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<ContentContactList> filteredPlayers=new ArrayList<>();
            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getName().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredPlayers.add(filterList.get(i));
                }
            }
            results.count=filteredPlayers.size();
            results.values=filteredPlayers;
        }else
        {
            results.count=filterList.size();
            results.values=filterList;
        }
        return results;
    }
    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.players= (ArrayList<ContentContactList>) results.values;
        //REFRESH
        adapter.notifyDataSetChanged();
    }
}
