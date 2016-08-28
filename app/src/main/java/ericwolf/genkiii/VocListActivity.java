package ericwolf.genkiii;

/**
 * Created by Eric on 09.08.2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import ericwolf.genkiii.describer_classes.VocList;

public class VocListActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "";
    public String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        name = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // create our UI using a fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentById(android.R.id.content) == null) {

            VocList fragment = VocList.newInstance();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(android.R.id.content, fragment);
            ft.commit();
        }

        ((AppCompatActivity)this).getSupportActionBar().setDisplayShowHomeEnabled(true);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

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
}
