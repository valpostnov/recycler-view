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
    private ItemTouchHelperAdapter helperAdapter;
    private float currentDx;
    private int blueGreen = 255;

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

                if (dX > currentDx)
                {
                    if (blueGreen >= 0) blueGreen -= 10;

                    int color = Color.rgb(255, blueGreen, blueGreen);
                    c.drawColor(color);
                    currentDx = dX;
                }
                else
                {
                    blueGreen = 255;
                    currentDx = dX;
                }

                break;
        }

        super.onChildDraw(c, recycler, holder, dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recycler, RecyclerView.ViewHolder holder,
                                float dX, float dY, int actionState, boolean isCurrentlyActive)
    {
        switch (actionState)
        {
            case ACTION_STATE_DRAG:

        }
        super.onChildDrawOver(c, recycler, holder, dX, dY, actionState, isCurrentlyActive);
    }
}
