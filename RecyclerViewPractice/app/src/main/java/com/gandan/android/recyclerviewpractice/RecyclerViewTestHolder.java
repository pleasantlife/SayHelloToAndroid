package com.gandan.android.recyclerviewpractice;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by XPS on 2018-01-26.
 */

public class RecyclerViewTestHolder extends RecyclerView.ViewHolder {

    public TextView textItem;

    public RecyclerViewTestHolder(View itemView) {
        super(itemView);
        textItem = itemView.findViewById(R.id.textItem);
    }
}
