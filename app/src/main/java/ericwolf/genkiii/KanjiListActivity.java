package ericwolf.genkiii;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import ericwolf.genkiii.describer_classes.KanjiList;

/**
 * Created by Eric on 11.08.2016.
 */
public class KanjiListActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "";
    public String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // create our UI using a fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentById(android.R.id.content) == null) {

            KanjiList fragment = KanjiList.newInstance();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(android.R.id.content, fragment);
            ft.commit();
        }

        //ListView listView = (ListView) listView.findViewById(R.id.custom_list_kanji);



        //((AppCompatActivity)this).getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    /**
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    */

    /**
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_learn:
                Intent intent2 = new Intent(this, VocLearn.class);
                intent2.putExtra(EXTRA_MESSAGE, name);
                this.startActivity(intent2);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
    */
}
