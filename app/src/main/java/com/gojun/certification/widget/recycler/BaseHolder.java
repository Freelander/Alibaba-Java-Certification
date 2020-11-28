package com.gojun.certification.widget.recycler;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

/**
*
*   @author Porster
*   @time   16/11/3 17:07
**/
public class BaseHolder extends RecyclerView.ViewHolder{
    private View mView;

    public BaseHolder(View itemView) {
        super(itemView);
        mView=itemView;
    }


    public View getView() {
        return mView;
    }
}