package ericwolf.genkiii.describer_classes;

/**
 * Created by Eric on 08.08.2016.
 */


import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ericwolf.genkiii.R;
import ericwolf.genkiii.VocListActivity;
import ericwolf.genkiii.helper_classes.Loading;


public class VocList extends Fragment {

    /***
     * Defines the view of the List aka Header and the data
     */

    private List<String> arrayList;
    private String name;

    public static VocList newInstance() {
        Bundle args = new Bundle();
        VocList fragment = new VocList();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.activity_voclist, container, false);
        ListView listView = (ListView) mainView.findViewById(R.id.custom_list);
        TextView head = (TextView) mainView.findViewById(R.id.head);
        Intent intent = getActivity().getIntent();
        name = intent.getStringExtra(VocListActivity.EXTRA_MESSAGE);
        switch (name){
            case "all":
                head.setText("All Vocabulary");
                break;
            case "adjectives_utf8":
                head.setText("Adjectives");
                break;
            case "verbs_utf8":
                head.setText("Verbs");
                break;
            case "nouns_utf8":
                head.setText("Nouns");
                break;
            case "adverbs_utf8":
                head.setText("Adverbs");
                break;


        }

        ArrayList<VocListEntry> entries = getListData();
        //listView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.voclist_item, arrayList));

        listView.setAdapter(new CustomVocListAdapter(getActivity(), entries));

        return mainView;
    }

    private ArrayList getListData() {

        ArrayList<VocListEntry> results = new ArrayList<VocListEntry>();
        Intent intent = getActivity().getIntent();
        name = intent.getStringExtra(VocListActivity.EXTRA_MESSAGE);
        AssetManager am = getActivity().getAssets();
        Loading adj = new Loading(name);
        List<Map<String, String>> dictionaryList = adj.getTxt(am);
        Map<String, String> dict_ej = dictionaryList.get(0);
        arrayList = new ArrayList<String>();

        for (Map.Entry<String, String> entry : dict_ej.entrySet()) {
            VocListEntry entry1 = new VocListEntry();
            entry1.setEnglish(entry.getKey().toString());
            entry1.setFiller(" ");
            entry1.setJapanese(entry.getValue().toString());
            results.add(entry1);
            /**
            String text = "";
            text += entry.getKey().toString();
            text += " - ";
            text += entry.getValue().toString();
            arrayList.add(text);
             */
        }
        return results;

    }

}
