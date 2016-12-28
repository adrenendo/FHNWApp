package com.example.admin.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.fhnwapp.R;

public class LinksFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_links, container, false);

//        R.layout.setText( Html.fromHtml("<a href=\"http://www.google.com\">Google</a>"));
//txtTest. setMovementMethod(LinkMovementMethod.getInstance());
        return rootView;
    }
}
