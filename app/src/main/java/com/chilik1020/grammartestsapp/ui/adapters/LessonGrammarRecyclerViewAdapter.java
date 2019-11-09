package com.chilik1020.grammartestsapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chilik1020.grammartestsapp.R;
import com.chilik1020.grammartestsapp.model.entities.LessonGrammar;

import com.chilik1020.grammartestsapp.ui.listeners.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LessonGrammarRecyclerViewAdapter extends RecyclerView.Adapter<LessonGrammarRecyclerViewAdapter.ViewHolder> {

    private RecyclerViewClickListener mListener;
    private List<LessonGrammar> data = new ArrayList<>();

    public LessonGrammarRecyclerViewAdapter() { }

    public void setmListener(RecyclerViewClickListener mListener) {
        this.mListener = mListener;
    }

    public List<LessonGrammar> getData() {
        return data;
    }

    public void setData(List<LessonGrammar> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LessonGrammarRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_card,parent,false);
        return new ViewHolder(item, mListener);
            }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.tvTopic.setText(data.get(position).getTopic());
            }

    @Override
    public int getItemCount() {
            return data.size();
            }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTopic;

        private RecyclerViewClickListener mListener;
        ViewHolder(View item, RecyclerViewClickListener listener) {
            super(item);
            mListener = listener;
            item.setOnClickListener(this);
            tvTopic = item.findViewById(R.id.tvCardTopic);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }

    }
}
