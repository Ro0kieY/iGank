package com.ro0kiey.igank.Widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;

import com.github.clans.fab.FloatingActionMenu;

/**
 * Created by Ro0kieY on 2017/7/21.
 */

public class IRecyclerView extends RecyclerView {

    private LoadMoreListener listener;
    private FloatingActionMenu actionMenu;
    private LayoutManager manager;


    public IRecyclerView(Context context) {
        super(context);
    }

    public IRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public IRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setLoadMoreListener(LoadMoreListener listener){
        this.listener = listener;
    }

    public void setFloatActionMenu(FloatingActionMenu actionMenu){
        this.actionMenu = actionMenu;
    }

    @Override
    public void onScrolled(int dx, int dy) {
        Log.d("IRV", "onScrolled: dy " + dy);
        if (actionMenu != null){
            if (dy > 0){
                if (actionMenu.isShown()){
                    actionMenu.hideMenu(true);
                }
            } else if (!actionMenu.isShown()) {
                actionMenu.showMenu(true);
            }
        }
        manager = getLayoutManager();
        int[] pos = new int[2];
        ((StaggeredGridLayoutManager) manager).findLastVisibleItemPositions(pos);
        int totalItemCount = manager.getItemCount();
        if (pos[1] >= totalItemCount - (((StaggeredGridLayoutManager) manager).getSpanCount() * 2)){
            listener.loadMore();
        }
    }

    public interface LoadMoreListener{
        void loadMore();
    }
}
