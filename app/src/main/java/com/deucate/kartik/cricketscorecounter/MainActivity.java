package com.deucate.kartik.cricketscorecounter;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Style;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

import static com.deucate.kartik.cricketscorecounter.AddActivity.DATE;
import static com.deucate.kartik.cricketscorecounter.AddActivity.OVER;
import static com.deucate.kartik.cricketscorecounter.AddActivity.TABLE_NAME;
import static com.deucate.kartik.cricketscorecounter.AddActivity.TEAM_1;
import static com.deucate.kartik.cricketscorecounter.AddActivity.TEAM_2;

public class MainActivity extends AppCompatActivity {

    public ListView mListView;
    private Cursor cursor;

    private List<ListViewGS> mGSList;
    ListViewAdapter mAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        toolbar.setTitle("Welcome");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        toolbar.setDrawingCacheBackgroundColor(Color.WHITE);
        toolbar.animate();
        setSupportActionBar(toolbar);


        //AdView
        AdView adView = (AdView) findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.mainFabBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);

            }
        });

        //initializing
        mListView = (ListView) findViewById(R.id.mainList);
        MatchDatabase mMatchDatabase = new MatchDatabase(getBaseContext(), "CricketMatch", null, 1);
        SQLiteDatabase database = mMatchDatabase.getReadableDatabase();

        cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        mGSList = new ArrayList<>();
        getDataFromDatabase(cursor);

        //mAdapter = new ListViewAdapter(MainActivity.this,R.layout.main_list_view,mGSList);
        mListView.setAdapter(mAdapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, RunActivity.class);
                intent.putExtra("Position", position);
                startActivity(intent);

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        getDataFromDatabase(cursor);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getDataFromDatabase(cursor);
    }

    private void getDataFromDatabase(Cursor cursor) {
        while (cursor.moveToNext()) {
            String date = cursor.getString(cursor.getColumnIndex(DATE));
            int over = cursor.getInt(cursor.getColumnIndex(OVER));
            String team1 = cursor.getString(cursor.getColumnIndex(TEAM_1));
            String team2 = cursor.getString(cursor.getColumnIndex(TEAM_2));

            ListViewGS listViewGS = new ListViewGS(team1, team2, date, over);

            // mGSList.add(listViewGS);
            mAdapter = new ListViewAdapter(MainActivity.this, R.layout.main_list_view, mGSList);
            mAdapter.add(listViewGS);


        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_donet:

                new MaterialStyledDialog.Builder(this).setDescription("We are working on this section. Please for check update in playstore.").setIcon(R.drawable.working).setStyle(Style.HEADER_WITH_ICON)
                        .setCancelable(false).setHeaderColor(R.color.colorPrimary).setPositiveText("Ok").onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                }).show();

                return true;

            case R.id.action_Like:
                new MaterialStyledDialog.Builder(this).setDescription("We are working on this section. Please for check update in playstore.").setIcon(R.drawable.working).setStyle(Style.HEADER_WITH_ICON)
                        .setCancelable(false).setHeaderColor(R.color.colorPrimary).setPositiveText("Ok").onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                }).show();
                return true;
            case R.id.action_rate:
                new MaterialStyledDialog.Builder(this).setDescription("We are working on this section. Please for check update in playstore.").setIcon(R.drawable.working).setStyle(Style.HEADER_WITH_ICON)
                        .setCancelable(false).setHeaderColor(R.color.colorPrimary).setPositiveText("Ok").onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                }).show();
                return true;
            case R.id.action_share:
                new MaterialStyledDialog.Builder(this).setDescription("We are working on this section. Please for check update in playstore.").setIcon(R.drawable.working).setStyle(Style.HEADER_WITH_ICON)
                        .setCancelable(false).setHeaderColor(R.color.colorPrimary).setPositiveText("Ok").onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                }).show();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }

    }
}
