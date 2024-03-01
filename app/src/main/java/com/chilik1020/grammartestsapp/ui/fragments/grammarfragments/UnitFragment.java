package com.chilik1020.grammartestsapp.ui.fragments.grammarfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.chilik1020.grammartestsapp.R;
import com.chilik1020.grammartestsapp.data.model.LessonGrammar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class UnitFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_unit, container, false);

        TextView tvTopic = viewRoot.findViewById(R.id.tvTopic);

        Bundle bundle = getArguments();
        LessonGrammar unit = (LessonGrammar) bundle.getSerializable("unit");

        tvTopic.setText(unit.getTopic());
        String grammarPath = unit.getGrammar();

        WebView wvGrammar = viewRoot.findViewById(R.id.wvGrammar);

        WebSettings settings = wvGrammar.getSettings();

        settings.setPluginState(WebSettings.PluginState.ON);

        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        settings.setSupportZoom(true);

        wvGrammar.setBackgroundColor(0);
//        wvGrammar.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        wvGrammar.loadUrl("file:///android_asset/html/" + grammarPath);
        return viewRoot;
    }
}
