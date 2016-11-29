package module.project.androidbraintech.jluapp.Utilities;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import module.project.androidbraintech.jluapp.R;


/**
 * Created by Tushar-PC on 07-07-2016.
 */


public class SimpleDividerItemGridDecoration extends RecyclerView.ItemDecoration {
    private final int mSpace;
    public SimpleDividerItemGridDecoration(int space) {
        this.mSpace = space;
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = mSpace;
        outRect.right = mSpace;
        outRect.bottom = mSpace;
        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildAdapterPosition(view) == 0 || parent.getChildAdapterPosition(view) == 1)
            outRect.top = mSpace+20;
    }
}