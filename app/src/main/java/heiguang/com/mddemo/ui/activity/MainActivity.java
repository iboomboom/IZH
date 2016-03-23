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

/**
 * 我的作品集
 */
public class MainActivity extends AppCompatActivity
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

    private void initToolbar()
    {
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toobar);

        toolbar.setTitle("我的作品集");
        setSupportActionBar(toolbar);
    }

    private void initView()
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
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Intent intent;

        switch (item.getItemId())
        {
            case R.id.menu_resume:
                intent = new Intent(MainActivity.this,ResumeActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_contacts:
                intent = new Intent(MainActivity.this,ImageActivity.class);
                startActivity(intent);
                break;

            case R.id.search:
                intent = new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
