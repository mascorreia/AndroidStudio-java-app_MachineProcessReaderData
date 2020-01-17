package com.example.cm_project2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class StatisticsActivity extends AppCompatActivity {

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        myDb = new DatabaseHelper(this);

    }

    //TESTAR SE FUNCIONA
    @Override
    protected  void onDestroy() {
        myDb.resetCsvTable();
        super.onDestroy();
    }
}
