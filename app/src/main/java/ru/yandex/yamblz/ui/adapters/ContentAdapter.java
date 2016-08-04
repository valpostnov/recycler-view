package ru.yandex.yamblz.ui.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import ru.yandex.yamblz.R;
import ru.yandex.yamblz.ui.recyclerview.ItemTouchHelperAdapter;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentHolder>
        implements ItemTouchHelperAdapter
{
    private static OnItemClickListener sOnItemClickListener;
    private final Random rnd = new Random();
    private final List<Integer> colors = new ArrayList<>();
    private int fromPosition = -1;
    private int toPosition = -1;


    public interface OnItemClickListener
    {
        void onClick(int position);
    }

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
        else
        {
            colors.add(position, Color.rgb(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)));
        }

        return colors.get(position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition)
    {
        Collections.swap(colors, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
    }

    @Override
    public void onItemDismiss(int position)
    {
        colors.remove(position);
        notifyItemRemoved(position);
    }

    public int getFromPosition()
    {
        return fromPosition;
    }

    public int getToPosition()
    {
        return toPosition;
    }

    public static class ContentHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ContentHolder(View itemView)
        {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        void bind(Integer color)
        {
            itemView.setBackgroundColor(color);
            ((TextView) itemView).setText("#".concat(Integer.toHexString(color).substring(2)));
        }

        @Override
        public void onClick(View v)
        {
            sOnItemClickListener.onClick(getAdapterPosition());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        sOnItemClickListener = listener;
    }
}
