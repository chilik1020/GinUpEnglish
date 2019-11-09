package com.chilik1020.grammartestsapp.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chilik1020.grammartestsapp.R;
import com.chilik1020.grammartestsapp.model.entities.Question;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Test4VarAnswersRecyclerViewAdapter extends RecyclerView.Adapter<Test4VarAnswersRecyclerViewAdapter.ViewHolder>{

    private Context ctx;
    private List<Question> data;
    private boolean isRightAnswerShowed = false;
    public Test4VarAnswersRecyclerViewAdapter(Context ctx, List<Question> data) {
        this.ctx = ctx;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_question4var_answers, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Question q = data.get(position);
        holder.llAnswerItem.setTag(position);

        holder.tvQuestion.setText(q.getQuestion());
        holder.tvAnswer0.setText(new StringBuilder().append("- ").append(q.getAnswer0()).toString());
        holder.tvAnswer1.setText(new StringBuilder().append("- ").append(q.getAnswer1()).toString());
        holder.tvAnswer2.setText(new StringBuilder().append("- ").append(q.getAnswer2()).toString());
        holder.tvAnswer3.setText(new StringBuilder().append("- ").append(q.getAnswer3()).toString());

        holder.tvAnswer0.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color_1_2));
        holder.tvAnswer1.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color_1_2));
        holder.tvAnswer2.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color_1_2));
        holder.tvAnswer3.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color_1_2));

        holder.llAnswerItem.setBackgroundResource(R.drawable.test_4var_item_bg);

        if (q.getYourChoose() >= 0) {
            if (q.getRightAnswer() != q.getYourChoose())
                holder.llAnswerItem.setBackgroundResource(R.drawable.test_4var_answer_wrong_item_bg);
            else
                holder.llAnswerItem.setBackgroundResource(R.drawable.test_4var_answer_right_item_bg);
        }


        if (q.getYourChoose() >= 0) {

            if (q.getRightAnswer() != q.getYourChoose()) {
                switch (q.getYourChoose()) {
                    case 0:
                        holder.tvAnswer0.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color_5_D0));
                        break;

                    case 1:
                        holder.tvAnswer1.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color_5_D0));
                        break;

                    case 2:
                        holder.tvAnswer2.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color_5_D0));
                        break;

                    case 3:
                        holder.tvAnswer3.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color_5_D0));
                        break;

                    default:
                        break;
                }
            } else if (q.getRightAnswer() == q.getYourChoose()){
                switch (q.getYourChoose()) {
                    case 0:
                        holder.tvAnswer0.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color_2));
                        break;

                    case 1:
                        holder.tvAnswer1.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color_2));
                        break;

                    case 2:
                        holder.tvAnswer2.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color_2));
                        break;

                    case 3:
                        holder.tvAnswer3.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color_2));
                        break;

                    default:
                        break;
                }
            }
        }

        if (isRightAnswerShowed) {
            switch (q.getRightAnswer()) {
                case 0:
                    holder.tvAnswer0.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color_2));
                    break;

                case 1:
                    holder.tvAnswer1.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color_2));
                    break;

                case 2:
                    holder.tvAnswer2.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color_2));
                    break;

                case 3:
                    holder.tvAnswer3.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color_2));
                    break;

                default:
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        if (data == null) {
            return 0;
        } else {
            return data.size();
        }
    }

    public void setRightAnswerShowed(boolean rightAnswerShowed) {
        isRightAnswerShowed = rightAnswerShowed;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView (R.id.llAnswerItem) LinearLayout llAnswerItem;
        @BindView (R.id.tvQuestionAnswersFrag) TextView tvQuestion;
        @BindView (R.id.tvVar0AnswersFrag) TextView tvAnswer0;
        @BindView (R.id.tvVar1AnswersFrag) TextView tvAnswer1;
        @BindView (R.id.tvVar2AnswersFrag) TextView tvAnswer2;
        @BindView (R.id.tvVar3AnswersFrag) TextView tvAnswer3;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
