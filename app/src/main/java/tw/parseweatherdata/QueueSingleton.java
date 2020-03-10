package tw.parseweatherdata;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

// 單例模式 - 避免在Request的時候都新增一個Queue
// 參考Android developers: https://developer.android.com/training/volley/requestqueue#java
public class QueueSingleton {
    private static QueueSingleton mInstance;
    private RequestQueue mRequestQueue;
    private static Context mContext;

    private QueueSingleton(Context context) {
        mContext = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized QueueSingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new QueueSingleton(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

}
