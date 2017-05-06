package com.deucate.kartik.cricketscorecounter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.deucate.kartik.cricketscorecounter.AddActivity.DATE;
import static com.deucate.kartik.cricketscorecounter.AddActivity.OVER;
import static com.deucate.kartik.cricketscorecounter.AddActivity.TABLE_NAME;
import static com.deucate.kartik.cricketscorecounter.AddActivity.TEAM_1;
import static com.deucate.kartik.cricketscorecounter.AddActivity.TEAM_2;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "----------->";
    public ListView mListView;
    private static SQLiteDatabase database;
    private Cursor cursor;

    private List<ListViewGS> mGSList;
    ListViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

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
        database = mMatchDatabase.getReadableDatabase();

        cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        mGSList = new ArrayList<>();
        getDataFromDatabase(cursor);

        //mAdapter = new ListViewAdapter(MainActivity.this,R.layout.main_list_view,mGSList);
        mListView.setAdapter(mAdapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this,RunActivity.class);
                intent.putExtra("Position",position);
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

            ListViewGS listViewGS = new ListViewGS(team1,team2,date,over);

           // mGSList.add(listViewGS);
            mAdapter = new ListViewAdapter(MainActivity.this,R.layout.main_list_view,mGSList);
           mAdapter.add(listViewGS);


        }
    }

}
