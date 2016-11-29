package module.project.androidbraintech.jluapp.Utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Tushar-PC on 07-03-2016.
 */
public class MySingletonForImage {
    private static MySingletonForImage mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static Context mCtx;

    public MySingletonForImage(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();


        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {

                        Log.d("getBitmap","MySingletonForImage");

                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {

                        Log.d("putBitmap", "MySingletonForImage");
                        cache.put(url, bitmap);



                    }


                });
    }

    public static synchronized MySingletonForImage getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MySingletonForImage(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {


        return mImageLoader;
    }
}