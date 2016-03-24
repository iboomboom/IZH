package heiguang.com.mddemo.http;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by hiviiup on 16/3/23.
 *
 * 单例模式网络加载
 */
public class HttpHelper
{
    private static HttpHelper mHttpHelper = null;
    private OkHttpClient mClient = null;

    private HttpHelper()
    {
        mClient = new OkHttpClient();
    }

    public static HttpHelper getInstance()
    {
        if (mHttpHelper == null)
        {
            synchronized (HttpHelper.class)
            {
                if (mHttpHelper == null)
                {
                    mHttpHelper = new HttpHelper();
                }
            }
        }
        return mHttpHelper;

    }

    public void getAsyn(final String key, final BaseCallback callback) {
        ThreadManager.getLongPool().execute(new Runnable()
        {
            @Override
            public void run()
            {
                Request mRequst = new Request.Builder().url(Consts.URL+key).build();
                Call call = mClient.newCall(mRequst);
                call.enqueue(callback);
            }
        });

    }

    public void getAsyn(final String url, String key,final BaseCallback callback)
    {
        ThreadManager.getLongPool().execute(new Runnable()
        {
            @Override
            public void run()
            {
                Request mRequst = new Request.Builder().url(url).build();
                Call call = mClient.newCall(mRequst);
                call.enqueue(callback);
            }
        });
    }


}
