package com.example.admin.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.fhnwapp.R;

public class LinksFragment extends Fragment {
    private View myFragmentView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myFragmentView = inflater.inflate(R.layout.fragment_links, container, false);

        TextView textView = (TextView) myFragmentView.findViewById(R.id.tvLinks);

        textView.setText( Html.fromHtml("<a href=\"http://next-career.ch/en\" >Jobs/Internships</a><br><br>" +
                                        "<a href=\"https://www.swissuniversities.ch/en/services/e-resources-cas/resources-by-document-type/databases/\">Library book search</a><br><br>" +
                "                        <a href=\"http://www.fhnw.ch/bachelor-und-master/rechtserlasse/HSW_Study%20and%20Examination%20Regulations%20MSc%20BIS_IM.PDF\">Study regulations</a>"));
        textView.setLinkTextColor(getResources().getColor(R.color.colorForeground));

        textView.setMovementMethod(LinkMovementMethod.getInstance());

        return myFragmentView;
    }
}
