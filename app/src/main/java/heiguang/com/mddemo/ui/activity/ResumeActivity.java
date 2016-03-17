package heiguang.com.mddemo.ui.activity;

import android.os.Bundle;
import android.os.Handler;
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
        ImageView imageView = (ImageView) findViewById(R.id.image);
        Glide.with(this).load("http://image.wufazhuce.com/FgFzVj6XM0PViWM-1srcGNxnBJmU").centerCrop().into(imageView);

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
