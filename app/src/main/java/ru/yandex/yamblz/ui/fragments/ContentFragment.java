package ru.yandex.yamblz.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import ru.yandex.yamblz.R;
import ru.yandex.yamblz.ui.adapters.ContentAdapter;
import ru.yandex.yamblz.ui.recyclerview.SimpleItemTouchHelper;

public class ContentFragment extends BaseFragment {

    private GridLayoutManager gridLayoutManager;
    private static final int SPAN_COUNT_DEF = 2;
    private ContentAdapter adapter;

    @BindView(R.id.rv)
    RecyclerView rv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        gridLayoutManager = new GridLayoutManager(getContext(), SPAN_COUNT_DEF);
        adapter = new ContentAdapter();
        rv.setLayoutManager(gridLayoutManager);
        rv.setAdapter(adapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelper(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(rv);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_two_columns:
                changeSpanCount(2);
                return true;

            case R.id.action_three_columns:
                changeSpanCount(3);
                return true;

            case R.id.action_thirty_columns:
                changeSpanCount(30);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void changeSpanCount(int spanCount)
    {
        int firstItem = gridLayoutManager.findFirstVisibleItemPosition();
        gridLayoutManager.setSpanCount(spanCount);
        adapter.notifyItemRangeChanged(firstItem, 0);
    }
}
