package heiguang.com.mddemo.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import heiguang.com.mddemo.R;

/**
 * Created by hiviiup on 16/3/19.
 */
public class ImageActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        initToolbar();

        initView();
    }

    private void initToolbar()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toobar);
        toolbar.setTitle("图集");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initView()
    {
        final ImageView imageView = (ImageView) findViewById(R.id.image);
        Glide.with(this).load("https://img1.doubanio.com/view/photo/photo/public/p2122961898.jpg").diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().into(imageView);
//        imageView.setImageResource(R.drawable.image);
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
