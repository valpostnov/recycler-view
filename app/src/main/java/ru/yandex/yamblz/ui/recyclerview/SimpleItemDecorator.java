package ru.yandex.yamblz.ui.recyclerview;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import ru.yandex.yamblz.ui.adapters.ContentAdapter;

import static android.graphics.Paint.Style.STROKE;

/**
 * Created by platon on 01.08.2016.
 */
public class SimpleItemDecorator extends RecyclerView.ItemDecoration
{
    private static final int STROKE_WIDTH = 10;
    private final Paint paint;
    private final Rect rect;

    public SimpleItemDecorator()
    {
        rect = new Rect();
        paint = new Paint();
        paint.setStyle(STROKE);
        paint.setStrokeWidth(STROKE_WIDTH);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state)
    {
        final ContentAdapter adapter = (ContentAdapter) parent.getAdapter();
        final int childCount = parent.getChildCount();
        final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

        for (int i = 0; i < childCount; i++)
        {
            final View childView = parent.getChildAt(i);
            final int adapterPosFrom = adapter.getFromPosition();
            final int adapterPosTo = adapter.getToPosition();
            final int layoutPos = parent.getChildLayoutPosition(childView);

            if (adapterPosFrom == layoutPos || adapterPosTo == layoutPos)
            {
                rect.set(layoutManager.getDecoratedLeft(childView),
                        layoutManager.getDecoratedTop(childView),
                        layoutManager.getDecoratedRight(childView),
                        layoutManager.getDecoratedBottom(childView));

                rect.inset(STROKE_WIDTH, STROKE_WIDTH);

                c.drawRect(rect, paint);
            }
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
    {
        //outRect.set(10, 10, 10, 10);
    }
}
