package ericwolf.genkiii.helper_classes;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Eric on 04.08.2016.
 */
public class Loading extends AppCompatActivity {

    String name;
    Map<String, String> dict_ej = new HashMap<String, String>();
    Map<String, String> dict_je = new HashMap<String, String>();
    LinkedHashMap<String, TupleKanji<String,String,String,String,String>> dict_kanji = new LinkedHashMap<String, TupleKanji<String,String,String,String,String>>();
    List<Map<String, String>> dictionaryList = new ArrayList<Map<String, String>>();
    public List<String> arts = new ArrayList<>();


    public Loading(String name) {
        this.name = name;
        arts.add("adverbs_utf8");
        arts.add("verbs_utf8");
        arts.add("nouns_utf8");
        arts.add("adjectives_utf8");
    }

    public Map<String, TupleKanji<String,String,String,String,String>> getKanji(AssetManager am) {

        try {
            InputStream in = am.open(this.name + ".txt");
            //BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line;
            //adjectives_ej.put("bright", "てすと");

            while ((line = br.readLine()) != null) {
                boolean startsWith = line.indexOf('#') == 0;
                boolean empty = line.equals("");
                boolean contains = line.contains("##");
                if (!startsWith && !empty && !contains) {
                    String[] parts = line.split("_");
                    String kanji = parts[0].trim();
                    //Log.d("kanji_first", kanji);
                    String onyomi = parts[1].trim();
                    String kunyomi = parts[2].trim();
                    String meaning = parts[3].trim();
                    String examples = parts[4].trim();
                    String description = parts[5].trim();
                    //Log.d("fourth", parts[4].trim());
                    TupleKanji tuple = new TupleKanji(onyomi,kunyomi,meaning,examples,description);
                    dict_kanji.put(kanji, tuple);

                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return dict_kanji;
    }


    public List<Map<String, String>> getTxt(AssetManager am) {


            if (!this.name.equals("all")) {
                try {
                    InputStream in = am.open(this.name + ".txt");
                    //BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                    BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    //adjectives_ej.put("bright", "てすと");

                    while ((line = br.readLine()) != null) {
                        boolean startsWith = line.indexOf('#') == 0;
                        boolean empty = line.equals("");
                        boolean contains = line.contains("##");
                        if (!startsWith && !empty && !contains) {
                            String[] parts = line.split("_");
                            String japaneseString = parts[1].trim();
                            String englishString = parts[2].trim();

                            //Log.d("voc", japaneseString + " : " + englishString);

                            dict_ej.put(englishString, japaneseString);
                            dict_je.put(japaneseString, englishString);
                        }

                    }

                    Map<String, String> sorted_ej = new TreeMap<String, String>(dict_ej);

                    dictionaryList.add(sorted_ej);
                    dictionaryList.add(dict_je);
                /*
                for ( String key : adjectives_ej.keySet() ) {
                    Log.d("keys", key);
                }
                */


                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                for (int i = 0; i < arts.size(); i++) {
                    String name_temp = arts.get(i);
                    try {
                        InputStream in = am.open(name_temp + ".txt");
                        //BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                        BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                        StringBuilder sb = new StringBuilder();
                        String line;
                        //adjectives_ej.put("bright", "てすと");

                        while ((line = br.readLine()) != null) {
                            boolean startsWith = line.indexOf('#') == 0;
                            boolean empty = line.equals("");
                            boolean contains = line.contains("##");
                            if (!startsWith && !empty && !contains) {
                                String[] parts = line.split("_");
                                String japaneseString = parts[1].trim();
                                String englishString = parts[2].trim();

                                //Log.d("voc", japaneseString + " : " + englishString);

                                dict_ej.put(englishString, japaneseString);
                                dict_je.put(japaneseString, englishString);
                            }

                        }



                /*
                for ( String key : adjectives_ej.keySet() ) {
                    Log.d("keys", key);
                }
                */


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                Map<String, String> sorted_ej = new TreeMap<String, String>(dict_ej);

                dictionaryList.add(sorted_ej);
                dictionaryList.add(dict_je);
            }




        return dictionaryList;
    }



}




