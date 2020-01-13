package com.example.cm_project2;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.util.List;


public class StartActivity extends Activity {

    DatabaseHelper myDb;
    private ListView listView;
    private ItemArrayAdapter itemArrayAdapter;
    private String s1;
    private String s2;
    Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        listView = (ListView) findViewById(R.id.listView);
        itemArrayAdapter = new ItemArrayAdapter(getApplicationContext(), R.layout.item_layout);

        Parcelable state = listView.onSaveInstanceState();
        listView.setAdapter(itemArrayAdapter);
        listView.onRestoreInstanceState(state);

        InputStream inputStream = getResources().openRawResource(R.raw.dados_teste_processo2); //CSV FILE NAME
        CSVFile csvFile = new CSVFile(inputStream);
        List<String[]> scoreList = csvFile.read();

        for (String[] scoreData : scoreList) {
            itemArrayAdapter.add(scoreData);
        }

        String[] stat2 = itemArrayAdapter.getItem(0);
        s1 = stat2[0];
        s2 = stat2[1];
        Log.d("CREATION", "Linha : " + s1 + s2);

        btnAddData = (Button) findViewById(R.id.btnAddData);

        //Create database
        myDb = new DatabaseHelper(this);
        AddData();
    }

    //Add csv file data to database
    public void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Log.d("CREATION", "Linha : " +s1 + " " + s2);
                        boolean isInserted;
                        Log.d("CREATION", "COUNT : " + itemArrayAdapter.getCount());
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
                                //Log.d("epa", "Linha: " + coluna1 + " " + coluna2 + " ");
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
}
