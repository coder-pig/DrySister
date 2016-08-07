package com.coderpig.drysisters.ui.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.coderpig.drysisters.R;
import com.coderpig.drysisters.bean.entity.Sister;
import com.coderpig.drysisters.network.SisterApi;
import com.coderpig.drysisters.imgloader.PictureLoader;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button showBtn;
    private Button refreshBtn;
    private ImageView showImg;

    private ArrayList<Sister> data;
    private int curPos = 0; //当前显示的是哪一张
    private int page = 1;   //当前页数
    private PictureLoader loader;
    private SisterApi sisterApi;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sisterApi = new SisterApi();
        loader = new PictureLoader();
        initData();
        initUI();
    }

    private void initData() {
        data = new ArrayList<>();
        new SisterTask(page).execute();
    }

    private void initUI() {
        showBtn = (Button) findViewById(R.id.btn_show);
        refreshBtn = (Button) findViewById(R.id.btn_refresh);
        showImg = (ImageView) findViewById(R.id.img_show);

        showBtn.setOnClickListener(this);
        refreshBtn.setOnClickListener(this);
    }

    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show:
                if(data != null && !data.isEmpty()) {
                    if (curPos > 9) {
                        curPos = 0;
                    }
                    loader.load(showImg, data.get(curPos).getUrl());
                    curPos++;
                }
                break;
            case R.id.btn_refresh:
                page++;
                new SisterTask(page).execute();
                curPos = 0;
                break;
        }
    }

    private class SisterTask extends AsyncTask<Void,Void,ArrayList<Sister>> {

        private int page;

        public SisterTask(int page) {
            this.page = page;
        }

        @Override
        protected ArrayList<Sister> doInBackground(Void... params) {
            return sisterApi.fetchSister(10,page);
        }

        @Override
        protected void onPostExecute(ArrayList<Sister> sisters) {
            super.onPostExecute(sisters);
            data.clear();
            data.addAll(sisters);
        }
    }

}
