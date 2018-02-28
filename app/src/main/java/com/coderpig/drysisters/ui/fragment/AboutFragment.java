package com.coderpig.drysisters.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coderpig.drysisters.R;
import com.coderpig.drysisters.utils.PackageUtils;
import com.coderpig.drysisters.utils.ResUtils;

/**
 * 描述： 关于的Fragment
 *
 * @author CoderPig on 2018/02/28 14:33.
 */

public class AboutFragment extends Fragment {

    private TextView tv_app_version;

    public static AboutFragment newInstance() {
        AboutFragment fragment = new AboutFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_about, container, false);
        tv_app_version = view.findViewById(R.id.tv_app_version);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String version = PackageUtils.packageName();
        if(version != null) {
            String msg = String.format(ResUtils.getString(R.string.app_version), version);
            tv_app_version.setText(msg);
        }
    }
}
