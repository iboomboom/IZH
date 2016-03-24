package heiguang.com.mddemo.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;

import com.google.gson.Gson;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Date;

import heiguang.com.mddemo.R;
import heiguang.com.mddemo.bean.NewDailys;
import heiguang.com.mddemo.http.BaseCallback;
import heiguang.com.mddemo.http.Consts;
import heiguang.com.mddemo.http.HttpHelper;
import heiguang.com.mddemo.ui.adapter.DailyAdapter;
import heiguang.com.mddemo.ui.view.CBSwipeRefreshLayout;
import heiguang.com.mddemo.ui.view.DividerItemDecoration;

public class Main2Activity extends BaseActivity
{

    private RecyclerView mRecyclerView;
    private DailyAdapter mAdapter;
    private CBSwipeRefreshLayout mRefresh;
    private BaseCallback dailyCallback;

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
        mRecyclerView.addItemDecoration(new DividerItemDecoration(Main2Activity.this, DividerItemDecoration.VERTICAL_LIST));

        mAdapter = new DailyAdapter(Main2Activity.this);
        mRecyclerView.setAdapter(mAdapter);

        mRefresh = (CBSwipeRefreshLayout) findViewById(R.id.refresh);
    }

    private void addListener()
    {
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                //刷新获取前一天的数据
                SimpleDateFormat mFormat = new SimpleDateFormat("yyyyMMdd");
                String date = mFormat.format(new Date(System.currentTimeMillis()-24*60*60*1000));
                HttpHelper.getInstance().getAsyn(Consts.BEFORE+date,dailyCallback);
            }
        });
    }

    @Override
    public void initMenu(Menu menu)
    {
        super.initMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main,menu);
    }

    private void bindData()
    {
        dailyCallback = new BaseCallback(Main2Activity.this)
        {
            @Override
            public void onSuccess(String response) throws JSONException
            {
                Gson gson = new Gson();
                NewDailys mNewDailys = gson.fromJson(response,NewDailys.class);
                mAdapter.addAll(mNewDailys.getStories());
                mAdapter.notifyDataSetChanged();

                if (mRefresh.isRefreshing())
                {
                    mRefresh.setRefreshing(false);
                }
            }
        };
        HttpHelper.getInstance().getAsyn(Consts.LASTEST, dailyCallback);
    }



    @Override
    public void onBackPressed()
    {
        System.exit(0);
    }
}
