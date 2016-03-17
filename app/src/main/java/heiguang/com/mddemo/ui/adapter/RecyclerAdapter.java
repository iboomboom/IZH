package heiguang.com.mddemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import heiguang.com.mddemo.R;

/**
 * Created by hiviiup on 16/3/11.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.NormalViewHolder>
{

    Context context;
    String[] imageUrls;


    public RecyclerAdapter(Context context)
    {
        this.context = context;
        imageUrls = new String[]{"http://imgwww.heiguang.net/uploadfile/2016/0309/20160309102330531.jpg", "http://imgwww.heiguang.net/uploadfile/2016/0309/20160309102347916.jpg", "http://imgwww.heiguang.net/uploadfile/2016/0122/20160122113350447.jpg", "http://imgwww.heiguang.net/uploadfile/2016/0309/20160309102433574.jpg", "http://imgwww.heiguang.net/uploadfile/2016/0122/20160122113358900.jpg"};
    }

    @Override
    public NormalViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return new NormalViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_resume, null));
    }

    @Override
    public void onBindViewHolder(NormalViewHolder holder, int position)
    {

        Glide.with(context).load(imageUrls[position % 5]).centerCrop().crossFade().into(holder.imageView);
//        holder.imageView.setImageResource(R.drawable.cheese_4);
    }

    @Override
    public int getItemCount()
    {
        return 30;
    }

    public class NormalViewHolder extends RecyclerView.ViewHolder
    {

        ImageView imageView;

        public NormalViewHolder(View itemView)
        {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageview);
        }
    }
}
