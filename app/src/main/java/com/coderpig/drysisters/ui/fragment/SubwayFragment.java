package com.coderpig.drysisters.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coderpig.drysisters.R;

/**
 * 描述：看天气的Fragment
 *
 * @author jay on 2018/1/12 12:19
 */

public class SubwayFragment extends Fragment {

    public static SubwayFragment newInstance() {
        SubwayFragment fragment = new SubwayFragment();
        return fragment;
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_subway, container,false);
    }
}
