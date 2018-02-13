package com.coderpig.drysisters.ui.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.coderpig.drysisters.R;
import com.coderpig.drysisters.bean.entity.Sister;
import com.coderpig.drysisters.db.SisterDBHelper;
import com.coderpig.drysisters.imgloader.PictureLoader;
import com.coderpig.drysisters.imgloader.SisterLoader;
import com.coderpig.drysisters.network.SisterApi;
import com.coderpig.drysisters.utils.NetworkUtils;

import java.util.ArrayList;


/**
 * 描述：妹看小姐姐的Fragment
 *
 * @author jay on 2018/1/12 12:19
 */

public class LittleSisterFragment extends Fragment implements View.OnClickListener {

    private Button previousBtn;
    private Button nextBtn;
    private ImageView showImg;
    private ArrayList<Sister> data;
    private int curPos = 0; //当前显示的是哪一张
    private int page = 1;   //当前页数
    private PictureLoader loader;
    private SisterApi sisterApi;
    private SisterTask sisterTask;
    private SisterLoader mLoader;
    private SisterDBHelper mDbHelper;
    private Context mContext;
    private TabLayout tl_little_sister;

    public static LittleSisterFragment newInstance() {
        LittleSisterFragment fragment = new LittleSisterFragment();
        return fragment;
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_little_sister, container, false);
        previousBtn = view.findViewById(R.id.btn_previous );
        nextBtn = view.findViewById(R.id.btn_next);
        showImg = view.findViewById(R.id.img_show);
        tl_little_sister = view.findViewById(R.id.tl_little_sister);
        tl_little_sister.addTab(tl_little_sister.newTab().setText("Gank.io"));
        tl_little_sister.addTab(tl_little_sister.newTab().setText("煎蛋"));
        tl_little_sister.addTab(tl_little_sister.newTab().setText("花瓣"));
        tl_little_sister.addTab(tl_little_sister.newTab().setText("BcyCos"));
        tl_little_sister.addTab(tl_little_sister.newTab().setText("图虫"));
        tl_little_sister.addTab(tl_little_sister.newTab().setText("妹子图"));
        previousBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
        return view;
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getActivity();
        sisterApi = new SisterApi();
        loader = new PictureLoader();
        mLoader = SisterLoader.getInstance(mContext);
        mDbHelper = SisterDBHelper.getInstance();
        data = new ArrayList<>();
        sisterTask = new SisterTask();
        sisterTask.execute();
    }

    private class SisterTask extends AsyncTask<Void, Void, ArrayList<Sister>> {

        SisterTask() {
        }

        @Override
        protected ArrayList<Sister> doInBackground(Void... params) {
            ArrayList<Sister> result = new ArrayList<>();
            if (page < (curPos + 1) / 10 + 1) {
                ++page;
            }
            //判断是否有网络
            if (NetworkUtils.isAvailable(mContext.getApplicationContext())) {
                result = sisterApi.fetchSister(10, page);
                //查询数据库里有多少个妹子，避免重复插入
                if (mDbHelper.getSistersCount() / 10 < page) {
                    mDbHelper.insertSisters(result);
                }
            } else {
                result.clear();
                result.addAll(mDbHelper.getSistersLimit(page - 1, 10));
            }
            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<Sister> sisters) {
            super.onPostExecute(sisters);
            data.addAll(sisters);
            if (data.size() > 0 && curPos + 1 < data.size()) {
                mLoader.bindBitmap(data.get(curPos).getUrl(), showImg, 400, 400);
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            sisterTask = null;
        }
    }

    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_previous:
                --curPos;
                if (curPos == 0) {
                    previousBtn.setVisibility(View.INVISIBLE);
                }
                if (curPos == data.size() - 1) {
                    sisterTask = new SisterTask();
                    sisterTask.execute();
                } else if (curPos < data.size()) {
                    mLoader.bindBitmap(data.get(curPos).getUrl(), showImg, 400, 400);
                }
                break;
            case R.id.btn_next:
                previousBtn.setVisibility(View.VISIBLE);
                if (curPos < data.size()) {
                    ++curPos;
                }
                if (curPos > data.size() - 1) {
                    sisterTask = new SisterTask();
                    sisterTask.execute();
                } else if (curPos < data.size()) {
                    mLoader.bindBitmap(data.get(curPos).getUrl(), showImg, 400, 400);
                }
                break;
        }
    }
}
