package com.ro0kiey.igank.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ro0kiey.igank.R;
import com.ro0kiey.igank.model.Bean.GankBean;

import java.util.List;

/**
 * Created by Ro0kieY on 2017/7/12.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<GankBean> mGankBeanList;

    public ListAdapter(List<GankBean> gankBeanList) {
        this.mGankBeanList = gankBeanList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_tablayout_fragment_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GankBean gankBean = mGankBeanList.get(position);
        holder.textView.setText(gankBean.getDesc());
    }

    @Override
    public int getItemCount() {
        return mGankBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.rv_tablayout_image);
            textView = (TextView)itemView.findViewById(R.id.rv_tablayout_text);
        }
    }
}
