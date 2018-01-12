package com.coderpig.drysisters.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coderpig.drysisters.R;
import com.coderpig.drysisters.bean.entity.Sister;
import com.coderpig.drysisters.bean.entity.SisterResult;
import com.coderpig.drysisters.network.NetworkService;
import com.coderpig.drysisters.utils.LogUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;


/**
 * 描述：妹看小姐姐的Fragment
 *
 * @author jay on 2018/1/12 12:19
 */

public class MeiziFragment extends Fragment {

    private SwipeRefreshLayout srl_refresh;
    private RecyclerView rec_meizi;
    private Handler mHandler = new Handler();

    public static MeiziFragment newInstance() {
        MeiziFragment fragment = new MeiziFragment();
        return fragment;
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meizi, container,false);
        srl_refresh = view.findViewById(R.id.srl_refresh);
        rec_meizi = view.findViewById(R.id.rec_meizi);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        srl_refresh.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        srl_refresh.setOnRefreshListener(this::refreshMeizi);
    }

    /* 刷新妹子 */
    private void refreshMeizi() {
        new Thread(()  -> {
            final String data = NetworkService.INSTANCE.fetchMeizi(10,1);
            Gson gson = new Gson();
            Type type = new TypeToken<SisterResult>() {}.getType();
            SisterResult result = gson.fromJson(data, type);
            showUI(result);
        }).start();
    }

    /* 加载更多妹子 */
    private void loadMoreMeizi() {

    }

    private void showUI(SisterResult data) {
        mHandler.post(() -> {
            for(Sister sister: data.getResults()) {
                Log.e("HEHE", sister.getUrl());
            }
        });
    }

}
