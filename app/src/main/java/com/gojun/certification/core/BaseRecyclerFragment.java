package com.gojun.certification.core;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
public abstract class BaseRecyclerFragment<T> extends BaseFragment implements OnListItemPartClickListener,PullToRefreshBase.OnRefreshListener2{

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
        return 0;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView=inflater.inflate(getLayoutId(),container,false);
        return mainView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
        mRecyclerView=$(R.id.recycler_view);
        mRecyclerView.getRefreshableView().setLayoutManager(initLayoutManager());

        mRecyclerView.setOnRefreshListener(this);
        mAdapter=initAdapter();
        if(mAdapter!=null){
            mAdapter.setItemClick(this);
            mRecyclerView.getRefreshableView().setAdapter(mAdapter);
        }
    }

    public BaseRecyclerFragment setDividerShow(){
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
    public BaseRecyclerFragment setSpaceShow(int spaceDp,int lineCount){
        getRecyclerView().getRefreshableView().addItemDecoration(new RecyclerSpaceDivider(ViewUtil.dip2px(mContext,spaceDp),lineCount));
        return  this;
    }
    public BaseRecyclerFragment setAutoLoadMore(){
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
