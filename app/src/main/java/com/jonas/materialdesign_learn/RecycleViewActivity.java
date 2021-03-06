package com.jonas.materialdesign_learn;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;
import com.jonas.materialdesign_learn.fragments.MainPagerAdapter;
import com.jonas.yun_library.tablayout.JPagerSlidingTabStrip;
import com.jonas.yun_library.tablayout.JTabStyleDelegate;

import static com.jonas.yun_library.tablayout.JTabStyleBuilder.STYLE_DEFAULT;
import static com.jonas.yun_library.tablayout.JTabStyleBuilder.STYLE_DOTS;
import static com.jonas.yun_library.tablayout.JTabStyleBuilder.STYLE_ROUND;

/**
 * <p> int firstIndex = mLayoutManager.findFirstVisibleItemPosition();//返回当前屏幕第一个显示的item的postion
 * <p> int firstComIndex = mLayoutManager.findFirstCompletelyVisibleItemPosition();//返回当前屏幕第一个完全显示的item的postion
 * <p> int lastIndex = mLayoutManager.findLastVisibleItemPosition();//返回当前屏幕最后一个显示的item的position
 * <p> int lastComIndex = mLayoutManager.findLastCompletelyVisibleItemPosition();//返回当前屏幕最后一个完全显示的item的posion
 * <p> View view = mLayoutManager.findViewByPosition(int postion);//返回对应postion的itemView
 */
public class RecycleViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("RecycleView");
        setContentView(R.layout.activity_recycle_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setUpViews();

//        DragSwipeCallback callback = new DragSwipeCallback(mAdapter);
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
////        itemTouchHelper.startDrag();
//        itemTouchHelper.attachToRecyclerView(mRcv);

    }

    private void setUpViews() {
        JPagerSlidingTabStrip mTabStripto = (JPagerSlidingTabStrip) findViewById(R.id.jsts);
        JTabStyleDelegate mTabStrip = mTabStripto.getTabStyleDelegate();
        JPagerSlidingTabStrip mTabStripbu = (JPagerSlidingTabStrip) findViewById(R.id.jstsb);
        JTabStyleDelegate mTabStripb = mTabStripbu.getTabStyleDelegate();
        JPagerSlidingTabStrip mDots = (JPagerSlidingTabStrip) findViewById(R.id.jst_dots);
        JTabStyleDelegate mDots_style = mDots.getTabStyleDelegate();
        mDots_style.setJTabStyle(STYLE_DOTS);
        //mTabStrip.setShouldExpand(true);
        mTabStrip.setTextAllCaps(false);
        mTabStrip.setFrameColor(Color.parseColor("#FACDB9"));
        mTabStrip.setTabTextSize(getDimen(R.dimen.tabstrip_textsize));
        //        mTabStrip.setTextColor(Color.parseColor("#FB6522"));
        mTabStrip.setTextColorStateResource(R.drawable.tabstripbg);
        mTabStrip.setJTabStyle(STYLE_ROUND);
        mTabStrip.setDividerColor(Color.parseColor("#FACDB9"));
        mTabStrip.setDividerPadding(0);
        mTabStrip.setUnderlineColor(Color.TRANSPARENT);
        mTabStrip.setIndicatorColor(Color.parseColor("#FACDB9"));
        mTabStrip.setIndicatorHeight(getDimen(R.dimen.sug_event_tabheight));

        mTabStripb.setShouldExpand(true);
        mTabStripb.setNotDrawIcon(false);
        mTabStripb.setTabMode(JPagerSlidingTabStrip.MODE_BUTTOM);
        mTabStripb.setTextColorStateResource(R.drawable.tabstripbg);
        mTabStripb.setJTabStyle(STYLE_DEFAULT);

        ViewPager vp = (ViewPager) findViewById(R.id.md_vp);
        vp.setAdapter(new MainPagerAdapter(this,getSupportFragmentManager()));
        mTabStripto.setViewPager(vp);
        mTabStripbu.setViewPager(vp);
        mDots.setViewPager(vp);

    }

    private int getDimen(int dimen) {
        return (int) getResources().getDimension(dimen);
    }

    public static class wifiReceiver extends BroadcastReceiver {
        NetworkInfo.State wifiState = null;
        NetworkInfo.State mobileState = null;
        public static final String ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            if (ACTION.equals(intent.getAction())) {
                //获取手机的连接服务管理器，这里是连接管理器类
                ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                wifiState = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
                mobileState = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();

                if (wifiState != null && mobileState != null && NetworkInfo.State.CONNECTED != wifiState && NetworkInfo.State.CONNECTED == mobileState) {
                    Toast.makeText(context, "手机网络连接成功！", Toast.LENGTH_SHORT).show();
                } else if (wifiState != null && mobileState != null && NetworkInfo.State.CONNECTED == wifiState && NetworkInfo.State.CONNECTED != mobileState) {
                    Toast.makeText(context, "无线网络连接成功！", Toast.LENGTH_SHORT).show();
                } else if (wifiState != null && mobileState != null && NetworkInfo.State.CONNECTED != wifiState && NetworkInfo.State.CONNECTED != mobileState) {
                    Toast.makeText(context, "手机没有任何网络...", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
