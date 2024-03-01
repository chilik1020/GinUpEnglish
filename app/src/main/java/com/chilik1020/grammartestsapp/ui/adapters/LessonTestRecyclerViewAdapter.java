package com.chilik1020.grammartestsapp.ui.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chilik1020.grammartestsapp.R;
import com.chilik1020.grammartestsapp.data.model.LessonTest;
import com.chilik1020.grammartestsapp.data.model.Score;
import com.chilik1020.grammartestsapp.ui.listeners.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LessonTestRecyclerViewAdapter extends RecyclerView.Adapter<LessonTestRecyclerViewAdapter.ViewHolder> {

    private RecyclerViewClickListener mListener;
    private Context mContext;
    private List<LessonTest> data = new ArrayList<>();
    private List<Score> rates = new ArrayList<>();

    public LessonTestRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public LessonTestRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_card_with_image,parent,false);
        return new ViewHolder(item, mListener);
    }

    public void setmListener(RecyclerViewClickListener mListener) {
        this.mListener = mListener;
    }

    public List<LessonTest> getData() {
        return data;
    }

    public void setData(List<LessonTest> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public List<Score> getRates() {
        return rates;
    }

    public void setRates(List<Score> rates) {
        this.rates = rates;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // no-op
        holder.tvTopic.setText(data.get(position).getTopic());

        Score score = null;
        for (Score s: rates) {
            if (s.getLessonId() == data.get(position).getId())
                score = s;
        }
        if (score != null) {
            int result = score.getResult();

            if (result == 0)
                holder.ivStarRate.setImageDrawable(mContext.getDrawable(R.drawable.star_0_vert));
            else if (result <= 30)
                holder.ivStarRate.setImageDrawable(mContext.getDrawable(R.drawable.star_1_vert));
            else if (result <= 50)
                holder.ivStarRate.setImageDrawable(mContext.getDrawable(R.drawable.star_2_vert));
            else if (result <= 70)
                holder.ivStarRate.setImageDrawable(mContext.getDrawable(R.drawable.star_3_vert));
            else if (result <= 99)
                holder.ivStarRate.setImageDrawable(mContext.getDrawable(R.drawable.star_4_vert));
            else
                holder.ivStarRate.setImageDrawable(mContext.getDrawable(R.drawable.star_5_vert));
        } else {
            holder.ivStarRate.setImageDrawable(mContext.getDrawable(R.drawable.star_0_vert));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTopic;
        ImageView ivStarRate;

        private RecyclerViewClickListener mListener;
        ViewHolder(View item, RecyclerViewClickListener listener) {
            super(item);
            mListener = listener;
            item.setOnClickListener(this);
            tvTopic = item.findViewById(R.id.tvCardTopic);
            ivStarRate = item.findViewById(R.id.startsRateVertical);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }

    }
}