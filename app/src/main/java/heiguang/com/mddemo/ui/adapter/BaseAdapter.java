package heiguang.com.mddemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
    private View mHeaderView = null;
    private OnLoadMoreListener mLoadMoreListener;

    public static final int VIEW_TYPE_DEFAULT = 0;
    public static final int VIEW_TYPE_LOAD_MORE = 1;
    public static final int VIEW_TYPE_HEADER = 2;

    public BaseAdapter(Context mContext, boolean isLoadMore)
    {
        this.mContext = mContext;
        this.datas = new ArrayList<>();
        this.isLoadMore = isLoadMore;
    }

    /**
     * 给集合添加单个元素
     *
     * @param t
     */
    public void addObject(T t)
    {
        datas.add(t);
    }

    /**
     * 重置集合
     *
     * @param list
     */
    public void addAll(List<T> list)
    {
        datas.clear();
        datas.addAll(list);
    }

    /**
     * 移除集合中的某一个元素
     *
     * @param position
     */
    public void remove(int position)
    {
        datas.remove(position);
    }

    /**
     * 获取集合中某一位置的元素
     *
     * @param position
     * @return
     */
    public T getObject(int position)
    {
        return datas.get(position);
    }

    @Override
    public int getItemCount()
    {
        int i = isLoadMore ? datas.size() + 1 : datas.size();
        return getHeaderView() == null ? i : i + 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        RecyclerView.ViewHolder holder = null;
        //加载更多，不需要header
        if (isLoadMore && getHeaderView() == null)
        {
            switch (viewType)
            {
                case VIEW_TYPE_DEFAULT:
                    holder = createMyViewHolder(parent, viewType);
                    break;
                case VIEW_TYPE_LOAD_MORE:
                    holder = new LoadMoreViewHolder(View.inflate(mContext, R.layout.list_item_load_more, null));
                    break;
            }
        }
        //加载更多，需要header
        else if (isLoadMore && getHeaderView() != null)
        {
            switch (viewType)
            {
                case VIEW_TYPE_DEFAULT:
                    holder = createMyViewHolder(parent, viewType);
                    break;
                case VIEW_TYPE_LOAD_MORE:
                    holder = new LoadMoreViewHolder(View.inflate(mContext, R.layout.list_item_load_more, null));
                    break;
                case VIEW_TYPE_HEADER:
                    holder = new HeaderViewHolder(getHeaderView());
                    break;
            }
        }
        //不加载更多，需要heder
        else if (!isLoadMore && getHeaderView() != null)
        {
            switch (viewType)
            {
                case VIEW_TYPE_DEFAULT:
                    holder = createMyViewHolder(parent, viewType);
                    break;

                case VIEW_TYPE_HEADER:
                    holder = new HeaderViewHolder(getHeaderView());
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
        if (getItemViewType(position) == VIEW_TYPE_DEFAULT)
        {
            bindData(k, getHeaderView() == null ? position : position - 1);
        }
    }


    @Override
    public int getItemViewType(int position)
    {
        //加载更多，但是没有headerview
        if (isLoadMore && getHeaderView() == null)
        {
            return position == getItemCount() - 1 ? VIEW_TYPE_LOAD_MORE : VIEW_TYPE_DEFAULT;
        }
        //加载更多，同时需要headerview
        else if (isLoadMore && getHeaderView() != null)
        {
            if (position == 0)
            {
                return VIEW_TYPE_HEADER;
            } else if (position == getItemCount() - 1)
            {
                return VIEW_TYPE_LOAD_MORE;
            } else
            {
                return VIEW_TYPE_DEFAULT;
            }
        }
        //没有加载更多，但是需要添加headerview
        else if (!isLoadMore && getHeaderView() != null)
        {
            return position == 0 ? VIEW_TYPE_HEADER : VIEW_TYPE_DEFAULT;
        }
        //两者都不需要的时候添加
        else
        {
            return super.getItemViewType(position);
        }
    }

    /**
     * 创建viewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    public abstract RecyclerView.ViewHolder createMyViewHolder(ViewGroup parent, int viewType);

    /**
     * Viewhoder数据绑定的方法
     *
     * @param holder
     * @param position
     */
    protected void bindData(RecyclerView.ViewHolder holder, final int position)
    {
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onItemClickListener(position);
            }
        });
    }

    /**
     * 添加headerview
     *
     * @param view
     */
    public void addHeaderView(View view)
    {
        mHeaderView = view;
    }

    /**
     * 获取headerview
     *
     * @return
     */
    private View getHeaderView()
    {
        return mHeaderView;
    }


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
                    if (lastVisibleItemPosition == itemCount - 1 && isSlidingBottom) //说明滑动到了底部
                    {
                        //Toast.makeText(mContext,"滑动到底部了",Toast.LENGTH_SHORT).show();
                        mLoadMoreListener.onLoadMore();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                //判断是否是向下活动
                isSlidingBottom = dy > 0 ? true : false;
            }
        });
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mListener)
    {
        mLoadMoreListener = mListener;
    }


    /**
     * 给每一个条目添加点击事件
     *
     * @param position
     */
    public void onItemClickListener(int position)
    {
    }

    /**
     * 当调用加载更多的时候，交给当前页面做数据处理
     */
    public interface OnLoadMoreListener
    {
        public void onLoadMore();
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

    /**
     * 头布局的viewholder
     */
    public class HeaderViewHolder extends RecyclerView.ViewHolder
    {

        public HeaderViewHolder(View itemView)
        {
            super(itemView);
        }
    }
}
