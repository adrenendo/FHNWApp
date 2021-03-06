package com.example.admin.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.LoginFilter;
import android.util.Log;
import android.util.Xml;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.admin.fhnwapp.CustomAdapter;
import com.example.admin.fhnwapp.FAQEntry;
import com.example.admin.fhnwapp.MainActivity;
import com.example.admin.fhnwapp.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FAQFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FAQFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FAQFragment extends Fragment {

    private static final String TAG = "FAQFragment";


    private OnFragmentInteractionListener mListener;

    public FAQFragment() {
        // Required empty public constructor
    }

    public static FAQFragment newInstance() {
        FAQFragment fragment = new FAQFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    private List<FAQEntry> itemsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //List<FAQEntry> itemsList;


        myFragementView = inflater.inflate(R.layout.fragment_faq, container, false);
        return myFragementView;
    }


    private View myFragementView;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ListView lvFAQ = (ListView) myFragementView.findViewById(R.id.lvFAQ);
        try {
//          InputStream is = getResources().getAssets().open("yourfilename.xml");
            Log.i(TAG, "onCreateTry: ");

            InputStream is = getActivity().getAssets().open("faq.xml");
            itemsList = parse(is);

        } catch (Exception e) {
            Log.i(TAG, "onCreateException: " + e.toString());
        }

        lvFAQ.setAdapter(new CustomAdapter(getActivity(), itemsList));
        lvFAQ.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemClick: ");

                FAQEntry entry = itemsList.get(position);
                Log.i(TAG, "onItemClick question: " + entry.getQuestion());
                View popupView = getActivity().getLayoutInflater().inflate(R.layout.pop_faq, null);

                PopupWindow popupWindow = new PopupWindow(popupView, (int)(parent.getWidth() * 1),  (int)(parent.getHeight() * 1));
                TextView tv = (TextView) popupView.findViewById(R.id.tvPopupQuestion);
                tv.setText(entry.getQuestion() + "\n\n" + entry.getAnswer());
                popupWindow.setFocusable(true);
                popupWindow.setBackgroundDrawable(new ColorDrawable());

                int location[] = new int[2];
                View anchorView = view ;

                int location2[] = new int[2];

                parent.getLocationOnScreen(location2);

                Log.i(TAG, "onItemClick: " + location[0] + " location y:" + location[1] + " height:" + anchorView.getHeight());

                // Using location, the PopupWindow will be displayed right under anchorView
                popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY,
                        location2[0], location2[1]);

            }
        });

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            //throw new RuntimeException(context.toString()
            //        + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    private static final String ns = null;

    public List parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFeed(parser);
        } finally {
            in.close();
        }
    }

    private List readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List entries = new ArrayList();

        parser.require(XmlPullParser.START_TAG, ns, "feed");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("entry")) {
                entries.add(readEntry(parser));
            } else {
                skip(parser);
            }
        }
        return entries;
    }

    private FAQEntry readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "entry");
        String title = null;
        String summary = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("question")) {
                title = readQuestion(parser);
            } else if (name.equals("answer")) {
                summary = readAnswer(parser);
            } else {
                skip(parser);
            }
        }
        return new FAQEntry(title, summary);
    }

    private String readQuestion(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "question");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "question");
        return title;
    }

    private String readAnswer(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "answer");
        String summary = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "answer");
        return summary;
    }

    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}
