package ru.yandex.yamblz.ui.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import ru.yandex.yamblz.R;
import ru.yandex.yamblz.ui.recyclerview.ItemTouchHelperAdapter;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentHolder>
        implements ItemTouchHelperAdapter
{
    private final Random rnd = new Random();
    private final List<Integer> colors = new ArrayList<>();

    @Override
    public ContentHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return new ContentHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.content_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ContentHolder holder, int position)
    {
        holder.bind(createColorForPosition(position));
    }

    @Override
    public int getItemCount()
    {
        return Integer.MAX_VALUE;
    }

    private Integer createColorForPosition(int position)
    {
        if (position >= colors.size())
        {
            colors.add(Color.rgb(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)));
        }

        Log.d("Adapter", "size: " + colors.size());
        return colors.get(position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition)
    {
        Collections.swap(colors, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);

    }

    @Override
    public void onItemDismiss(int position)
    {
        colors.remove(position);
        notifyItemRemoved(position);
    }

    static class ContentHolder extends RecyclerView.ViewHolder
    {
        ContentHolder(View itemView)
        {
            super(itemView);
        }

        void bind(Integer color)
        {
            itemView.setBackgroundColor(color);
            ((TextView) itemView).setText("#".concat(Integer.toHexString(color).substring(2)));
        }
    }
}
