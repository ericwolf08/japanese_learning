package ericwolf.genkiii.describer_classes;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import ericwolf.genkiii.DetailedKanjiActivity;
import ericwolf.genkiii.R;
import ericwolf.genkiii.helper_classes.Loading;
import ericwolf.genkiii.helper_classes.TupleKanji;


/**
 * Created by Eric on 11.08.2016.
 */
public class KanjiList extends Fragment {

    /***
     * Defines the view of the List aka Header and the data
     */

    private List<String> arrayList;
    private String name;

    public static KanjiList newInstance() {
        Bundle args = new Bundle();
        KanjiList fragment = new KanjiList();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.activity_kanjilist, container, false);
        ListView listView = (ListView) mainView.findViewById(R.id.custom_list_kanji);
        TextView head = (TextView) mainView.findViewById(R.id.head);

        head.setText("Kanjis");


        final ArrayList<KanjiListEntry> entries = getListData();
        /* listView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.voclist_item, arrayList)); */

        listView.setAdapter(new CustomKanjiListAdapter(getActivity(), entries));

        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(android.widget.AdapterView<?> parent,
                                    View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DetailedKanjiActivity.class);
                Bundle extras = new Bundle();
                //Log.d("intent", entries.get(position).getKanji());
                extras.putString("EXTRA_KANJI", entries.get(position).getKanji());
                extras.putString("EXTRA_MEANING", entries.get(position).getMeaning());
                String yomi = "";
                if(entries.get(position).getOnyomi().trim().length() >= 1){
                    yomi += "On-yomi: " + entries.get(position).getOnyomi() + " ";
                }
                if(entries.get(position).getKunyomi().trim().length() >= 1){
                    yomi += "Kun-yomi: " + entries.get(position).getKunyomi();
                }
                //Log.d("intent", yomi);
                extras.putString("EXTRA_YOMI", yomi);
                Log.d("examples", entries.get(position).getExamples());
                extras.putString("EXTRA_EX", entries.get(position).getExamples());
                extras.putString("EXTRA_DESC", entries.get(position).getDescr());
                //intent.putExtra(EXTRA_KANJI, entries.get(position).getKanji());

                intent.putExtras(extras);
                startActivity(intent);
                //Toast.makeText(getActivity().getApplicationContext(), entries.get(position).getKanji(), Toast.LENGTH_LONG).show();

            }
        });


        return mainView;
    }

    private ArrayList getListData() {

        ArrayList<KanjiListEntry> results = new ArrayList<KanjiListEntry>();
        AssetManager am = getActivity().getAssets();
        Loading dict_kanji = new Loading("kanjis");
        LinkedHashMap<String, TupleKanji<String,String,String,String,String>> dictionaryList = (LinkedHashMap<String, TupleKanji<String, String, String, String , String>>) dict_kanji.getKanji(am);

        arrayList = new ArrayList<String>();

        for (LinkedHashMap.Entry<String, TupleKanji<String,String,String,String,String>> entry : dictionaryList.entrySet()) {
            KanjiListEntry entry1 = new KanjiListEntry();
            //Log.d("kanji", entry.getKey().toString());
            entry1.setKanji(entry.getKey().toString());
            TupleKanji<String,String,String,String,String> tuple = entry.getValue();
            entry1.setOnyomi(tuple.on);
            entry1.setKunyomi(tuple.kun);
            entry1.setMeaning(tuple.meaning);
            entry1.setExamples(tuple.examples);
            entry1.setDescr(tuple.description);
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
