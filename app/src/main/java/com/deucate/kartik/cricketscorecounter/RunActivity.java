package com.deucate.kartik.cricketscorecounter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.deucate.kartik.cricketscorecounter.HistoryMode.HistoryAdapter;
import com.deucate.kartik.cricketscorecounter.HistoryMode.HistoryGs;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.michael.easydialog.EasyDialog;

import java.util.ArrayList;
import java.util.List;

import static com.deucate.kartik.cricketscorecounter.AddActivity.BALL;
import static com.deucate.kartik.cricketscorecounter.AddActivity.RUN;
import static com.deucate.kartik.cricketscorecounter.AddActivity.TABLE_NAME;
import static com.deucate.kartik.cricketscorecounter.AddActivity.TEAM_1;
import static com.deucate.kartik.cricketscorecounter.AddActivity.TEAM_2;
import static com.deucate.kartik.cricketscorecounter.AddActivity.WICKET;
import static com.deucate.kartik.cricketscorecounter.AddActivity._ID;

public class RunActivity extends AppCompatActivity {

    int mPosition;
    int currentRun;
    int ball;
    int wicket;
    int where;
    int matchCount;
    int tappedRun;
    int temp = 0;

    Button run0, run1, run2, run3, run4, run6, runNo, runOut, runWide;
    ImageButton undoBTn;
    TextView team1TV, team2TV, ballTV, RunTV, wicketTV;

    RecyclerView mRecyclerView;
    List<HistoryGs> mHistoryGses = new ArrayList<>();
    HistoryAdapter mAdapter;

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

        mRecyclerView = (RecyclerView) findViewById(R.id.runRecyclerView);

        run0 = (Button) findViewById(R.id.run0);
        run1 = (Button) findViewById(R.id.run1);
        run2 = (Button) findViewById(R.id.run2);
        run3 = (Button) findViewById(R.id.run3);
        run4 = (Button) findViewById(R.id.run4);
        run6 = (Button) findViewById(R.id.run6);
        runNo = (Button) findViewById(R.id.runNO);
        runOut = (Button) findViewById(R.id.runOUT);
        runWide = (Button) findViewById(R.id.runWide);
        undoBTn = (ImageButton) findViewById(R.id.runUndoBtn);
        run0.setOnClickListener(mClickListener);
        run1.setOnClickListener(mClickListener);
        run2.setOnClickListener(mClickListener);
        run3.setOnClickListener(mClickListener);
        run4.setOnClickListener(mClickListener);
        run6.setOnClickListener(mClickListener);
        runNo.setOnClickListener(mClickListener);
        runOut.setOnClickListener(mClickListener);
        runWide.setOnClickListener(mClickListener);

        undoBTn.setVisibility(View.INVISIBLE);

        MatchDatabase mMatchDatabase = new MatchDatabase
                (getBaseContext(), "CricketMatch", null, 1);
        database = mMatchDatabase.getReadableDatabase();

