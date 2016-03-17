package heiguang.com.mddemo.ui.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

public class CBSwipeRefreshLayout extends SwipeRefreshLayout
{
    private int mTouchSlop;
    private float mPrevX;
    // Indicate if we've already declined the move event
    private boolean mDeclined;

//    public CBSwipeRefreshLayout(Context context) {
//        super(context);
//    }

    public CBSwipeRefreshLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        if (!isEnabled())
        {
            return false;
        }
        switch (ev.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                mPrevX = MotionEvent.obtain(ev).getX();
                mDeclined = false; // New action
                break;

            case MotionEvent.ACTION_MOVE:
                final float eventX = ev.getX();
                float xDiff = Math.abs(eventX - mPrevX);

                if (mDeclined || xDiff > mTouchSlop)
                {
                    mDeclined = true; // Memorize
                    return false;
                }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0)
    {
        if (!isEnabled())
        {
            return false;
        }
        return super.onTouchEvent(arg0);
    }
}