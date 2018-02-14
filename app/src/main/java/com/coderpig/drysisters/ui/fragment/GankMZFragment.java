package com.coderpig.drysisters.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coderpig.drysisters.R;
import com.coderpig.drysisters.data.dto.GankMeizi;
import com.coderpig.drysisters.net.APIService;
import com.coderpig.drysisters.ui.adapter.GankMZAdapter;
import com.coderpig.drysisters.utils.RxSchedulers;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 描述：Gank.io妹子Fragment
 *
 * @author CoderPig on 2018/02/14 09:49.
 */

public class GankMZFragment extends Fragment {

    private static final String TAG = "GankMZFragment";
    private SwipeRefreshLayout srl_refresh;
    private RecyclerView rec_mz;
    private CompositeDisposable mSubscriptions;
    private GankMZAdapter mAdapter;
    private static final int PRELOAD_SIZE = 6;
    private int mCurPage = 1;
    private ArrayList<GankMeizi> mData;

    public static GankMZFragment newInstance() {
        return new GankMZFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mz_content, container, false);
        srl_refresh = view.findViewById(R.id.srl_refresh);
        rec_mz = view.findViewById(R.id.rec_mz);
        srl_refresh.setOnRefreshListener(() -> {
            mCurPage = 1;
            fetchGankMZ(true);
        });
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        rec_mz.setLayoutManager(layoutManager);
        rec_mz.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {//加载更多
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    if (layoutManager.getItemCount() - recyclerView.getChildCount() <= layoutManager.findFirstVisibleItemPosition()) {
                        ++mCurPage;
                        fetchGankMZ(false);
                    }
                }
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSubscriptions = new CompositeDisposable();
        mData = new ArrayList<>();
        mAdapter = new GankMZAdapter(getActivity(), mData);
        rec_mz.setAdapter(mAdapter);
        srl_refresh.setRefreshing(true);
        fetchGankMZ(true);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mSubscriptions.clear();
    }

    /* 拉取妹子数据 */
    private void fetchGankMZ(boolean isRefresh) {
        Disposable subscribe = APIService.getInstance().apis.fetchGankMZ(20, mCurPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(subscription -> srl_refresh.setRefreshing(true))
                .doFinally(() -> srl_refresh.setRefreshing(false))
                .subscribe(data -> {
                    if (isRefresh) {
                        mAdapter.addAll(data.getResults());
                    } else {
                        mAdapter.loadMore(data.getResults());
                    }
                }, RxSchedulers::processRequestException);
        mSubscriptions.add(subscribe);
    }


}
