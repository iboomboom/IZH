package heiguang.com.mddemo.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @param <T> 模型类型
 * @param <K> ViewHolder
 */
public abstract class BaseAdapter<T,K extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<K>
{

    public Activity mActivity;
    private List<T> datas = new ArrayList<>();

    public BaseAdapter(Activity activity)
    {
        this.mActivity = activity;
    }

    public void addObject(T t)
    {
        datas.add(t);
    }

    public void addAll(List<T> list)
    {
        datas.clear();
        datas.addAll(list);
    }

    public void remove(int position)
    {
        datas.remove(position);
    }

    public T getObject(int position)
    {
        return datas.get(position);
    }


    @Override
    public K onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return createMyViewHolder(parent,viewType);
    }


    @Override
    public void onBindViewHolder(K k, int position)
    {
        bindData(k,position);
    }

    @Override
    public int getItemCount()
    {
        return datas.size();
    }

    public abstract K createMyViewHolder(ViewGroup parent, int viewType);

    protected abstract void bindData(K k, int position);



}
