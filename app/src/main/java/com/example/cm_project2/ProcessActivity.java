package com.example.cm_project2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ProcessActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    Button btn;
    TextView mTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);

        //Initialize database
        myDb = new DatabaseHelper(this);

        //Button  Animated
        btn = findViewById(R.id.button);

       /*Animation animation = AnimationUtils.loadAnimation(ProcessActivity.this, R.anim.lefttoright);
        animation.setDuration(10000);
        btn.startAnimation(animation);*/

        //line = myDb.countLines(); //22 //Criar ciclo for dentro de outro em que lê a primeira linha de dados, executa os movimentos pretendidos para essa primeira linha e só depois avança para a proxima
        //column = 17;
        //getColumnLineData(column, line);
        //Log.d("TAG", "getColumnLineData: " + getColumnLineData(column, line));
        //Start Animation
        processAnimation();
    }

    //Read database data sensor
    public String getSensorData(int column, int line){
        Cursor res = myDb.getAllData(line);
        if(res.getCount() == 0){
            //show message;
            Toast.makeText(ProcessActivity.this, "Erro! Não foram encontrados dados!", Toast.LENGTH_LONG).show();
        }
        StringBuilder buffer = new StringBuilder();
        while(res.moveToNext()){
            buffer.append(res.getString(column));
        }
            //Show all data
        showMessage(buffer.toString());
        return buffer.toString();
    }

    //Get column name from database
    public String getColumn(int column, int line){
        Cursor res = myDb.getAllData(line);
        StringBuilder buffer = new StringBuilder();
        while(res.moveToNext()){
            buffer.append(res.getColumnName(column));
        }
        return buffer.toString();
    }

    //Transformar método para mostrar as mensagens de sensores ativos, tipo de peças detetadas e
    public void showMessage(String message){
        mTxt = findViewById(R.id.textView_test);
        mTxt.setText(message);
    }

    //Do Animations
    @SuppressWarnings("SpellCheckingInspection")
    public void processAnimation(){
        for (int i = 1; i < myDb.countLines(); i++) {
            for (int j = 1; j < 18; j++) {
                String col_name = getColumn(j, i);
                String col_value = getSensorData(j,i);
                //Log.d("TAG", "Linha: " + i);
                //Log.d("TAG", "Coluna: " + j);
                //Log.d("TAG", "-----------col_1: " + col_1);
                //Log.d("TAG", "col_1 = 1: " + col_1);
                switch (col_name) {
                    //S1 ou SI: Se existe uma peça presente no tapete na posição ninicial: bit a 1, caso contrário:nbit a 0
                    case "S1":
                        if (col_value.equals("1")) {
                            //Log.d("TAG", "Coluna 1: " + col_name + " = " + col_value);
                            Animation animation = AnimationUtils.loadAnimation(ProcessActivity.this, R.anim.lefttoright);
                            animation.setDuration(1500);
                            btn.startAnimation(animation);
                        }else{
                            //Log.d("TAG", "Coluna 0: " + col_name + " = " + col_value);
                        }
                    case "S2":
                        //S2 ou SM: se a peça é de metal: bit a 1, caso contrário: bit a 0
                    case "S3":
                        //S3: Se a peça é branca: bit a 1, caso contrário: bit a 0
                    case "S4":
                        //S4 ou SA: deteta se a peça foi arrumada (avanço) com sucesso pelo cilindro A e este voltou à posição original (recuo): bit a 1,caso contrário: bit a 0
                    case "S5":
                        //S5 ou SB: deteta se a peça foi arrumada (avanço) com sucesso pelo cilindro B e este voltou à posição original (recuo): bit a 1, caso contrário: bit a 0
                    case "S6":
                        //S6 ou SC: deteta se a peça foi arrumada (avanço) com sucesso pelo cilindro C e este voltou à posição original (recuo): bit a 1, caso contrário: bit a 0
                    /*case "S7":
                        //Do Something
                    case "S8":
                        //Do Something
                    case "C1":
                        //C1 (HL1): Estado Laranja (ligado: bit a 1, desligado: bit a 0)
                    case "C2":
                        //C2 (HL2): Estado Verde (ligado: bit a 1, desligado: bit a 0)
                    case "C3":
                        //C3 (HL3): Estado Vermelho (ligado: bit a 1, desligado: bit a 0)
                    case "C4":
                        //C4 (SB1): Ordem de Execução Start (quando pressionado: bit a 1, caso contrário bit a 0)
                    case "C5":
                        //C5 (SB2): Ordem de Execução Stop (quando pressionado: bit a 1, caso contrário bit a 0)
                    case "C6":
                        //C6 (SA): Modo de Seleção (bit a 0) ou Modo de Execução (bit a 1)
                    case "C7":
                        //C7 (QS): Emergência (quando pressionado: bit a 1, caso contrário bit a 0)
                    case "C8":
                        //Componente não existente com bit sempre a 0
                    case "C9":
                        //Do Something
                    case "DATE":
                        //Do Something*/
                        break;
                }
            }
        }
    }
}
