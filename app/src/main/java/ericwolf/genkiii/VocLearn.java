package ericwolf.genkiii;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

import ericwolf.genkiii.helper_classes.Loading;

import static android.view.View.OnClickListener;

public class VocLearn extends AppCompatActivity implements OnClickListener {

    /***
     * Defines the Learn mode (switching vocs)
    */

    private TextSwitcher mSwitcher;
    private Button btnNext;

    private Boolean enjap = true;

    public final static String EXTRA_MESSAGE = "";

    private static Animation animation;

    public List<String> engList_static = new ArrayList<>();
    public List<String> japList_static = new ArrayList<>();
    public List<String> engList_rand = new ArrayList<>();
    public List<String> japList_rand = new ArrayList<>();
    public List<String> engList = new ArrayList<>();
    public List<String> japList = new ArrayList<>();

    public TextView myText;
    public Switch mySwitch;
    public Switch dirSwitch;
    public String name;

    int dictCount = 0;
    // 1 == englisch, 0 == japanese
    int currDict = 1;
    int currIndex = 0;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_list:
                Intent intent = new Intent(this, VocListActivity.class);
                intent.putExtra(EXTRA_MESSAGE, name);
                this.startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voclearn);


        Intent intent = getIntent();
        name = intent.getStringExtra(Vocabulary.EXTRA_MESSAGE);

        learning(name);


        mySwitch = (Switch) findViewById(R.id.shuffle);



        //attach a listener to check for changes in state
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    long seed = System.nanoTime();
                    Collections.shuffle(engList_rand, new Random(seed));
                    Collections.shuffle(japList_rand, new Random(seed));
                    engList = engList_rand;
                    japList = japList_rand;
                    // if english to japanese
                    if(enjap){
                        //if current voc is japanese
                        if (!((currIndex & 1) == 0)) {
                            currIndex += 1;
                        }
                        currDict = 1;
                        mSwitcher.setText(engList.get(currIndex));
                        btnNext.setText("Next");
                    }
                    //if current voc is english
                    else{
                        if (((currIndex & 1) == 0)) {
                            currIndex += 1;
                        }
                        currDict = 0;
                        mSwitcher.setText(japList.get(currIndex));
                        btnNext.setText("Next");
                    }

                } else {
                    // if japanese to english
                    if(enjap) {
                        engList = engList_static;
                        japList = japList_static;
                        //if current voc is english
                        if (((currIndex & 1) == 0)) {
                            currIndex += 1;
                        }
                        currDict = 1;
                        mSwitcher.setText(engList.get(currIndex));
                        btnNext.setText("Solve");
                    }
                    else{
                        engList = engList_static;
                        japList = japList_static;
                        //if current voc is japanese
                        if (!((currIndex & 1) == 0)) {
                            currIndex += 1;
                        }
                        currDict = 0;
                        mSwitcher.setText(japList.get(currIndex));
                        btnNext.setText("Solve");
                    }
                }

            }
        });


        dirSwitch = (Switch) findViewById(R.id.switch_dir);

        //attach a listener to check for changes in state
        dirSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    enjap = false;
                    // if it is japanese
                    if (currDict == 0){
                        btnNext.setText("Solve");
                    }
                    else{
                        btnNext.setText("Next");
                    }
                    dirSwitch.setText("Jap -> En");


                } else {
                    enjap = true;
                    if (currDict == 1){
                        btnNext.setText("Solve");
                    }
                    else{
                        btnNext.setText("Next");
                    }
                    dirSwitch.setText("En -> Jap");
                }

            }
        });


    }

        //Log.d("voc", adjectives_ej.get("glad"));


    public void learning(String name){

        AssetManager am = getAssets();
        Loading adj = new Loading(name);
        List<Map<String, String>> dictionaryList = adj.getTxt(am);
        Map<String, String> dict_ej = dictionaryList.get(0);
        Map<String, String> dict_je = dictionaryList.get(1);
        for (Map.Entry<String, String> entry : dict_ej.entrySet()) {
            //Log.d("voc", entry.getKey());
            engList.add(entry.getKey());
            engList_static.add(entry.getKey());
            engList_rand.add(entry.getKey());
            japList.add(entry.getValue());
            japList_static.add(entry.getValue());
            japList_rand.add(entry.getValue());
        }
        dictCount = engList.size();

        btnNext = (Button) findViewById(R.id.next);
        animation = AnimationUtils.loadAnimation(VocLearn.this,
                R.anim.fade_in);
        mSwitcher = (TextSwitcher) findViewById(R.id.textSwitcher);

        mSwitcher.setFactory(new ViewSwitcher.ViewFactory() {

            public View makeView() {
                // create new textView and set the properties like clolr, size etc
                myText = new TextView(VocLearn.this);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    myText.setForegroundGravity(Gravity.FILL);
                }
                myText.setGravity(Gravity.CENTER);
                myText.setTextSize(40);
                myText.setTextColor(Color.BLUE);
                myText.setText(engList.get(currIndex));
                return myText;
            }
        });

        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        mSwitcher.setInAnimation(in);
        mSwitcher.setOutAnimation(out);


        btnNext.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                Log.d("dir", enjap.toString());
                btnNext.startAnimation(animation);
                if (currDict == 0) {

                    if (!enjap){

                        btnNext.setText("Next");
                        if (currIndex == dictCount)
                            currIndex = 0;
                        mSwitcher.setText(engList.get(currIndex));
                        currDict = 1;

                    }
                    else{
                        currIndex++;
                        btnNext.setText("Solve");
                        if (currIndex == dictCount)
                            currIndex = 0;
                        mSwitcher.setText(engList.get(currIndex));
                        currDict = 1;

                    }

                } else {
                    if (!enjap){
                        currIndex++;
                        btnNext.setText("Solve");
                        if (currIndex == dictCount)
                            currIndex = 0;
                        mSwitcher.setText(japList.get(currIndex));
                        currDict = 0;

                    }
                    else{

                        btnNext.setText("Next");
                        if (currIndex == dictCount)
                            currIndex = 0;
                        mSwitcher.setText(japList.get(currIndex));
                        currDict = 0;


                    }

                }


            }
        });

    }




    @Override
    public void onClick(View view) {

    }
    
}
