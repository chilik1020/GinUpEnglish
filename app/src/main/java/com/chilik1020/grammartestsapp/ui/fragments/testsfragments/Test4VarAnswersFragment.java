package com.chilik1020.grammartestsapp.ui.fragments.testsfragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.chilik1020.grammartestsapp.R;
import com.chilik1020.grammartestsapp.data.model.Question;
import com.chilik1020.grammartestsapp.ui.adapters.Test4VarAnswersRecyclerViewAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Test4VarAnswersFragment extends Fragment implements View.OnClickListener {
    Test4VarAnswersRecyclerViewAdapter adapter;
    Button btnShowrightAnswers;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_test4_var_answers,container,false);
        TextView tvTopic = rootView.findViewById(R.id.tvTest4VarAnswersTopic);
        btnShowrightAnswers = rootView.findViewById(R.id.btnShowRightAnswer);
        btnShowrightAnswers.setOnClickListener(this);

        List<Question> list = getArguments().getParcelableArrayList("data");
        String topic = getArguments().getString("topic");
        tvTopic.setText(topic);




        adapter = new Test4VarAnswersRecyclerViewAdapter(list);
        adapter.setCtx(getActivity());
        RecyclerView mRV = rootView.findViewById(R.id.fragment_answers_my_recycler_view);
        RecyclerView.LayoutManager mRVManager = new LinearLayoutManager(getActivity());
        mRV.setLayoutManager(mRVManager);
        mRV.setAdapter(adapter);
        mRV.setHasFixedSize(true);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        adapter.setRightAnswerShowed(true);
        adapter.notifyDataSetChanged();
    }
}
