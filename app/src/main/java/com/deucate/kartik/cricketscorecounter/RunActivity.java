package com.deucate.kartik.cricketscorecounter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import static com.deucate.kartik.cricketscorecounter.AddActivity.BALL;
import static com.deucate.kartik.cricketscorecounter.AddActivity.RUN;
import static com.deucate.kartik.cricketscorecounter.AddActivity.TABLE_NAME;
import static com.deucate.kartik.cricketscorecounter.AddActivity.TEAM_1;
import static com.deucate.kartik.cricketscorecounter.AddActivity.TEAM_2;
import static com.deucate.kartik.cricketscorecounter.AddActivity.WICKET;
import static com.deucate.kartik.cricketscorecounter.AddActivity._ID;

public class RunActivity extends AppCompatActivity {

    int positon;
    int currentRun;
    int ball;
    int wicket;
    int where;
    int matchCount;

    Button run0, run1, run2, run3, run4, run6, runNo, runOut, runWide;
    TextView team1TV,team2TV,ballTV,RunTV,wicketTV;

    private SQLiteDatabase database;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);


        AdView adView = (AdView) findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

         team1TV = (TextView) findViewById(R.id.runTeam1);
         team2TV = (TextView) findViewById(R.id.runTeam2);
         ballTV = (TextView) findViewById(R.id.runOver);
         RunTV = (TextView) findViewById(R.id.runRun);
         wicketTV = (TextView) findViewById(R.id.runWicket);

        run0 = (Button) findViewById(R.id.run0);
        run1 = (Button) findViewById(R.id.run1);
        run2 = (Button) findViewById(R.id.run2);
        run3 = (Button) findViewById(R.id.run3);
        run4 = (Button) findViewById(R.id.run4);
        run6 = (Button) findViewById(R.id.run6);
        runNo = (Button) findViewById(R.id.runNO);
        runOut = (Button) findViewById(R.id.runOUT);
        runWide = (Button) findViewById(R.id.runWide);
        run0.setOnClickListener(mClickListener);
        run1.setOnClickListener(mClickListener);
        run2.setOnClickListener(mClickListener);
        run3.setOnClickListener(mClickListener);
        run4.setOnClickListener(mClickListener);
        run6.setOnClickListener(mClickListener);
        runNo.setOnClickListener(mClickListener);
        runOut.setOnClickListener(mClickListener);
        runWide.setOnClickListener(mClickListener);


        MatchDatabase mMatchDatabase = new MatchDatabase(getBaseContext(), "CricketMatch", null, 1);
        database = mMatchDatabase.getReadableDatabase();

        @SuppressLint("Recycle")
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            positon = bundle.getInt("Position");
        }


        cursor.move(positon + 1);
        matchCount = cursor.getInt(cursor.getColumnIndex(_ID));
        ball = cursor.getInt(cursor.getColumnIndex(BALL)) ;
        currentRun = cursor.getInt(cursor.getColumnIndex(RUN));
        wicket = cursor.getInt(cursor.getColumnIndex(WICKET));
        team1TV.setText(cursor.getString(cursor.getColumnIndex(TEAM_1)));
        team2TV.setText(cursor.getString(cursor.getColumnIndex(TEAM_2)));
        ballTV.setText(ball + "");
        RunTV.setText(currentRun + "");
        wicketTV.setText(wicket+"");
        buttonisiblity();

    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View v) {



            switch (v.getId()) {

                case R.id.run0: {
                    ball--;
                    ballTV.setText(ball + "");
                }
                break;
                case R.id.run1: {
                    currentRun += 1;
                    ball--;
                    ballTV.setText(ball + "");
                    RunTV.setText(currentRun + "");
                }
                break;
                case R.id.run2: {
                    currentRun += 2;
                    ball--;
                    ballTV.setText(ball + "");
                    RunTV.setText(currentRun + "");
                }
                break;
                case R.id.run3: {
                    currentRun += 3;
                    ball--;
                    ballTV.setText(ball + "");
                    RunTV.setText(currentRun + "");
                }
                break;
                case R.id.run4: {
                    currentRun += 4;
                    ball--;
                    ballTV.setText(ball + "");
                    RunTV.setText(currentRun + "");
                }
                break;
                case R.id.run6: {
                    currentRun += 6;
                    ball--;
                    ballTV.setText(ball + "");
                    RunTV.setText(currentRun + "");
                }
                break;
                case R.id.runNO: {

                    AlertDialog.Builder builder = new AlertDialog.Builder(RunActivity.this);
                    builder.setTitle("Choose Run").setItems(R.array.no_run_arrey, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int chooseRun = which+1;
                            currentRun = currentRun+chooseRun;
                            RunTV.setText(currentRun + "");
                        }
                    }).show();

                }
                break;
                case R.id.runWide: {

                    AlertDialog.Builder builder = new AlertDialog.Builder(RunActivity.this);
                    builder.setTitle("Choose Run").setItems(R.array.wide_run_arrey, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int chooseRun = which+1;
                            currentRun = currentRun+chooseRun;
                            RunTV.setText(currentRun + "");
                        }
                    }).show();
                }
                break;
                case R.id.runOUT: {
                    wicket++;
                    ball--;
                    buttonisiblity();
                    ballTV.setText(ball + "");
                    wicketTV.setText(wicket+"");
                }
                break;
            }
            updateDatabase();
        }
    };

    private void buttonisiblity() {
        if (wicket>9 || ball<1){
            run0.setVisibility(View.INVISIBLE);
            run1.setVisibility(View.INVISIBLE);
            run2.setVisibility(View.INVISIBLE);
            run3.setVisibility(View.INVISIBLE);
            run4.setVisibility(View.INVISIBLE);
            run6.setVisibility(View.INVISIBLE);
            runNo.setVisibility(View.INVISIBLE);
            runWide.setVisibility(View.INVISIBLE);
            runOut.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        updateDatabase();
        database.close();
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateDatabase();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateDatabase();
    }

    private void updateDatabase() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(RUN,currentRun);
        contentValues.put(WICKET,wicket);
        contentValues.put(BALL,ball);
        where = positon+1;
        database.update(TABLE_NAME,contentValues,_ID+" = "+where,null);
    }


    public void onDataDelete(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(RunActivity.this);
        builder.setTitle("Alert!!").setMessage("Are you sure to delete record")
        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //todo delete row
                Toast.makeText(RunActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                database.delete(TABLE_NAME,_ID+" = "+matchCount,null);
                Intent intent = new Intent(RunActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();


    }
}
