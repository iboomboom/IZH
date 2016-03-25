package heiguang.com.mddemo.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import heiguang.com.mddemo.R;

/**
 * @param <T> 模型类型
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    public Context mContext;
    private List<T> datas;
    private boolean isLoadMore;

    public BaseAdapter(Context mContext, boolean isLoadMore)
    {
        this.mContext = mContext;
        this.datas = new ArrayList<>();
        this.isLoadMore = isLoadMore;
    }

    /**
     * 给集合添加单个元素
     * @param t
     */
    public void addObject(T t)
    {
        datas.add(t);
    }

    /**
     * 重置集合
     * @param list
     */
    public void addAll(List<T> list)
    {
        datas.clear();
        datas.addAll(list);
    }

    /**
     * 移除集合中的某一个元素
     * @param position
     */
    public void remove(int position)
    {
        datas.remove(position);
    }

    /**
     * 获取集合中某一位置的元素
     * @param position
     * @return
     */
    public T getObject(int position)
    {
        return datas.get(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        RecyclerView.ViewHolder holder = null;
        if(isLoadMore)
        {
            switch (viewType)
            {
                case 0:
                    holder = createMyViewHolder(parent,viewType);
                    break;
                case 1:
                    holder = new LoadMoreViewHolder(View.inflate(mContext,R.layout.list_item_load_more,null));
                    break;

            }
        } else
        {
            holder = createMyViewHolder(parent, viewType);
        }

        return holder;

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder k, int position)
    {
        if (!isLoadMore)
        {
            bindData(k, position);
        } else
        {
            switch (getItemViewType(position))
            {
                case 0:
                    bindData(k,position);
                    break;
                case 1:
                    break;
            }
        }

    }

    @Override
    public int getItemCount()
    {
        return isLoadMore ? datas.size() + 1 : datas.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        //判断是否需要加载更多的功能
        if (isLoadMore)
        {
            if (position == getItemCount()-1)
            {
                return 1;
            } else
            {
                return 0;
            }
        } else
        {
            return super.getItemViewType(position);
        }

    }

    /**
     * 创建viewHolder
     * @param parent
     * @param viewType
     * @return
     */
    public abstract RecyclerView.ViewHolder createMyViewHolder(ViewGroup parent, int viewType);

    /**
     * Viewhoder数据绑定的抽象方法
     * @param k
     * @param position
     */
    protected abstract void bindData(RecyclerView.ViewHolder k, int position);


    /**
     * 当调用加载更多的时候，交给当前页面做数据处理
     */
    public void onLoadMoreListener() {};
    /**
     * 当需要加载更多的功能的时候，给recycleview添加滑动监听事件
     */
    public void configLoadListener(RecyclerView mRecyclerView)
    {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            //是否是向下滑动的
            boolean isSlidingBottom = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                //获取LinearLayoutManager
                LinearLayoutManager mManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                {
                    //获取最后一个item的position
                    final int lastVisibleItemPosition = mManager.findLastVisibleItemPosition();
                    final int itemCount = mManager.getItemCount();
                    if (lastVisibleItemPosition == itemCount -1 && isSlidingBottom) //说明滑动到了底部
                    {
                        //Toast.makeText(mContext,"滑动到底部了",Toast.LENGTH_SHORT).show();
                        onLoadMoreListener();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                //判断是否是向下活动
                if (dy >0)
                {
                    isSlidingBottom = true;
                } else
                {
                    isSlidingBottom = false;
                }
            }
        });
    }


    /**
     * 加载更多的holder
     */
    public class LoadMoreViewHolder extends RecyclerView.ViewHolder
    {

        public LoadMoreViewHolder(View itemView)
        {
            super(itemView);
        }
    }


}
