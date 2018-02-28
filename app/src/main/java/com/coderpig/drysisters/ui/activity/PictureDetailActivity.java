package com.coderpig.drysisters.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.coderpig.drysisters.R;
import com.r0adkll.slidr.Slidr;

/**
 * 描述：图片详情页
 *
 * @author CoderPig on 2018/02/28 10:39.
 */

public class PictureDetailActivity extends AppCompatActivity{

    private ImageView img_picture;
    private String picUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_detail);
        Slidr.attach(this);
        initData();
        initView();
    }

    private void initData() {
        picUrl = getIntent().getStringExtra("pic_url");
    }

    private void initView() {
        img_picture = findViewById(R.id.img_picture);
        if(picUrl != null) {
            Glide.with(this).load(picUrl).into(img_picture);
        }
    }
}
