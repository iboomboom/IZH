package heiguang.com.mddemo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONException;

import java.io.IOException;

import heiguang.com.mddemo.R;
import heiguang.com.mddemo.http.BaseCallback;
import heiguang.com.mddemo.http.Consts;
import heiguang.com.mddemo.http.HttpHelper;
import okhttp3.Response;

/**
 * Created by hiviiup on 16/3/23.
 */
public class SplashActivity extends BaseActivity
{

    ImageView ivSplash;
    TextView tvAuthor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();
        loadStartImage();

    }


    @Override
    public void initToolbar()
    {

    }

    @Override
    public void initView()
    {
        ivSplash = (ImageView) findViewById(R.id.iv_splash);
        tvAuthor = (TextView) findViewById(R.id.tv_author);
    }

    /**
     * 加载启动页
     */
    private void loadStartImage()
    {
        BaseCallback mCallback = new BaseCallback(this)
        {
            @Override
            public void onSuccess(String response) throws JSONException
            {
                super.onSuccess(response);

                String text = mJsonObject.getString("text");
                String picUrl = mJsonObject.getString("img");

                tvAuthor.setText("By " + text);
                Glide.with(SplashActivity.this).load(picUrl).diskCacheStrategy(DiskCacheStrategy.ALL).animate(getAnimation()).into(ivSplash);
            }
        };

        HttpHelper.getInstance().getAsyn(Consts.SPLASH, mCallback);
    }

    /**
     * 启动页加载动画
     * @return
     */
    public ScaleAnimation getAnimation()
    {
        ScaleAnimation mAnimation = new ScaleAnimation(1f,1.05f,1f,1.05f,0.5f,0.5f);
        mAnimation.setDuration(3000);
        mAnimation.setFillAfter(true);
        mAnimation.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {
            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                SplashActivity.this.startActivity(new Intent(SplashActivity.this,Main2Activity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {
            }
        });
        return mAnimation;
    }

    @Override
    public void onBackPressed()
    {
    }
}
