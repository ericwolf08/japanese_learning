package ericwolf.genkiii;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

/**
 * Created by Eric on 11.08.2016.
 */
public class GrammarEntry extends AppCompatActivity {


    public final static String EXTRA_MESSAGE = "";
    public String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammarentry);


        Intent intent = getIntent();
        name = intent.getStringExtra(Vocabulary.EXTRA_MESSAGE);

        AssetManager am = getAssets();


        try {

            InputStream in = am.open(name);
            //BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            //BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            //StringBuilder sb = new StringBuilder();

            String content = readStream(in);
            String[] parts = content.split("_");

            TextView title = (TextView) findViewById(R.id.title);
            title.setText(parts[0]);

            TextView explanation = (TextView) findViewById(R.id.explanation);
            explanation.setText(parts[1]);

            TextView examples = (TextView) findViewById(R.id.examples);
            examples.setText(parts[3]);

            TextView rulestitle = (TextView) findViewById(R.id.rulestitle);
            rulestitle.setText(parts[4]);

            TextView description = (TextView) findViewById(R.id.description);
            description.setText(parts[5]);

            TextView addition = (TextView) findViewById(R.id.addition);
            addition.setText(parts[6]);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static String readStream(InputStream is) {
        StringBuilder sb = new StringBuilder(512);
        try {
            Reader r = new InputStreamReader(is, "UTF-8");
            int c = 0;
            while ((c = r.read()) != -1) {
                sb.append((char) c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

}

