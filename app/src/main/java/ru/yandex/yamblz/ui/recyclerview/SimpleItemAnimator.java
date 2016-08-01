package ru.yandex.yamblz.ui.recyclerview;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;

/**
 * Created by platon on 01.08.2016.
 */
public class SimpleItemAnimator extends DefaultItemAnimator
{
    @Override
    public boolean animateRemove(RecyclerView.ViewHolder holder)
    {
        return super.animateRemove(holder);
    }

    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder)
    {
        return super.animateAdd(holder);
    }

    @Override
    public boolean animateMove(RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY)
    {
        return super.animateMove(holder, fromX, fromY, toX, toY);
    }

    @Override
    public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromX, int fromY, int toX, int toY)
    {
        return super.animateChange(oldHolder, newHolder, fromX, fromY, toX, toY);
    }

    @Override
    public void endAnimation(RecyclerView.ViewHolder item)
    {
        super.endAnimation(item);
    }
}
