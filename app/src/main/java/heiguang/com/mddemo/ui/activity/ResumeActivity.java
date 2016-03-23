package heiguang.com.mddemo.ui.activity;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import heiguang.com.mddemo.R;
import heiguang.com.mddemo.ui.adapter.RecyclerAdapter;

/**
 * Created by hiviiup on 16/3/10.
 */
public class ResumeActivity extends AppCompatActivity
{

    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private CollapsingToolbarLayout ctl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);

        initToolbar();
        initView();

        addListener();

    }


    private void initToolbar()
    {

        //设置标题栏
        Toolbar toolbar = (Toolbar) findViewById(R.id.toobar);
        setSupportActionBar(toolbar);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null)
        {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        //设置title
        ctl = (CollapsingToolbarLayout) findViewById(R.id.collaps_toolbar_layout);
        ctl.setTitle("马佳宁");

        //设置视差背景图
        final ImageView imageView = (ImageView) findViewById(R.id.image);


//        blurImage(BitmapFactory.decodeResource(getResources(), R.drawable.cheese_4), imageView);

        Glide.with(this).load("http://image.wufazhuce.com/FkmnU4eAKdDzdPTVIkTr10PYH-GA").asBitmap().into(new SimpleTarget<Bitmap>()
        {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation)
            {
                blurImage(resource,imageView);
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void blurImage(final Bitmap resource, final ImageView imageView)
    {
        imageView.post(new Runnable()
        {
            @Override
            public void run()
            {
                Bitmap overlary = Bitmap.createBitmap(imageView.getMeasuredWidth(), imageView.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(overlary);
                canvas.drawBitmap(resource, -imageView.getLeft(), -imageView.getTop(), null);
                RenderScript renderScript = RenderScript.create(ResumeActivity.this);
                Allocation overlayAlloc = Allocation.createFromBitmap(renderScript, overlary);
                ScriptIntrinsicBlur scriptIntrinsicBlur = ScriptIntrinsicBlur.create(renderScript, overlayAlloc.getElement());
                scriptIntrinsicBlur.setInput(overlayAlloc);
                scriptIntrinsicBlur.setRadius(10f);
                scriptIntrinsicBlur.forEach(overlayAlloc);
                overlayAlloc.copyTo(overlary);
                imageView.setImageDrawable(new BitmapDrawable(overlary));
                renderScript.destroy();
            }
        });


    }

    private void initView()
    {
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(ResumeActivity.this));
        recyclerView.setAdapter(new RecyclerAdapter(ResumeActivity.this));

    }

    private void addListener()
    {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                refreshData();
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_resume, null);
        return super.onCreateOptionsMenu(menu);
    }

    public void refreshData()
    {
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                refreshLayout.setRefreshing(false);
            }
        }, 3000);
    }


}
