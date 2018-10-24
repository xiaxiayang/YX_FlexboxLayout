package com.example.yx.yx_flexboxlayoutdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yx.yx_flexboxlayoutdemo.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pc on 2018/10/9.
 */

public class LabelAdapter extends RecyclerView.Adapter<LabelAdapter.FlexViewHolder> {


    private List<String> lables;
    private Context mContext;
    private OnItemClickListener listener;

    public LabelAdapter(List<String> lables, Context mContext) {
        this.lables = lables;
        this.mContext = mContext;
    }

    public void setOnItemClickListener( OnItemClickListener listener){
        this.listener = listener;
    }
    public  interface  OnItemClickListener{
        void onItemClick(View view,int position);
    }
    @Override
    public FlexViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_label, parent, false);

        return new FlexViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FlexViewHolder holder, final int position) {
        holder.itemTvLabel.setText(lables.get(position));

        holder.itemTvLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener !=null){
                    listener.onItemClick(holder.itemTvLabel,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return lables.size();
    }

    static class FlexViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_tv_label)
        TextView itemTvLabel;
        public FlexViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
}
