package com.coderpig.drysisters.imgloader;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * 描述：加载结果类
 *
 * @author coder-pig： 2016/08/17 11:09
 */
public class LoaderResult {
    public ImageView img;
    public String uri;
    public Bitmap bitmap;
    public int reqWidth;
    public int reqHeight;

    public LoaderResult(ImageView img, String uri, Bitmap bitmap, int reqWidth, int reqHeight) {
        this.img = img;
        this.uri = uri;
        this.bitmap = bitmap;
        this.reqWidth = reqWidth;
        this.reqHeight = reqHeight;
    }
}
