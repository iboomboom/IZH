package heiguang.com.mddemo.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import heiguang.com.mddemo.R;

/**
 * Created by hiviiup on 16/3/14.
 */
public class SearchActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initToolbar();
    }

    private void initToolbar()
    {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toobar);
        mToolbar.setTitle("搜索");
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        final MenuItem item = menu.findItem(R.id.menu_search);
        SearchView mSearchView = (SearchView) item.getActionView();

        mSearchView.setQueryHint("关键词");
        mSearchView.setIconifiedByDefault(true);
        mSearchView.setIconified(false);

        return super.onCreateOptionsMenu(menu);
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
}
