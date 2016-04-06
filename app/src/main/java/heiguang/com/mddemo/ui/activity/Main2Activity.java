package heiguang.com.mddemo.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;


import java.text.SimpleDateFormat;
import java.util.Date;

import heiguang.com.mddemo.R;
import heiguang.com.mddemo.bean.NewDailys;
import heiguang.com.mddemo.http.BaseCallback;
import heiguang.com.mddemo.http.Consts;
import heiguang.com.mddemo.http.HttpHelper;
import heiguang.com.mddemo.ui.adapter.BaseAdapter;
import heiguang.com.mddemo.ui.adapter.DailyAdapter;
import heiguang.com.mddemo.ui.adapter.MyPagerAdapter;
import heiguang.com.mddemo.ui.view.CBSwipeRefreshLayout;

public class Main2Activity extends BaseActivity
{

    private RecyclerView mRecyclerView;
    private ViewPager mViewPager;
    private DailyAdapter mAdapter;
    private CBSwipeRefreshLayout mRefresh;
    private BaseCallback dailyCallback;

    int page = 0;
    //获取当前日期
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyyMMdd");
    String curDate = mFormat.format(new Date(System.currentTimeMillis()));
    private MyPagerAdapter mPagerAdapter;
    private SwipeRefreshLayout.OnRefreshListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initToolbar();
        initView();

        bindData();
        addListener();

    }


    @Override
    public void initToolbar()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("爱知乎");
        setSupportActionBar(toolbar);
    }

    @Override
    public void initView()
    {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(Main2Activity.this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new DailyAdapter(Main2Activity.this, true);
        mAdapter.configLoadListener(mRecyclerView);
        mAdapter.addHeaderView(initHeaderView());
        mRecyclerView.setAdapter(mAdapter);
        mRefresh = (CBSwipeRefreshLayout) findViewById(R.id.refresh);

    }

    private View initHeaderView()
    {
        final View view = View.inflate(this, R.layout.activity_main_header, null);
        mViewPager = (ViewPager) view.findViewById(R.id.vp);
        mPagerAdapter = new MyPagerAdapter(this);
        mViewPager.setAdapter(mPagerAdapter);
        return view;
    }

    private void addListener()
    {
        listener = new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                HttpHelper.getInstance().getAsyn(Consts.LASTEST, dailyCallback);
            }
        };
        mRefresh.setOnRefreshListener(listener);
        mRefresh.post(new Runnable()
        {
            @Override
            public void run()
            {
                mRefresh.setRefreshing(true);
            }
        });
        listener.onRefresh();


        mAdapter.setOnLoadMoreListener(new BaseAdapter.OnLoadMoreListener()
        {
            @Override
            public void onLoadMore()
            {
                //加载获取前一天的数据
                String beforeDate = mFormat.format(new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000 * page));
                Toast.makeText(Main2Activity.this, beforeDate, Toast.LENGTH_SHORT).show();
                HttpHelper.getInstance().getAsyn(Consts.BEFORE + beforeDate, dailyCallback);
            }
        });

    }


    private void bindData()
    {
        dailyCallback = new BaseCallback(Main2Activity.this)
        {
            @Override
            public void onSuccess(String response)
            {
                Gson gson = new Gson();
                NewDailys mNewDailys = gson.fromJson(response, NewDailys.class);

                if (curDate.equals(mNewDailys.getDate()))
                {//刷新或者刚进来的时候
                    mAdapter.addAll(mNewDailys.getStories());
                    mPagerAdapter.addAll(mNewDailys.getTop_stories());
                    mRefresh.postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            mRefresh.setRefreshing(false);
                        }
                    },1000);

                } else
                {//加载之前数据的时候
                    for (NewDailys.StoriesEntity storiesEntity : mNewDailys.getStories())
                    {
                        mAdapter.addObject(storiesEntity);
                    }
                    page++;
                }
                mAdapter.notifyDataSetChanged();
                mPagerAdapter.notifyDataSetChanged();
            }
        };
    }

    @Override
    public void initMenu(Menu menu)
    {
        super.initMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
    }

    @Override
    public void onBackPressed()
    {
        System.exit(0);
    }
}
