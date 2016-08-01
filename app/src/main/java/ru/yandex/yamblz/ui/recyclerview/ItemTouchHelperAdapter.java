package ru.yandex.yamblz.ui.recyclerview;

/**
 * Created by platon on 01.08.2016.
 */
public interface ItemTouchHelperAdapter
{
    void onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
