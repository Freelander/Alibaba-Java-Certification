package com.gojun.certification.core;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gojun.certification.R;
import com.gojun.certification.utils.ViewUtil;
import com.gojun.certification.widget.pulltorefresh.PullToRefreshBase;
import com.gojun.certification.widget.pulltorefresh.PullToRefreshRecyclerView;
import com.gojun.certification.widget.recycler.BaseAdapter;
import com.gojun.certification.widget.recycler.OnListItemPartClickListener;
import com.gojun.certification.widget.recycler.RecyclerDivider;
import com.gojun.certification.widget.recycler.RecyclerSpaceDivider;

/**
*   List、Grid
*   @author Porster
*   @time   16/11/10 10:43
**/
public abstract class BaseRecyclerActivity<T> extends BaseActivity implements OnListItemPartClickListener,PullToRefreshBase.OnRefreshListener2{

    private boolean mIsAutoLoadMore;

    /**是否存在下一页*/
    public boolean mHasNext;

    public int mPage=1;
    public int mPageCount=20;

    private BaseAdapter<T> mAdapter;

    private PullToRefreshRecyclerView mRecyclerView;

    public BaseAdapter<T> getAdapter() {
        return mAdapter;
    }

    public PullToRefreshRecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    /**布局ID*/
    public int getLayoutId(){
        return R.layout.activity_recyclerview;
    }
    /**设置布局样式*/
    protected  RecyclerView.LayoutManager initLayoutManager(){
        return new LinearLayoutManager(mContext);
    }

    /**设置你的UI*/
    protected abstract void initUI();
    /**是否自动进行刷新获取数据*/
    protected abstract boolean autoRefresh();
    /**设置你自己的适配器*/
    protected abstract BaseAdapter<T> initAdapter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initLayout();
        initUI();
        if(autoRefresh()){
            mRecyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mRecyclerView.setRefreshing(true);
                }
            },500);
        }

    }

    private void initLayout() {
        mRecyclerView=$(R.id.recyclerview);
        mRecyclerView.getRefreshableView().setLayoutManager(initLayoutManager());

        mRecyclerView.setOnRefreshListener(this);
        mAdapter=initAdapter();
        if(mAdapter!=null){
            mAdapter.setItemClick(this);
            mRecyclerView.getRefreshableView().setAdapter(mAdapter);
        }
    }

    public BaseRecyclerActivity setDividerShow(){
        RecyclerDivider mDivider=new RecyclerDivider();
        mDivider.setColor(ContextCompat.getColor(mContext,R.color.gray_stroke)).setSize(1);
        mRecyclerView.getRefreshableView().addItemDecoration(mDivider);
        return  this;
    }

    /**
     * 设置Grid布局的间距
     * @param spaceDp       10dp
     * @param lineCount     一行个数
     * @return
     */
    public BaseRecyclerActivity setSpaceShow(int spaceDp, int lineCount){
        getRecyclerView().getRefreshableView().addItemDecoration(new RecyclerSpaceDivider(ViewUtil.dip2px(mContext,spaceDp),lineCount));
        return  this;
    }
    public BaseRecyclerActivity setAutoLoadMore(){
        this.mIsAutoLoadMore=true;
        mRecyclerView.getRefreshableView().addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!ViewCompat.canScrollVertically(recyclerView,1)&&mHasNext){
                    mRecyclerView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
                    mRecyclerView.post(new Runnable() {
                        @Override
                        public void run() {
                            mRecyclerView.setRefreshing(true);
                        }
                    });
                }
            }
        });
        return this;
    }
    public void loadMoreDone(){
        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.onRefreshComplete();
            }
        });
    }

}
