package com.chilik1020.grammartestsapp.ui.fragments.testsfragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.chilik1020.grammartestsapp.R;
import com.chilik1020.grammartestsapp.data.App;
import com.chilik1020.grammartestsapp.data.dao.QuestionDao;
import com.chilik1020.grammartestsapp.data.db.AppGeneralDataDatabase;
import com.chilik1020.grammartestsapp.data.model.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TestsMainFragment extends Fragment implements View.OnClickListener {

    Map<Integer, Integer> questionsStat = new HashMap<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tests, container, false);

        TextView test10Q = rootView.findViewById(R.id.tvTest10q);
        test10Q.setText(R.string.random10test);

        TextView test20Q = rootView.findViewById(R.id.tvTest20q);
        test20Q.setText(R.string.random20test);

        TextView testChQ = rootView.findViewById(R.id.tvTestChQ);
        testChQ.setText(R.string.chapters);

        TextView testTopQ = rootView.findViewById(R.id.tvTestTopQ);
        testTopQ.setText(R.string.lessons);

        addListenersForCardView(rootView);

        return rootView;
    }

    private void addListenersForCardView(View root) {
        View card10q = root.findViewById(R.id.test10Q);
        card10q.setOnClickListener(this);

        View card20q = root.findViewById(R.id.test20Q);
        card20q.setOnClickListener(this);

        View cardChQ = root.findViewById(R.id.testChQ);
        cardChQ.setOnClickListener(this);

        View cardTopQ = root.findViewById(R.id.testTopQ);
        cardTopQ.setOnClickListener(this);

        View cardTestDb = root.findViewById(R.id.testDbGetQ);
        cardTestDb.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        getActivity();

        Fragment frag = null;

        switch (view.getId()) {
            case R.id.test10Q : {
                Bundle bundle = new Bundle();
                bundle.putInt("chapterId", -1);
                bundle.putInt("lessonId", -1);
                bundle.putInt("testId", -1);
                bundle.putInt("number", 10);
                bundle.putInt("type", 0);
                frag = new Test4VarFragment();
                frag.setArguments(bundle);
                break;
            }

            case R.id.test20Q : {
                Bundle bundle = new Bundle();
                bundle.putInt("chapterId", -1);
                bundle.putInt("lessonId", -1);
                bundle.putInt("testId", -1);
                bundle.putInt("number", 20);
                bundle.putInt("type", 0);
                frag = new Test4VarFragment();
                frag.setArguments(bundle);
                break;
            }

            case R.id.testChQ : {
                frag = new ChaptersTestsFragment();
                break;
            }

            case R.id.testTopQ: {
                 frag = new LessonsTestsFragment();
                 break;
            }

            case R.id.testDbGetQ: {
//                testDbGetQ();
                break;
            }
        }
        if (frag != null) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.frame_container, frag)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit();
        }

    }

    private void testDbGetQ(){

        AppGeneralDataDatabase db = App.getInstance().getAppGeneralDataDatabase();
        QuestionDao questionDao = db.questionDao();

        for (int i = 1; i <= 1000; i++) {
            questionDao.getAllQuestions()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(questions -> getQuestions(10, questions));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        int maxId = 0;
        int minId = 0;
        int min = 0;
        int max = 0;

        for (Map.Entry<Integer, Integer> entry : questionsStat.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                maxId = entry.getKey();
            }

            if (entry.getValue() < min | min == 0) {
                min = entry.getValue();
                minId = entry.getKey();
            }
        }

        Log.d("tagz", "MAX_ID = " + maxId + " , MIN_ID = " + minId);
        Log.d("tagz", "MAX = " + max + " , MIN = " + min);
        Log.d("tagz", questionsStat.toString());
    }

    private void getQuestions(int number, List<Question> data) {

//        Log.d("tagz", "number of questions = " + data.size());

        List<Question> randomData = getRandomQuestions(number, data);

//        Log.d("tagz", "number of random questions = " + randomData.size());
    }
    private List<Question> getRandomQuestions(int number, List<Question> data) {
        if (data.size() <= 10)
            return data;

        List<Question> randomData = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            Question q = data.get((int) (Math.random()*data.size()));
            randomData.add(q);
            data.remove(q);

            int id = q.getId();
            if (questionsStat.containsKey(id)) {
                int oldValue = questionsStat.get(id);
                questionsStat.put(id, oldValue+1);
            } else
                questionsStat.put(id,1);

        }
        return randomData;
    }
}
