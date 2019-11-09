package com.chilik1020.grammartestsapp.ui.fragments.grammarfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.chilik1020.grammartestsapp.BuildConfig;
import com.chilik1020.grammartestsapp.R;
import com.chilik1020.grammartestsapp.model.entities.LessonGrammar;
import com.chilik1020.grammartestsapp.utils.AppConstant;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UnitFragment extends Fragment {

    @BindView(R.id.wvGrammar) WebView wvGrammar;
    @BindView(R.id.tvTopicUnitFragment) TextView tvTopic;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_unit, container, false);
        ButterKnife.bind(this, viewRoot);

        Bundle bundle = getArguments();
        LessonGrammar unit = (LessonGrammar) bundle.getSerializable("unit");

        tvTopic.setText(unit.getTopic());
        String grammarPath = unit.getGrammar();

        initWebView();

        wvGrammar.loadUrl(AppConstant.PathToHTML + grammarPath);
        return viewRoot;
    }

    private void initWebView(){
        WebSettings settings = wvGrammar.getSettings();

        settings.setPluginState(WebSettings.PluginState.ON);

        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        settings.setSupportZoom(true);

        wvGrammar.setBackgroundColor(0);
    }
}
