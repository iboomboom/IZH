package heiguang.com.mddemo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import heiguang.com.mddemo.R;
import heiguang.com.mddemo.bean.NewDailys;
import heiguang.com.mddemo.ui.activity.WebActivity;

/**
 * Created by hiviiup on 16/4/5.
 */
public class MyPagerAdapter<T> extends PagerAdapter
{

    private Context mContext;
    private List<NewDailys.TopStoriesEntity> datas ;

    public MyPagerAdapter(Context mContext)
    {
        this.mContext = mContext;
        datas = new ArrayList<>();
    }

    public void addAll(List<NewDailys.TopStoriesEntity> list)
    {
        datas.clear();
        datas.addAll(list);
    }

    @Override
    public int getCount()
    {
        return datas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        View view = View.inflate(mContext, R.layout.vp,null);

        view.setOnClickListener(new MyOnClickListener(position));

        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        TextView textView = (TextView) view.findViewById(R.id.textview);

        Glide.with(mContext).load(datas.get(position).getImage()).crossFade().into(imageView);
        textView.setText(datas.get(position).getTitle());

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        container.removeView((View) object);
    }


    private class MyOnClickListener implements View.OnClickListener
    {
        private int position;

        public MyOnClickListener(int position)
        {
            this.position = position;
        }

        @Override
        public void onClick(View v)
        {
            Intent intent = new Intent(mContext, WebActivity.class);
            intent.putExtra("title",datas.get(position).getTitle());
            intent.putExtra("id",datas.get(position).getId());
            mContext.startActivity(intent);
        }
    }
}
