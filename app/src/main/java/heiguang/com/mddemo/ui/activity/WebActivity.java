package heiguang.com.mddemo.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.json.JSONException;

import heiguang.com.mddemo.R;
import heiguang.com.mddemo.bean.News;
import heiguang.com.mddemo.http.BaseCallback;
import heiguang.com.mddemo.http.Consts;
import heiguang.com.mddemo.http.HttpHelper;

/**
 * Created by hiviiup on 16/3/24.
 */
public class WebActivity extends BaseActivity
{

    private News news;
    private WebView mWebview;
    private ImageView imageView;
    private CollapsingToolbarLayout collapsingToolbarLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web);

        initToolbar();
        initView();

        bindData();
    }

    @Override
    public void initToolbar()
    {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void initView()
    {
        mWebview = (WebView) findViewById(R.id.webview);
        mWebview.setWebChromeClient(new WebChromeClient());
        mWebview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebview.getSettings().setJavaScriptEnabled(true);

        imageView = (ImageView) findViewById(R.id.imageview);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collaps_toolbar_layout);
        collapsingToolbarLayout.setTitle(getIntent().getStringExtra("title"));

    }

    public void bindData()
    {
        final BaseCallback mCssCallback = new BaseCallback(WebActivity.this)
        {
            @Override
            public void onSuccess(String response) throws JSONException
            {
                String css = response;
                String html = "<html><head><style>" + css + "</style></head><body>" + news.getBody() + "</body></html>";
                html = html.replace("<div class=\"img-place-holder\">", "");
                mWebview.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);

            }
        };

        BaseCallback mCallback = new BaseCallback(WebActivity.this)
        {


            @Override
            public void onSuccess(String response) throws JSONException
            {
                Gson gson = new Gson();
                news = gson.fromJson(response, News.class);

                Glide.with(WebActivity.this).load(news.getImage()).centerCrop().into(imageView);

                HttpHelper.getInstance().getAsyn(news.getCss().get(0),null,mCssCallback);
            }
        };

        HttpHelper.getInstance().getAsyn(Consts.STORY+getIntent().getIntExtra("id",0),mCallback);
    }

    @Override
    public void onMenuItemClickListener(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                break;
        }
        super.onMenuItemClickListener(item);
    }
}
