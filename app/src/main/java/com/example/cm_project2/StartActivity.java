package com.example.cm_project2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.List;
import java.util.Locale;


public class StartActivity extends Activity {

    DatabaseHelper myDb;
    private ListView listView;
    private ItemArrayAdapter itemArrayAdapter;
    private String s1;
    private String s2;
    Button btnAddData;

    private static final long START_TIME_IN_MILLIS = 4000;
    private TextView mTextViewCountDown;
    private ProgressBar mProgressBar;
    private Switch mButtonStart;
    private CountDownTimer mCoutDouwnTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMilis = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        listView = findViewById(R.id.listView);
        itemArrayAdapter = new ItemArrayAdapter(getApplicationContext(), R.layout.item_layout);

        Parcelable state = listView.onSaveInstanceState();
        listView.setAdapter(itemArrayAdapter);
        listView.onRestoreInstanceState(state);

        InputStream inputStream = getResources().openRawResource(R.raw.dados_teste_processo2); //CSV FILE NAME
        CSVFile csvFile = new CSVFile(inputStream);
        List<String[]> processList = csvFile.read();

        for (String[] scoreData : processList) {
            itemArrayAdapter.add(scoreData);
        }

        btnAddData = findViewById(R.id.btnAddData);

        //Initialize database
        myDb = new DatabaseHelper(this);
        AddData();

        mTextViewCountDown = findViewById(R.id.textView_time);
        mButtonStart = findViewById(R.id.btn_start);
        mProgressBar = findViewById(R.id.progressBar_timer);

        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextViewCountDown.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(ProgressBar.VISIBLE);
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });
        updateCountDownText();
    }

    //Add csv file data to database
    public void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted;
                        for (int i = 0; i < itemArrayAdapter.getCount(); i++) {
                            String[] linha1 = itemArrayAdapter.getItem(i);
                            for (int j = 0; j < 1; j++) {
                                String coluna1 = linha1[j];
                                String coluna2 = linha1[j + 1];
                                String coluna3 = linha1[j + 2];
                                String coluna4 = linha1[j + 3];
                                String coluna5 = linha1[j + 4];
                                String coluna6 = linha1[j + 5];
                                String coluna7 = linha1[j + 6];
                                String coluna8 = linha1[j + 7];
                                String coluna9 = linha1[j + 8];
                                String coluna10 = linha1[j + 9];
                                String coluna11 = linha1[j + 10];
                                String coluna12 = linha1[j + 11];
                                String coluna13 = linha1[j + 12];
                                String coluna14 = linha1[j + 13];
                                String coluna15 = linha1[j + 14];
                                String coluna16 = linha1[j + 15];
                                String coluna17 = linha1[j + 16];
                                isInserted = myDb.insertData(coluna1, coluna2, coluna3, coluna4, coluna5, coluna6, coluna7, coluna8, coluna9, coluna10,
                                        coluna11, coluna12, coluna13, coluna14, coluna15, coluna16, coluna17);
                                // Log.d("TAG", "Linha: " + coluna1 + " " + coluna2 + " ");
                            }
                        }
                        if (isInserted = true) {
                            Toast.makeText(StartActivity.this, "Dados importados e inseridos!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(StartActivity.this, "Erro! Dados não inseridos!", Toast.LENGTH_LONG).show();

                        }
                    }
                }
        );
    }

    private void startTimer() {
        mCoutDouwnTimer = new CountDownTimer(mTimeLeftInMilis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMilis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                Intent intent = new Intent(StartActivity.this, ProcessActivity.class);
                startActivity(intent);
            }
        }.start();

        mTimerRunning = true;
        mButtonStart.setText("Iniciar processo");
    }

    private void pauseTimer() {
        mCoutDouwnTimer.cancel();
        mTimerRunning = false;
    }

    private void updateCountDownText() {
        int seconds = (int) mTimeLeftInMilis / 100 % 9;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%01d", seconds);
        mTextViewCountDown.setText(timeLeftFormatted);
    }
}
