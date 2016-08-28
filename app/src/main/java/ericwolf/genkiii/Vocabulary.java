package ericwolf.genkiii;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Eric on 11.08.2016.
 */
public class Vocabulary extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voc);




    }

    public void gotoall(View view) {
        Intent intent = new Intent(this, VocLearn.class);
        intent.putExtra(EXTRA_MESSAGE, "all");
        startActivity(intent);
    }
    public void gotoverb(View view) {
        Intent intent = new Intent(this, VocLearn.class);
        intent.putExtra(EXTRA_MESSAGE, "verbs_utf8");
        startActivity(intent);
    }
    public void gotoadj(View view) {
        Intent intent = new Intent(this, VocLearn.class);
        intent.putExtra(EXTRA_MESSAGE, "adjectives_utf8");
        startActivity(intent);
    }
    public void gotonom(View view) {
        Intent intent = new Intent(this, VocLearn.class);
        intent.putExtra(EXTRA_MESSAGE, "nouns_utf8");
        startActivity(intent);
    }

    public void gotoadv(View view) {
        Intent intent = new Intent(this, VocLearn.class);
        intent.putExtra(EXTRA_MESSAGE, "adverbs_utf8");
        startActivity(intent);
    }
}
