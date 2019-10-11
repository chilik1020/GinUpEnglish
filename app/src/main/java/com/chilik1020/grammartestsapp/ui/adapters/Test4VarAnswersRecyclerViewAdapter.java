package com.chilik1020.grammartestsapp.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chilik1020.grammartestsapp.R;
import com.chilik1020.grammartestsapp.data.App;
import com.chilik1020.grammartestsapp.data.model.Question;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Test4VarAnswersRecyclerViewAdapter extends RecyclerView.Adapter<Test4VarAnswersRecyclerViewAdapter.ViewHolder>{

    private List<Question> data;
    private boolean isRightAnswerShowed = false;
    private Context ctx;
    public Test4VarAnswersRecyclerViewAdapter(List<Question> data) {
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

        holder.tvAnswer0.setBackgroundColor(App.getInstance().getResources().getColor(R.color.color_1_2));
        holder.tvAnswer1.setBackgroundColor(App.getInstance().getResources().getColor(R.color.color_1_2));
        holder.tvAnswer2.setBackgroundColor(App.getInstance().getResources().getColor(R.color.color_1_2));
        holder.tvAnswer3.setBackgroundColor(App.getInstance().getResources().getColor(R.color.color_1_2));

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
                        holder.tvAnswer0.setBackgroundColor(App.getInstance().getResources().getColor(R.color.color_5_D0));
                        break;

                    case 1:
                        holder.tvAnswer1.setBackgroundColor(App.getInstance().getResources().getColor(R.color.color_5_D0));
                        break;

                    case 2:
                        holder.tvAnswer2.setBackgroundColor(App.getInstance().getResources().getColor(R.color.color_5_D0));
                        break;

                    case 3:
                        holder.tvAnswer3.setBackgroundColor(App.getInstance().getResources().getColor(R.color.color_5_D0));
                        break;

                    default:
                        break;
                }
            } else if (q.getRightAnswer() == q.getYourChoose()){
                switch (q.getYourChoose()) {
                    case 0:
                        holder.tvAnswer0.setBackgroundColor(App.getInstance().getResources().getColor(R.color.color_2));
                        break;

                    case 1:
                        holder.tvAnswer1.setBackgroundColor(App.getInstance().getResources().getColor(R.color.color_2));
                        break;

                    case 2:
                        holder.tvAnswer2.setBackgroundColor(App.getInstance().getResources().getColor(R.color.color_2));
                        break;

                    case 3:
                        holder.tvAnswer3.setBackgroundColor(App.getInstance().getResources().getColor(R.color.color_2));
                        break;

                    default:
                        break;
                }
            }
        }

        if (isRightAnswerShowed) {
            switch (q.getRightAnswer()) {
                case 0:
                    holder.tvAnswer0.setBackgroundColor(App.getInstance().getResources().getColor(R.color.color_2));
                    break;

                case 1:
                    holder.tvAnswer1.setBackgroundColor(App.getInstance().getResources().getColor(R.color.color_2));
                    break;

                case 2:
                    holder.tvAnswer2.setBackgroundColor(App.getInstance().getResources().getColor(R.color.color_2));
                    break;

                case 3:
                    holder.tvAnswer3.setBackgroundColor(App.getInstance().getResources().getColor(R.color.color_2));
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

    public void setCtx(Context ctx) {
        this.ctx = ctx;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout llAnswerItem;
        TextView tvQuestion;
        TextView tvAnswer0;
        TextView tvAnswer1;
        TextView tvAnswer2;
        TextView tvAnswer3;


        ViewHolder(View itemView) {
            super(itemView);
            llAnswerItem = itemView.findViewById(R.id.llAnswerItem);
            tvQuestion = itemView.findViewById(R.id.tvQuestionAnswersFrag);
            tvAnswer0 = itemView.findViewById(R.id.tvVar0AnswersFrag);
            tvAnswer1 = itemView.findViewById(R.id.tvVar1AnswersFrag);
            tvAnswer2 = itemView.findViewById(R.id.tvVar2AnswersFrag);
            tvAnswer3 = itemView.findViewById(R.id.tvVar3AnswersFrag);
        }
    }
}
