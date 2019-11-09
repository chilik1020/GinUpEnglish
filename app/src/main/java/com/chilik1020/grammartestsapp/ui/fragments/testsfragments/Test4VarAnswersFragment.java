package com.chilik1020.grammartestsapp.ui.fragments.testsfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.chilik1020.grammartestsapp.R;
import com.chilik1020.grammartestsapp.model.entities.Question;
import com.chilik1020.grammartestsapp.ui.adapters.Test4VarAnswersRecyclerViewAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Test4VarAnswersFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.fragment_answers_my_recycler_view) RecyclerView mRV;
    @BindView(R.id.btnShowRightAnswer) Button btnShowRightAnswers;
    @BindView(R.id.tvTest4VarAnswersTopic) TextView tvTopic;

    Test4VarAnswersRecyclerViewAdapter adapter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_test4_var_answers,container,false);
        ButterKnife.bind(this, rootView);

        List<Question> list = getArguments().getParcelableArrayList("data");
        String topic = getArguments().getString("topic");

        tvTopic.setText(topic);

        adapter = new Test4VarAnswersRecyclerViewAdapter(getActivity(),list);

        RecyclerView.LayoutManager mRVManager = new LinearLayoutManager(getActivity());
        mRV.setLayoutManager(mRVManager);
        mRV.setAdapter(adapter);
        mRV.setHasFixedSize(true);

        btnShowRightAnswers.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        adapter.setRightAnswerShowed(true);
        adapter.notifyDataSetChanged();
    }
}
