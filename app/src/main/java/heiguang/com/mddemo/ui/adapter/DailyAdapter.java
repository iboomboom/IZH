package heiguang.com.mddemo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import heiguang.com.mddemo.R;
import heiguang.com.mddemo.bean.NewDailys;
import heiguang.com.mddemo.ui.activity.WebActivity;

/**
 * Created by hiviiup on 16/3/24.
 */
public class DailyAdapter extends BaseAdapter<NewDailys.StoriesEntity>
{

    public DailyAdapter(Context mContext, boolean isLoadMore)
    {
        super(mContext, isLoadMore);
    }

    @Override
    public DailyViewHolder createMyViewHolder(ViewGroup parent, int viewType)
    {
        return new DailyViewHolder(View.inflate(mContext, R.layout.list_item_daily, null));
    }

    @Override
    protected void bindData(RecyclerView.ViewHolder holder, int position)
    {
        super.bindData(holder,position);
        if (getObject(position).getImages().size() == 0)
        {
            ((DailyViewHolder) holder).imageView.setVisibility(View.GONE);
        } else
        {
            ((DailyViewHolder) holder).imageView.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(getObject(position).getImages().get(0)).centerCrop().crossFade().into(((DailyViewHolder) holder).imageView);
        }

        ((DailyViewHolder) holder).textView.setText(getObject(position).getTitle());

    }

    @Override
    public void onItemClickListener(int position)
    {
        super.onItemClickListener(position);
        Intent intent = new Intent(mContext, WebActivity.class);
        intent.putExtra("id", getObject(position).getId());
        intent.putExtra("title", getObject(position).getTitle());
        mContext.startActivity(intent);
    }

    public class DailyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView textView;
        RelativeLayout relativeLayout;

        public DailyViewHolder(View itemView)
        {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageview);
            textView = (TextView) itemView.findViewById(R.id.title);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.ll);
        }
    }

}
