package com.chilik1020.grammartestsapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chilik1020.grammartestsapp.R;
import com.chilik1020.grammartestsapp.data.model.Chapter;
import com.chilik1020.grammartestsapp.ui.listeners.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChapterRecyclerViewAdapter extends RecyclerView.Adapter<ChapterRecyclerViewAdapter.ViewHolder> {

    private RecyclerViewClickListener mListener;
    private List<Chapter> data = new ArrayList<>();

    public ChapterRecyclerViewAdapter() {
    }

    public void setData(List<Chapter> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public List<Chapter> getData() {
        return data;
    }

    public void setmListener(RecyclerViewClickListener mListener) {
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public ChapterRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_card,parent,false);
        return new ViewHolder(item, mListener);
    }


    @Override
    public void onBindViewHolder(@NonNull ChapterRecyclerViewAdapter.ViewHolder holder, int position) {
        // no-op
        holder.tvChapter.setText(data.get(position).getChapter());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvChapter;

        private RecyclerViewClickListener mListener;
        ViewHolder(View item, RecyclerViewClickListener listener) {
            super(item);
            mListener = listener;
            item.setOnClickListener(this);
            tvChapter = item.findViewById(R.id.tvCardTopic);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }

    }
}
