package ericwolf.genkiii;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void gotovoc(View view) {
        Intent intent = new Intent(this, Vocabulary.class);
        startActivity(intent);
    }

    public void gotokanjis(View view) {
        Intent intent = new Intent(this, KanjiListActivity.class);
        startActivity(intent);
    }

    public void gotogrammar(View view) {
        Intent intent = new Intent(this, GrammarActivity.class);
        startActivity(intent);
    }


}
