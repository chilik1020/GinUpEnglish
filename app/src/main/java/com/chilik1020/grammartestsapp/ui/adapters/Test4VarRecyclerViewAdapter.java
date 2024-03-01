package com.chilik1020.grammartestsapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chilik1020.grammartestsapp.R;
import com.chilik1020.grammartestsapp.data.model.Question;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Test4VarRecyclerViewAdapter extends RecyclerView.Adapter<Test4VarRecyclerViewAdapter.ViewHolder> {

    private List<Question> questions = new ArrayList<>();

    public Test4VarRecyclerViewAdapter() {}

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_question4var, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Question q = questions.get(position);

        holder.radioGroup.setTag(position);

        holder.question.setText(q.getQuestion());
        holder.rbVar0.setText(q.getAnswer0());
        holder.rbVar1.setText(q.getAnswer1());
        holder.rbVar2.setText(q.getAnswer2());
        holder.rbVar3.setText(q.getAnswer3());

        holder.radioGroup.check(q.getCheckedId());

        holder.radioGroup.setOnCheckedChangeListener((rg, checkedId) -> {
            int pos = (int) rg.getTag();
            questions.get(pos).setCheckedId(checkedId);
            switch (checkedId) {
                case R.id.rbVar0:
                    questions.get(pos).setYourChoose(0);
                    break;
                case R.id.rbVar1:
                    questions.get(pos).setYourChoose(1);
                    break;
                case R.id.rbVar2:
                    questions.get(pos).setYourChoose(2);
                    break;
                case R.id.rbVar3:
                    questions.get(pos).setYourChoose(3);
                    break;
                default:
                    questions.get(pos).setYourChoose(-1);
                    break;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (questions == null) {
            return 0;
        } else {
            return questions.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView question;
        RadioGroup radioGroup;
        RadioButton rbVar0;
        RadioButton rbVar1;
        RadioButton rbVar2;
        RadioButton rbVar3;

        ViewHolder(View item) {
            super(item);
            question = item.findViewById(R.id.tvQuest);
            radioGroup = item.findViewById(R.id.rgVariants);
            rbVar0 = item.findViewById(R.id.rbVar0);
            rbVar1 = item.findViewById(R.id.rbVar1);
            rbVar2 = item.findViewById(R.id.rbVar2);
            rbVar3 = item.findViewById(R.id.rbVar3);
            rbVar0.setActivated(false);
        }
    }
}
