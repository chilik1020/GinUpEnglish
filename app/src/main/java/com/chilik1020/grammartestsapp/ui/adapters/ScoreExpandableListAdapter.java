package com.chilik1020.grammartestsapp.ui.adapters;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.chilik1020.grammartestsapp.R;
import com.chilik1020.grammartestsapp.data.model.Score;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreExpandableListAdapter extends BaseExpandableListAdapter {
    private List<List<Score>> mGroupsChild;
    private List<List<Score>> mGroupsParent;
    private Map<Integer, String> chapters;
    private Map<Integer, String> lessonTests;
    private Context mContext;

    public ScoreExpandableListAdapter(Context mContext) {
        this.mGroupsChild = new ArrayList<>();
        this.mGroupsParent = new ArrayList<>();
        this.chapters = new HashMap<>();
        this.lessonTests = new HashMap<>();
        this.mContext = mContext;
    }

    public void setmGroupsChild(List<List<Score>> mGroupsChild) {
        this.mGroupsChild = mGroupsChild;
        notifyDataSetChanged();
    }

    public void setmGroupsParent(List<List<Score>> mGroupsParent) {
        this.mGroupsParent = mGroupsParent;
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return mGroupsChild.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mGroupsChild.get(groupPosition).size();
    }

    public Map<Integer, String> getChapters() {
        return chapters;
    }

    public void setChapters(Map<Integer, String> chapters) {
        this.chapters = chapters;
        notifyDataSetChanged();
    }

    public Map<Integer, String> getLessonTests() {
        return lessonTests;
    }

    public void setLessonTests(Map<Integer, String> lessonTests) {
        this.lessonTests = lessonTests;
        notifyDataSetChanged();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroupsChild.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mGroupsChild.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, final boolean isExpanded, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.card_score_group, null);
        }

        convertView.setOnClickListener(v -> {
            ExpandableListView expandableListView = (ExpandableListView) parent;

            if (groupPosition == 0) {
                expandableListView.collapseGroup(groupPosition);
                return;
            }
            if (isExpanded)
                expandableListView.collapseGroup(groupPosition);
            else
                expandableListView.expandGroup(groupPosition);
        });

        TextView textGroupTopic = convertView.findViewById(R.id.tvGroupScoreCardTopic);
//        TextView textGroupAttempts = convertView.findViewById(R.id.tvGroupScoreCardAttempts);
        TextView textGroupResult = convertView.findViewById(R.id.tvGroupScoreCardAverage);
        ImageView ivGroupImage = convertView.findViewById(R.id.ivGroupScoreCardStars);

        textGroupTopic.setGravity(Gravity.CENTER);

        switch (groupPosition) {
            case 0:
                textGroupTopic.setText(R.string.random_questions);
                break;
            case 1:
                textGroupTopic.setText(R.string.chapters);
                break;
            case 2:
                textGroupTopic.setText(R.string.lessons);
                break;
        }

        int result = 0;
        if (!(mGroupsParent.size() < 3)) {
            if(!mGroupsParent.get(groupPosition).isEmpty())
                result = mGroupsParent.get(groupPosition).get(0).getResult();
        }

            textGroupResult.setText(result + " %");

            if (result == 0)
                ivGroupImage.setImageDrawable(mContext.getDrawable(R.drawable.stars_0));
            else if (result <= 30)
                ivGroupImage.setImageDrawable(mContext.getDrawable(R.drawable.stars_1));
            else if (result <= 50)
                ivGroupImage.setImageDrawable(mContext.getDrawable(R.drawable.stars_2));
            else if (result <= 70)
                ivGroupImage.setImageDrawable(mContext.getDrawable(R.drawable.stars_3));
            else if (result <= 99)
                ivGroupImage.setImageDrawable(mContext.getDrawable(R.drawable.stars_4));
            else
                ivGroupImage.setImageDrawable(mContext.getDrawable(R.drawable.stars_5));


        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.card_score_child, null);
        }

        TextView tvChildTopic =  convertView.findViewById(R.id.tvChildScoreCardTopic);
//        TextView tvChildAttempts = convertView.findViewById(R.id.tvChildScoreCardAttempts);
        TextView tvChildResult = convertView.findViewById(R.id.tvChildScoreCardAverage);
        ImageView ivChildImage = convertView.findViewById(R.id.ivChildScoreCardStars);

        Score score = mGroupsChild.get(groupPosition).get(childPosition);

        switch (groupPosition) {
            case 0 : tvChildTopic.setText(R.string.random_questions);
                break;
            case 1 :
                if (chapters.containsKey(score.getChapterId()))
                    tvChildTopic.setText(chapters.get(score.getChapterId()));
                else
                    tvChildTopic.setText(score.getChapterId());
                break;
            case 2 :
                if (lessonTests.containsKey(score.getLessonId()))
                    tvChildTopic.setText(lessonTests.get(score.getLessonId()));
                else
                    tvChildTopic.setText(score.getLessonId());
                break;
        }

        int result = score.getResult();

        tvChildResult.setText(result + " %");

        if (result == 0)
            ivChildImage.setImageDrawable(mContext.getDrawable(R.drawable.stars_0));
        else if (result <= 30)
            ivChildImage.setImageDrawable(mContext.getDrawable(R.drawable.stars_1));
        else if (result <= 50)
            ivChildImage.setImageDrawable(mContext.getDrawable(R.drawable.stars_2));
        else if (result <= 70)
            ivChildImage.setImageDrawable(mContext.getDrawable(R.drawable.stars_3));
        else if (result <= 99)
            ivChildImage.setImageDrawable(mContext.getDrawable(R.drawable.stars_4));
        else
            ivChildImage.setImageDrawable(mContext.getDrawable(R.drawable.stars_5));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
