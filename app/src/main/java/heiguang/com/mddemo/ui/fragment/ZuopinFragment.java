package heiguang.com.mddemo.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import heiguang.com.mddemo.ui.view.CBSwipeRefreshLayout;
import heiguang.com.mddemo.R;

/**
 * Created by hiviiup on 16/3/10.
 */
public class ZuopinFragment extends Fragment
{

    public static ZuopinFragment newInstance()
    {
        Bundle args = new Bundle();
        ZuopinFragment fragment = new ZuopinFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_zuopin, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        Glide.with(this).load(R.drawable.london_flat).centerCrop().into(imageView);

        final CBSwipeRefreshLayout refreshLayout = (CBSwipeRefreshLayout) view.findViewById(R.id.refresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        refreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });

        return view;
    }
}
