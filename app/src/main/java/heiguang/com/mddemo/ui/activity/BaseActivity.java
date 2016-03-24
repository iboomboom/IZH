package heiguang.com.mddemo.ui.activity;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseActivity extends AppCompatActivity
{

    //定义一个list集合管理所有正在运行的actvity
    public static final ArrayList<BaseActivity> mActivities = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //将所有打开的activity添加进入集合
        synchronized (mActivities)
        {
            mActivities.add(this);
        }

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        synchronized (mActivities)
        {
            mActivities.remove(this);
        }
    }



    /**
     * 提供一个finish掉当前运行的所有app的方法，此方法经常用于登录操作，用来重新启动一个activity
     */
    public void finishAllRunningActivity()
    {
        // 复制了一份mActivities 集合
        List<BaseActivity> copy;
        synchronized (mActivities) {
            copy = new ArrayList<>(mActivities);
        }
        for (BaseActivity activity : copy) {
            activity.finish();
        }
        // 杀死当前的进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        initMenu(menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        onMenuItemClickListener(item);
        return super.onOptionsItemSelected(item);
    }

    public abstract void initToolbar();

    public abstract void initView();

    /**
     * 给Toolbar设置menu布局
     * @param menu
     */
    public void initMenu(Menu menu) {}

    /**
     * 给menu添加点击事件
     * @param item
     */
    public void onMenuItemClickListener(MenuItem item) {}
}
