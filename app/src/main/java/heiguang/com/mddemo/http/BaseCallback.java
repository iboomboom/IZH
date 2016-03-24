package heiguang.com.mddemo.http;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by hiviiup on 16/3/23.
 */
public class BaseCallback implements Callback
{

    private Activity mActivity;
    public JSONObject mJsonObject;

    public BaseCallback(Activity activity)
    {
        this.mActivity = activity;
    }

    @Override
    public void onFailure(Call call, final IOException e)
    {
        mActivity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                Toast.makeText(mActivity, "网络加载出错：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                onFail();
            }
        });

    }

    @Override
    public void onResponse(Call call, final Response response) throws IOException
    {
        final String result = response.body().string();
        mActivity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    onSuccess(result);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }


    /**
     * 提供一个加载成功的回调
     * @param response
     */
    public void onSuccess(String response) throws JSONException
    {
        mJsonObject = new JSONObject(response);
    }

    /**
     * 提供一个加载失败的回调
     */
    public void onFail()
    {

    }
}
