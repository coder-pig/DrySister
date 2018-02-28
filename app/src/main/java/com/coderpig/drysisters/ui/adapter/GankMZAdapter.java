package com.coderpig.drysisters.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.coderpig.drysisters.R;
import com.coderpig.drysisters.data.dto.GankMeizi;
import com.coderpig.drysisters.ui.activity.PictureDetailActivity;

import java.util.ArrayList;

/**
 * 描述：
 *
 * @author CoderPig on 2018/02/14 12:15.
 */

public class GankMZAdapter extends RecyclerView.Adapter<GankMZAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<GankMeizi> mzs = new ArrayList<>();

    public GankMZAdapter(Context mContext, ArrayList<GankMeizi> mzs) {
        this.mContext = mContext;
        this.mzs = mzs;
    }

    public void addAll(ArrayList<GankMeizi> data) {
        mzs.clear();
        mzs.addAll(data);
        notifyDataSetChanged();
    }

    public void loadMore(ArrayList<GankMeizi> data) {
        mzs.addAll(data);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mz, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mzs.get(position));
    }

    @Override
    public int getItemCount() {
        return mzs.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_content;

        ViewHolder(View itemView) {
            super(itemView);
            img_content = itemView.findViewById(R.id.img_content);
        }

        void bind(GankMeizi data) {
            Glide.with(mContext)
                    .load(data.getUrl())
                    .apply(new RequestOptions()
                            .centerCrop())
                    .into(img_content);
            img_content.setOnClickListener(view -> {
                Intent intent = new Intent(mContext, PictureDetailActivity.class);
                intent.putExtra("pic_url", data.getUrl());
                mContext.startActivity(intent);
            });
        }
    }
}
