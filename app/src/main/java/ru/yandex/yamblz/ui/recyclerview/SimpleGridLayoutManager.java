package ru.yandex.yamblz.ui.recyclerview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by platon on 05.08.2016.
 */
public class SimpleGridLayoutManager extends GridLayoutManager
{
    public SimpleGridLayoutManager(Context context, int spanCount)
    {
        super(context, spanCount);
    }

    @Override
    public boolean supportsPredictiveItemAnimations()
    {
        return true;
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state)
    {
        super.onLayoutChildren(recycler, state);
    }
}
