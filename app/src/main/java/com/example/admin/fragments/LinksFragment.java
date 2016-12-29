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

        textView.setText( Html.fromHtml("<ul><li>Jobs/Internships: <a href=\"http://next-career.ch/en\" >next-career.ch</a></li>" +
                                        "<li>Library book search: <a href=\"https://www.swissuniversities.ch/en/services/e-resources-cas/resources-by-document-type/databases/\">Database</a></li>" +
                "                        <li>Study regulations: <a href=\"http://www.fhnw.ch/bachelor-und-master/rechtserlasse/HSW_Study%20and%20Examination%20Regulations%20MSc%20BIS_IM.PDF\">PDF File</a></li> </ul>"));
        textView.setLinkTextColor(getResources().getColor(R.color.colorForeground));
        //"<ul><li>Jobs/Internships: <a href=\"http://www.google.com\" >Google</a></li><li>Library book search: <a href=\"http://www.chip.de\">CHippppiiii</a></li></ul>");
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        return myFragmentView;
    }
}
