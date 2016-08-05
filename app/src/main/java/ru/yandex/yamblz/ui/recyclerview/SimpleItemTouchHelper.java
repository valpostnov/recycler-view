package ru.yandex.yamblz.ui.recyclerview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import static android.support.v7.widget.helper.ItemTouchHelper.*;

/**
 * Created by platon on 01.08.2016.
 */
public class SimpleItemTouchHelper extends Callback
{
    private static final int RGB = 255;
    private ItemTouchHelperAdapter helperAdapter;
    private final Paint paint;
    private final Rect rect;

    public SimpleItemTouchHelper(ItemTouchHelperAdapter helperAdapter)
    {
        this.helperAdapter = helperAdapter;
        rect = new Rect();
        paint = new Paint();
        paint.setColor(Color.WHITE);
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

                final int greenAndBlue = (int) (RGB - Math.abs(dX) / 3);

                if (greenAndBlue > 0)
                {
                    drawRect(c, holder.itemView, Color.rgb(RGB, greenAndBlue, greenAndBlue));
                }
                else
                {
                    drawRect(c, holder.itemView, Color.rgb(RGB, RGB, RGB));
                }

                break;
        }

        super.onChildDraw(c, recycler, holder, dX, dY, actionState, isCurrentlyActive);
    }

    private void drawRect(Canvas c, View view, int color)
    {
        paint.setColor(color);

        rect.set(view.getLeft(),
                view.getTop(),
                view.getRight(),
                view.getBottom());

        c.drawRect(rect, paint);
    }
}
