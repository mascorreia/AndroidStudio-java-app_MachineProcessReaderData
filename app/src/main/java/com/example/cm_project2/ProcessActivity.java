package com.example.cm_project2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ProcessActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    Button btn;
    TextView mTxt;
    private int column, line;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);

        //Initialize database
        myDb = new DatabaseHelper(this);

        btn = (Button)findViewById(R.id.button);



        Animation animation = AnimationUtils.loadAnimation(ProcessActivity.this, R.anim.lefttoright);
        animation.setDuration(10000);
        btn.startAnimation(animation);

        //line = myDb.countLines(); //22 //Criar ciclo for dentro de outro em que lê a primeira linha de dados, executa os movimentos pretendidos para essa primeira linha e só depois avança para a proxima
        //column = 17;
        //getColumnLineData(column, line);
        //Log.d("TAG", "getColumnLineData: " + getColumnLineData(column, line));

    }

    //Read database data and compare with values.
    public String getColumnLineData(int column, int line){
        Cursor res = myDb.getAllData(line);
        if(res.getCount() == 0){
            //show message;
            Toast.makeText(ProcessActivity.this, "Erro! Não foram encontrados dados!", Toast.LENGTH_LONG).show();
        }
        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            buffer.append(res.getString(column));
        }
            //Show all data
        showMessage(buffer.toString());
        return buffer.toString();
    }

    //Transformar método para mostrar as mensagens de sensores ativos, tipo de peças detetadas e
    public void showMessage(String message){
        mTxt = findViewById(R.id.textView_test);
        mTxt.setText(message);
    }

    public void processAnimation(){
        for (int i = 0; i < myDb.countLines(); i++) {
            for (int j = 0; j < 1; j++) {
                getColumnLineData(i,j);
                showMessage(getColumnLineData(i,j));
            }
        }
    }
}
