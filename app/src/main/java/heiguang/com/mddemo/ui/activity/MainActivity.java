package heiguang.com.mddemo.ui.activity;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import heiguang.com.mddemo.R;
import heiguang.com.mddemo.ui.adapter.ViewPagerAdapter;
import heiguang.com.mddemo.ui.fragment.ZuopinFragment;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 我的作品集
 *
 * 这个类已经过时
 * @see Main2Activity
 */
@Deprecated
public class MainActivity extends BaseActivity
{

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initView();

    }

    public void initToolbar()
    {
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toobar);

        toolbar.setTitle("知乎日报");
        setSupportActionBar(toolbar);

    }

    public void initView()
    {
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.vp);

        setFragment();

        tabLayout.setupWithViewPager(viewPager);

    }

    private void setFragment()
    {
        ViewPagerAdapter mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mAdapter.addFragment(ZuopinFragment.newInstance(), "好逑");
        mAdapter.addFragment(ZuopinFragment.newInstance(), "快闪到家");
        mAdapter.addFragment(ZuopinFragment.newInstance(), "众点众易");
        viewPager.setAdapter(mAdapter);

    }


    @Override
    public void initMenu(Menu menu)
    {
        super.initMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main,menu);
    }

    @Override
    public void onMenuItemClickListener(MenuItem item)
    {
        Intent intent;

        switch (item.getItemId())
        {
            case R.id.menu_resume:
                intent = new Intent(MainActivity.this,ResumeActivity.class);
                startActivity(intent);
                break;

            case R.id.search:
                intent = new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);
                break;
        }
        super.onMenuItemClickListener(item);
    }

    @Override
    public void onBackPressed()
    {
        System.exit(0);
    }
}
