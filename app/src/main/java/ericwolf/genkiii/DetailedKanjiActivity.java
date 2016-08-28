package ericwolf.genkiii;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import static ericwolf.genkiii.R.layout.activity_detailedkanji;

/**
 * Created by Eric on 12.08.2016.
 */
public class DetailedKanjiActivity extends AppCompatActivity {

    String id = "";
    String kanji = "";
    String meaning = "";
    String yomi = "";
    String examples = "";
    String description = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_detailedkanji);

        Intent intent = getIntent();
        Bundle extras = getIntent().getExtras();
        kanji = extras.getString("EXTRA_KANJI");
        meaning = extras.getString("EXTRA_MEANING");
        yomi = extras.getString("EXTRA_YOMI");
        TextView viewKanji = (TextView) findViewById(R.id.kanji);
        TextView viewMeaning = (TextView) findViewById(R.id.meaning);
        TextView viewYomi = (TextView) findViewById(R.id.yomi);
        viewKanji.setText(kanji);
        viewMeaning.setText(meaning);
        viewYomi.setText(yomi);
        TextView viewEx = (TextView) findViewById(R.id.examples);
        TextView viewDesc = (TextView) findViewById(R.id.description);
        examples = extras.getString("EXTRA_EX");
        description = extras.getString("EXTRA_DESC");
        viewEx.setText(examples);
        viewDesc.setText(description);
    }

}
