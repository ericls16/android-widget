package com.liusong.widget.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liusong.widget.R;

import java.util.List;

/**
 * @description 固定item布局（只包含一个TextView），item有单击和长按监听.
 * Created by liusong on 2016/10/10.
 */
public class RvSingleTextAdapter extends RecyclerView.Adapter<RvSingleTextAdapter.MyViewHolder> {

    private List<String> data;
    private OnItemClickListener mOnItemClickListener;

    public RvSingleTextAdapter(List<String> data) {
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_single_text, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.mContent.setText(data.get(position));

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.itemView, pos);
                    return true; //这里需要消耗掉，不然就会触发单击事件
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mContent;

        public MyViewHolder(View itemView) {
            super(itemView);
            mContent = (TextView) itemView.findViewById(R.id.content);
        }
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    //item点击监听接口
    public interface OnItemClickListener {
        //单击事件
        void onItemClick(View view, int position);

        //长按事件
        void onItemLongClick(View view, int position);
    }

}
