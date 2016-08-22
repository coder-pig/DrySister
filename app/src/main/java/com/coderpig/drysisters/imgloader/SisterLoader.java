package com.coderpig.drysisters.imgloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.coderpig.drysisters.R;
import com.coderpig.drysisters.imgloader.helper.DiskCacheHelper;
import com.coderpig.drysisters.imgloader.helper.MemoryCacheHelper;
import com.coderpig.drysisters.imgloader.helper.NetworkHelper;
import com.coderpig.drysisters.utils.NetworkUtils;
import com.coderpig.drysisters.utils.SizeUtils;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述：图片加载的主要控制类
 *
 * @author coder-pig： 2016/08/10 11:05
 */
public class SisterLoader {

    private static final String TAG = "SisterLoader";

    public static final int MESSAGE_POST_RESULT = 1;
    private static final int TAG_KEY_URI = R.id.sister_loader_uri;  //一个常量值，setTag用到

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();    //获取CPU个数
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;    //核心线程数
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1; //最大线程池大小
    private static final long KEEP_ALIVE = 10L; //线程空闲时间

    private Context mContext;
    private MemoryCacheHelper mMemoryHelper;
    private DiskCacheHelper mDiskHelper;

    /** 线程工厂创建线程 */
    private static final ThreadFactory mFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r,"SisterLoader#" + mCount.getAndIncrement());
        }
    };

    /** 线程池管理线程*/
    public static final Executor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(
            CORE_POOL_SIZE, MAXIMUM_POOL_SIZE,
            KEEP_ALIVE, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(), mFactory);

    private Handler mMainHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            LoaderResult result = (LoaderResult) msg.obj;
            ImageView resultImage = result.img;
            //设置图片大小，加载图片
            ViewGroup.LayoutParams params = resultImage.getLayoutParams();
            params.width = SizeUtils.dp2px(mContext.getApplicationContext(),result.reqWidth);
            params.height = SizeUtils.dp2px(mContext.getApplicationContext(),result.reqHeight);
            resultImage.setLayoutParams(params);
            resultImage.setImageBitmap(result.bitmap);
            String uri = (String) resultImage.getTag(TAG_KEY_URI);
            if (uri.equals(result.uri)) {
                resultImage.setImageBitmap(result.bitmap);
            } else {
                Log.w(TAG, "URL发生改变，不设置图片");
            }
        }
    };

    public static SisterLoader getInstance(Context context) {
        return new SisterLoader(context);
    }

    private SisterLoader(Context context) {
        mContext = context.getApplicationContext();
        mMemoryHelper = new MemoryCacheHelper(mContext);
        mDiskHelper = new DiskCacheHelper(mContext);
    }

    /** 同步加载图片,该方法只能在主线程执行 */
    private Bitmap loadBitmap(String url, int reqWidth, int reqHeight) {
        final String key = NetworkHelper.hashKeyFormUrl(url);
        //先到内存缓存里找
        Bitmap bitmap = mMemoryHelper.getBitmapFromMemoryCache(key);
        if (bitmap != null) {
            return bitmap;
        }
        //到磁盘缓存里找
        try {
            bitmap = mDiskHelper.loadBitmapFromDiskCache(key,reqWidth,reqHeight);
            //如果磁盘缓存中找到，往内存缓存里面塞一个
            if (bitmap != null) {
                mMemoryHelper.addBitmapToMemoryCache(key, bitmap);
                return bitmap;
            }
            //磁盘里也找不到，加载网络
            if(NetworkUtils.isAvailable(mContext)) {
                bitmap = mDiskHelper.saveImgByte(key,reqWidth,reqHeight,NetworkHelper.downloadUrlToStream(url));
                Log.d(TAG,"加载网络上的图片，URL：" + url);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        if(bitmap == null && !mDiskHelper.getIsDiskCacheCreate()) {
            Log.w(TAG,"磁盘缓存未创建！");
            bitmap = NetworkHelper.downloadBitmapFromUrl(url);
        }
        return bitmap;
    }

    /** 异步加载图片 */
    public void bindBitmap(final String url, final ImageView imageView,
                           final int reqWidth, final int reqHeight) {
        final String key = NetworkHelper.hashKeyFormUrl(url);
        imageView.setTag(TAG_KEY_URI, url);
        Runnable loadBitmapTask = new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = loadBitmap(url,reqWidth,reqHeight);
                if(bitmap != null) {
                    LoaderResult result = new LoaderResult(imageView,url,bitmap,reqWidth,reqHeight);
                    mMainHandler.obtainMessage(MESSAGE_POST_RESULT,result).sendToTarget();
                }
            }
        };
        THREAD_POOL_EXECUTOR.execute(loadBitmapTask);
    }
}
