package module.project.androidbraintech.jluapp.Utilities;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * Created by Tushar-PC on 07-07-2016.
 */


public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {
    private final int mSpace;
    public SimpleDividerItemDecoration(int space) {
        this.mSpace = space;
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = mSpace;
        outRect.right = mSpace;
        outRect.bottom = mSpace;

    }
}