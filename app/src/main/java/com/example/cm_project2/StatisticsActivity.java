package com.example.cm_project2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class StatisticsActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    TextView mTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        myDb = new DatabaseHelper(this);

        countPieces();
    }

    //TESTAR SE FUNCIONA
    /*@Override
    protected  void onStop() {
        myDb.resetCsvTable();
        super.onStop();
    }*/

    public void countPieces() {
        Cursor res = myDb.countPieces();
        if (res.getCount() == 0) {
            //show message;
            Toast.makeText(StatisticsActivity.this, "Erro! Não foram encontrados dados!", Toast.LENGTH_LONG).show();
        }
        StringBuilder buffer = new StringBuilder();
        while (res.moveToNext()) {
            buffer.append("Quantidade de peças processadas: ").append(res.getString(0));
        }
        //Show all data
        showMessage(buffer.toString());
    }

    public void showMessage(String message) {
        mTxt = findViewById(R.id.textView_statistics);
        mTxt.setText(message);
    }

}
