package ericwolf.genkiii;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class GrammarActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar);


    }

    public void gotopotential(View view) {
        Intent intent = new Intent(this, GrammarEntry.class);
        intent.putExtra(EXTRA_MESSAGE, "potential_verbs.txt");

        startActivity(intent);

    }




}
