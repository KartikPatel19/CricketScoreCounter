package com.deucate.kartik.cricketscorecounter;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Style;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    //constants
    public static final String TABLE_NAME = "CricketTable";
    public static final String _ID = "match";
    public static final String TEAM_1 = "team1";
    public static final String TEAM_2 = "team2";
    public static final String OVER = "over";
    public static final String DATE = "date";
    public static final String RUN = "run";
    public static final String WICKET = "wicket";
    public static final String BALL = "ball";

    //full table uri
    public SQLiteDatabase database;

    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE "+ TABLE_NAME + "("+ _ID + " INTEGER PRIMARY KEY, "+TEAM_1+" TEXT, "+TEAM_2+" TEXT, "+ OVER+" INTEGER, "+
            DATE+" TEXT, "+RUN+" INTEGER, "+WICKET+" INTEGER, "+BALL+" INTEGER); ";


    //Variable for all text
    private String team1Name = "";
    private String team2Name = "";
    public static String DATEOF = "";
    public String overTemp = "";
    private int over = 0;

    //All widget
    private EditText mTeam1ET;
    private EditText mTeam2ET;
    private EditText mOverET;
    private Button mDatePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //AdView
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        mTeam1ET = (EditText) findViewById(R.id.addTeam1ET);
        mTeam2ET = (EditText) findViewById(R.id.addTeam2ET);
        mOverET = (EditText) findViewById(R.id.addOverEt);
        mDatePicker = (Button) findViewById(R.id.addPickDateBtn);
        Button doneBtn = (Button) findViewById(R.id.addDoneBtn);


        MatchDatabase mMatchDatabase = new MatchDatabase(getBaseContext(),"CricketMatch",null,1);
        database = mMatchDatabase.getWritableDatabase();


        mDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month = month+1;
                        String date = dayOfMonth+"/"+month+"/"+year;
                        mDatePicker.setText(date);
                        DATEOF = date;

                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE));

                datePickerDialog.show();

            }
        });


        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                team1Name = mTeam1ET.getText().toString();
                team2Name = mTeam2ET.getText().toString();
                overTemp = mOverET.getText().toString();

                if (TextUtils.isEmpty(team1Name) || TextUtils.isEmpty(team2Name) || TextUtils.isEmpty(overTemp)){
                    Toast.makeText(AddActivity.this, "Please enter all detail", Toast.LENGTH_SHORT).show();
                    return;
                }
                over = Integer.parseInt(overTemp);
                if (DATEOF.equals("")||DATEOF==null){
                    Calendar calendar = Calendar.getInstance();
                    @SuppressLint("SimpleDateFormat")
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    DATEOF = simpleDateFormat.format(calendar.getTime());
                }

                ContentValues values = new ContentValues();
                values.put(TEAM_1,team1Name);
                values.put(TEAM_2,team2Name);
                values.put(OVER,over);
                values.put(DATE,DATEOF);
                values.put(RUN,0);
                values.put(WICKET,0);
                values.put(BALL,over*6);
                database.insert(TABLE_NAME,null,values);

                MaterialStyledDialog.Builder builder = new MaterialStyledDialog.Builder(AddActivity.this).setTitle("Good Job.").setCancelable(false).setStyle(Style.HEADER_WITH_ICON).setIcon(R.drawable.run_icon).setHeaderColor(R.color.colorPrimary)
                        .withDialogAnimation(true).setDescription("Well done!\n See you in the match ;)").setPositiveText("OK").onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                Intent intent = new Intent(AddActivity.this,MainActivity.class);
                                startActivity(intent);
                            }
                        });

                builder.show();

            }
        });


    }
}
