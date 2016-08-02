package ru.yandex.yamblz.ui.recyclerview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;

import static android.support.v7.widget.helper.ItemTouchHelper.*;

/**
 * Created by platon on 01.08.2016.
 */
public class SimpleItemTouchHelper extends Callback
{
    private static final int rgb = 255;
    private ItemTouchHelperAdapter helperAdapter;

    public SimpleItemTouchHelper(ItemTouchHelperAdapter helperAdapter)
    {
        this.helperAdapter = helperAdapter;
    }
    
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder source, RecyclerView.ViewHolder target)
    {
        helperAdapter.onItemMove(source.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction)
    {
        helperAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder)
    {
        int dragFlags = UP | DOWN | LEFT | RIGHT;
        int swipeFlags = END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean isLongPressDragEnabled()
    {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled()
    {
        return true;
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recycler, RecyclerView.ViewHolder holder,
                            float dX, float dY, int actionState, boolean isCurrentlyActive)
    {
        switch (actionState)
        {
            case ACTION_STATE_SWIPE:

                final int greenAndBlue = (int) (rgb - Math.abs(dX) / 2);
                int color;
                if (greenAndBlue > 0) color = Color.rgb(rgb, greenAndBlue, greenAndBlue);
                else color = Color.rgb(rgb, rgb, rgb);
                c.drawColor(color);
                break;
        }

        super.onChildDraw(c, recycler, holder, dX, dY, actionState, isCurrentlyActive);
    }
}
