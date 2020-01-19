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
        Cursor countTotalPieces = myDb.countPieces();
        Cursor countIndividualPieces = myDb.countIndividualPieces();
        Cursor countCommandInterface = myDb.countCommandInterface();
        Cursor getProcessFinalTime = myDb.getProcessFinalTime();
        if (countTotalPieces.getCount() == 0 ||countIndividualPieces.getCount() == 0  || countCommandInterface.getCount() == 0 && getProcessFinalTime.getCount() == 0) {
            //show message;
            Toast.makeText(StatisticsActivity.this, "Erro!!", Toast.LENGTH_LONG).show();
        }
        StringBuilder buffer = new StringBuilder();
        while (countTotalPieces.moveToNext() && countIndividualPieces.moveToNext() && countCommandInterface.moveToNext() && getProcessFinalTime.moveToNext()) {
            buffer.append("Quantidade de peças processadas: ").append(countTotalPieces.getString(0));
            buffer.append("\n - Metal:  ").append(countIndividualPieces.getString(0));
            buffer.append("\n - Brancas: ").append(countIndividualPieces.getString(1));
            buffer.append("\n - Pretas: ").append(countIndividualPieces.getString(2));
            buffer.append("\n\n Interfaces de comando acionadas: ");
            buffer.append("\n - Estado laranja: ").append(countCommandInterface.getString(0));
            buffer.append("\n - Estado verde: ").append(countCommandInterface.getString(1));
            buffer.append("\n - Estado vermelho: ").append(countCommandInterface.getString(2));
            buffer.append("\n - Ordem de execução start: ").append(countCommandInterface.getString(3));
            buffer.append("\n - Ordem de execução stop: ").append(countCommandInterface.getString(4));
            buffer.append("\n - Modo de execução: ").append(countCommandInterface.getString(5));
            buffer.append("\n - Emergência: ").append(countCommandInterface.getString(6));
            buffer.append("\n\n\n Tempo do processo: ").append(getProcessFinalTime.getString(0));
        }
        //Show all data
        showMessage(buffer.toString());
    }

    public void showMessage(String message) {
        mTxt = findViewById(R.id.textView_statistics);
        mTxt.setText(message);
    }

}
