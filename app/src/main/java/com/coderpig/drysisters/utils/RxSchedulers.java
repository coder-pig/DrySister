package com.coderpig.drysisters.utils;

import com.coderpig.drysisters.R;
import com.google.gson.JsonSyntaxException;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import cn.coderpig.cp_base.utils.ResUtilsKt;
import cn.coderpig.cp_base.utils.ToastUtilsKt;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * 描述：Rx 处理工具类
 *
 * @author CoderPig on 2018/02/14 11:06.
 */

public class RxSchedulers {
    public static <T> ObservableTransformer<T, T> compose() {
        return upstream ->
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
    }

    public static void processRequestException(Throwable e) {
        if(e instanceof ConnectException || e instanceof SocketException) {
            ToastUtilsKt.shortToast(ResUtilsKt.getTextRes(R.string.network_connected_exception));
        } else if(e instanceof SocketTimeoutException) {
            ToastUtilsKt.shortToast(ResUtilsKt.getTextRes(R.string.network_socket_time_out));
        } else if(e instanceof JsonSyntaxException) {
            ToastUtilsKt.shortToast(ResUtilsKt.getTextRes(R.string.network_json_syntax_exception));
        } else if(e instanceof UnknownHostException) {
            ToastUtilsKt.shortToast(ResUtilsKt.getTextRes(R.string.network_unknown_host));
        } else {
            Timber.d(e.getMessage());
        }
    }
}
