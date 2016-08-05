package ru.yandex.yamblz.ui.recyclerview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import static android.support.v7.widget.RecyclerView.ViewHolder;

/**
 * Created by platon on 01.08.2016.
 */
public class SimpleItemAnimator extends DefaultItemAnimator
{
    @Override
    public boolean canReuseUpdatedViewHolder(ViewHolder viewHolder)
    {
        return true;
    }

    @Override
    public boolean animateChange(@NonNull ViewHolder oldHolder, @NonNull ViewHolder newHolder, @NonNull ItemHolderInfo preInfo, @NonNull ItemHolderInfo postInfo)
    {
        if (oldHolder != newHolder)
        {
            return super.animateChange(oldHolder, newHolder, preInfo, postInfo);
        }

        View view = newHolder.itemView;

        ObjectAnimator alphaAnim, scaleXAnim, scaleYAnim;

        alphaAnim = ObjectAnimator.ofFloat(view, "alpha", 0, 1);
        scaleXAnim = ObjectAnimator.ofFloat(view, "scaleX", 0, 1);
        scaleYAnim = ObjectAnimator.ofFloat(view, "scaleY", 0, 1);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(200);
        animatorSet.playTogether(alphaAnim, scaleXAnim, scaleYAnim);
        animatorSet.start();

        return true;
    }
}
