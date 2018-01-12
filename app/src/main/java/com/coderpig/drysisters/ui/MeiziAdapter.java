package com.coderpig.drysisters.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.coderpig.drysisters.bean.entity.Sister;

import java.util.ArrayList;

/**
 * Created by jay on 18-1-12.
 *
 * 妹子列表的Adapter
 */

public class MeiziAdapter extends RecyclerView.Adapter<MeiziAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Sister> sisters;

    private void addAll(ArrayList<Sister> data) {
        sisters.clear();
        sisters.addAll(data);
        notifyDataSetChanged();
    }

    private void loadMore(ArrayList<Sister> data) {
        sisters.addAll(data);
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