        @SuppressLint("Recycle")
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            mPosition = bundle.getInt("Position");
        }


        cursor.move(mPosition + 1);
        matchCount = cursor.getInt(cursor.getColumnIndex(_ID));
        ball = cursor.getInt(cursor.getColumnIndex(BALL));
        currentRun = cursor.getInt(cursor.getColumnIndex(RUN));
        wicket = cursor.getInt(cursor.getColumnIndex(WICKET));
        team1TV.setText(cursor.getString(cursor.getColumnIndex(TEAM_1)));
        team2TV.setText(cursor.getString(cursor.getColumnIndex(TEAM_2)));
        ballTV.setText(ball + "");
        RunTV.setText(currentRun + "");
        wicketTV.setText(wicket + "");
        buttonisiblity();

        mAdapter = new HistoryAdapter(mHistoryGses);
        RecyclerView.LayoutManager manager = new LinearLayoutManager
                (getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.run0: {
                    tappedRun = 0;
                    temp = 6;
                    ball--;
                    ballTV.setText(ball + "");
                    createCheerDialoge(0);

                }
                break;
                case R.id.run1: {
                    tappedRun = 1;
                    temp = 0;
                    currentRun += 1;
                    ball--;
                    ballTV.setText(ball + "");
                    RunTV.setText(currentRun + "");
                    createCheerDialoge(1);
                }
                break;
                case R.id.run2: {
                    tappedRun = 2;
                    temp = 0;
                    currentRun += 2;
                    ball--;
                    ballTV.setText(ball + "");
                    RunTV.setText(currentRun + "");
                    createCheerDialoge(2);
                }
                break;
                case R.id.run3: {
                    tappedRun = 3;
                    temp = 0;
                    currentRun += 3;
                    ball--;
                    ballTV.setText(ball + "");
                    RunTV.setText(currentRun + "");
                    createCheerDialoge(3);
                }
                break;
                case R.id.run4: {
                    tappedRun = 4;
                    temp = 0;
                    currentRun += 4;
                    ball--;
                    ballTV.setText(ball + "");
                    RunTV.setText(currentRun + "");
                    createCheerDialoge(4);
                }
                break;
                case R.id.run6: {
                    tappedRun = 6;
                    temp = 0;
                    currentRun += 6;
                    ball--;
                    ballTV.setText(ball + "");
                    RunTV.setText(currentRun + "");
                    createCheerDialoge(6);
                }
                break;
                case R.id.runNO: {
                    temp = 1;
                    currentRun++;
                    AlertDialog.Builder builder = new AlertDialog.Builder(RunActivity.this);
                    builder.setTitle("Choose Run").setItems(R.array.no_run_arrey,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    currentRun = currentRun + which;
                                    RunTV.setText(currentRun + "");
                                }
                            }).show();

                }
                break;
                case R.id.runWide: {
                    temp = 2;
                    currentRun++;

                    AlertDialog.Builder builder = new AlertDialog.Builder(RunActivity.this);
                    builder.setTitle("Choose Run").setItems(R.array.wide_run_arrey,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    currentRun = currentRun + which;
                                    RunTV.setText(currentRun + "");
                                }
                            }).show();
                }
                break;
                case R.id.runOUT: {
                    temp = 3;
                    wicket++;
                    ball--;
                    buttonisiblity();
                    ballTV.setText(ball + "");
                    wicketTV.setText(wicket + "");
                    createCheerDialoge(9);
                }
                break;
            }
            String history = "";

            if (temp != 0) {
                switch (temp) {
                    case 1: {
                        history = "No Ball";
                    }
                    break;
                    case 2: {
                        history = "Wide Ball";
                    }
                    break;
                    case 3: {
                        history = "Out";
                    }
                    break;
                    case 6: {
                        history = "0";
                    }
                }
            } else {
                history = tappedRun + "";
            }
            if (undoBTn.getVisibility() == View.INVISIBLE) {
                undoBTn.setVisibility(View.VISIBLE);
            }

            HistoryGs historyGs = new HistoryGs(ball, history);
            mHistoryGses.add(historyGs);
            mRecyclerView.setAdapter(mAdapter);

            updateDatabase();
        }
    };

    private void buttonisiblity() {
        if (wicket > 9 || ball < 1) {
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


    private void updateDatabase() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(RUN, currentRun);
        contentValues.put(WICKET, wicket);
        contentValues.put(BALL, ball);
        where = mPosition + 1;
        database.update(TABLE_NAME, contentValues, _ID + " = " + where, null);

    }


    public void onDataDelete(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(RunActivity.this);
        builder.setTitle("Alert!!").setMessage("Are you sure to delete record")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //todo delete row
                        Toast.makeText(RunActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                        database.delete(TABLE_NAME, _ID + " = " + matchCount, null);
                        Intent intent = new Intent(RunActivity.this, MainActivity.class);
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

    @SuppressLint("SetTextI18n")
    public void onUndoClick(View view) {

        ball++;
        ballTV.setText(ball + "");
        currentRun = currentRun - tappedRun;
        RunTV.setText(currentRun + "");
        int temp1 = mHistoryGses.size() - 1;
        mHistoryGses.remove(temp1);
        //  mRecyclerView.setAdapter(mAdapter);

        undoBTn.setVisibility(View.INVISIBLE);

    }

    private void createCheerDialoge(int id) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.cheerful_dialog, null);
        ImageView cheerImage = (ImageView) view.findViewById(R.id.cheer_image);

        switch (id){
            case 0:{
                cheerImage.setImageResource(R.drawable.run_0);
            }break;
            case 1:{
                cheerImage.setImageResource(R.drawable.run_1);
            }break;
            case 2:{
                cheerImage.setImageResource(R.drawable.run_2);
            }break;
            case 3:{
                cheerImage.setImageResource(R.drawable.run_3);
            }break;
            case 4:{
                cheerImage.setImageResource(R.drawable.run_4);
            }break;
            case 6:{
                cheerImage.setImageResource(R.drawable.run_6);
            }break;

            case 9:{
                cheerImage.setImageResource(R.drawable.run_out);
            }break;
        }

        new EasyDialog(RunActivity.this)
                .setLayout(view)
                .setBackgroundColor(Color.TRANSPARENT)
                .setGravity(EasyDialog.GRAVITY_BOTTOM)
                .setAnimationTranslationShow(EasyDialog.DIRECTION_X, 500, -600, 100, -50, 50, 0)
                .setAnimationAlphaShow(100, 0.3f, 1.0f)
                .setAnimationTranslationDismiss(EasyDialog.DIRECTION_X, 500, -50, 800)
                .setAnimationAlphaDismiss(100, 1.0f, 0.0f)
                .setTouchOutsideDismiss(true)
                .setMatchParent(true)
                .setOutsideColor(Color.TRANSPARENT)
                .setTouchOutsideDismiss(true)
                .show();



    }

}
