package com.chilik1020.grammartestsapp.ui.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chilik1020.grammartestsapp.R;
import com.chilik1020.grammartestsapp.data.App;
import com.chilik1020.grammartestsapp.data.model.Score;
import com.chilik1020.grammartestsapp.data.model.Test;
import com.chilik1020.grammartestsapp.ui.MainActivity;
import com.chilik1020.grammartestsapp.ui.listeners.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TestRecyclerViewAdapter extends RecyclerView.Adapter<TestRecyclerViewAdapter.ViewHolder>  {

    private RecyclerViewClickListener mListener;
    private Context mContext;
    private List<Test> tests = new ArrayList<>();
    private List<Score> rates = new ArrayList<>();

    public TestRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setmListener(RecyclerViewClickListener mListener) {
        this.mListener = mListener;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
        notifyDataSetChanged();
    }

    public List<Score> getRates() {
        return rates;
    }

    public void setRates(List<Score> rates) {
        this.rates = rates;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TestRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_card_with_image,parent,false);
        return new ViewHolder(item, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TestRecyclerViewAdapter.ViewHolder holder, int position) {
        // no-op
        holder.tvTopic.setText("Test " + (position + 1));
        Test test = tests.get(position);

        if (!App.getInstance().isIsAllTestPurchased()) {
            if (test.getPrice() != 0) {
                holder.itemView.setClickable(false);
                holder.itemView.setAlpha(0.7f);
                holder.ivBuyTests.setVisibility(View.VISIBLE);
            }
        }
        Score score = null;
        for (Score s: rates) {
            if (s.getTestId() == test.get_id())
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
        return tests.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTopic;
        ImageView ivBuyTests;
        ImageView ivStarRate;

        private RecyclerViewClickListener mListener;
        ViewHolder(View item, RecyclerViewClickListener listener) {
            super(item);
            mListener = listener;
            item.setOnClickListener(this);
            tvTopic = item.findViewById(R.id.tvCardTopic);
            ivBuyTests = item.findViewById(R.id.navUpgrade);
            ivStarRate = item.findViewById(R.id.startsRateVertical);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }

    }
}
