package heiguang.com.mddemo.http;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
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

        Message message = Message.obtain();
        message.obj = e.getMessage();
        message.what= 1;
        mHandler.sendMessage(message);

    }

    @Override
    public void onResponse(Call call, final Response response) throws IOException
    {
        final String result = response.body().string();
        Message message = Message.obtain();
        message.obj = result;
        message.what = 0;//成功
        mHandler.sendMessage(message);
    }

    /**
     * 提供一个加载成功的回调
     *
     * @param response
     */
    public void onSuccess(String response) throws JSONException
    {
        try
        {
            mJsonObject = new JSONObject(response);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 提供一个加载失败的回调
     */
    public void onFail()
    {

    }


    private Handler mHandler = new Handler(new Handler.Callback()
    {
        @Override
        public boolean handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case 0:
                    try
                    {
                        onSuccess((String) msg.obj);
                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                    break;

                case 1:
                    Toast.makeText(mActivity, "网络加载出错：" + msg.obj, Toast.LENGTH_SHORT).show();
                    onFail();
                    break;
            }
            return true;
        }
    });
}
