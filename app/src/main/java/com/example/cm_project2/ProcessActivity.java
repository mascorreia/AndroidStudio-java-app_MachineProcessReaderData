package com.example.cm_project2;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class ProcessActivity extends AppCompatActivity {

    private CustomView customView;

    DatabaseHelper myDb;
    Button cylinderA;
    Button cylinderB;
    Button cylinderC;
    TextView mTxt;
    RelativeLayout.LayoutParams lp;
    ImageView imageView;
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);

        //Initialize database
        myDb = new DatabaseHelper(this);

        //Cylinders
        cylinderA = findViewById(R.id.cylinderA);
        cylinderB = findViewById(R.id.cylinderB);
        cylinderC = findViewById(R.id.cylinderC);

        moveCylinder("A");
        moveCylinder("B");
        moveCylinder("C");

        //Start Animation
        processAnimation();
    }

    //Do Animations
    @SuppressWarnings("SpellCheckingInspection")
    public void processAnimation(){
        for (int i = 1; i < myDb.countLines(); i++) {
            for (int j = 1; j < 18; j++) {
                String col_name = getColumn(j, i);
                String col_value = getSensorData(j,i);
                switch (col_name) {
                    case "S1":
                        //S1 ou SI: Se existe uma peça presente no tapete na posição ninicial: bit a 1, caso contrário:nbit a 0
                        if (col_value.equals("1")) {
                            delayMessage(8000,"Encontra-se uma peça no copo de alimentação em espera!");
                        }else{
                            delayMessage(16000,"Nenhuma peça se encontra em espera");
                        }
                    case "S2":
                        //S2 ou SM: se a peça é de metal: bit a 1, caso contrário: bit a 0
                        if (col_value.equals("1")) {
                            delayMessage(32000,"A peça é de metal!");
                            createGrayPiece();
                        }else{
                            delayMessage(64000,"A não é de metal!");
                        }
                    case "S3":
                        //S3: Se a peça é branca: bit a 1, caso contrário: bit a 0
                        if (col_value.equals("1")) {
                            delayMessage(128000,"A peça é branca!");
                            createWhitePiece();
                        }else{
                            delayMessage(256000,"A peça não é branca!");
                            createBlackPiece();
                        }
                    case "S4":
                        //S4 ou SA: deteta se a peça foi arrumada (avanço) com sucesso pelo cilindro A e este voltou à posição original (recuo): bit a 1,caso contrário: bit a 0
                        /*if (col_value.equals("1")) {
                            delayMessage(512000,"A peça foi arrumada com sucesso pelo Cilindro A!");
                        }else{
                            delayMessage(1024000,"A peça não foi arrumada com sucesso pelo Cilindro A!");
                        }*/
                    case "S5":
                        //S5 ou SB: deteta se a peça foi arrumada (avanço) com sucesso pelo cilindro B e este voltou à posição original (recuo): bit a 1, caso contrário: bit a 0
                        /*if (col_value.equals("1")) {
                            delayMessage(2048000,"A peça foi arrumada com sucesso pelo Cilindro B!");
                        }else{
                            delayMessage(4096000,"A peça não foi arrumada com sucesso pelo Cilindro B!");
                        }*/
                    case "S6":
                        //S6 ou SC: deteta se a peça foi arrumada (avanço) com sucesso pelo cilindro C e este voltou à posição original (recuo): bit a 1, caso contrário: bit a 0
                        /*if (col_value.equals("1")) {
                            //delayMessage(8000,"A peça foi arrumada com sucesso pelo Cilindro C!");
                        }else{
                            //delayMessage(8000,"A peça não foi arrumada com sucesso pelo Cilindro C!");
                        }*/
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
                }
            }
        }
    }

    /*------- Create pieces --------*/

    public void createBlackPiece(){
        imageView = new ImageView(this); // initialize ImageView
        lp = new RelativeLayout.LayoutParams(80, 80);
        lp.addRule(RelativeLayout.ALIGN_BOTTOM); // A position in layout.
        imageView.setLayoutParams(lp);
        imageView.setImageResource(R.drawable.peca_pretaa);
        RelativeLayout layout = findViewById(R.id.process_RelativeLayout);
        layout.addView(imageView);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ObjectAnimator animationY = ObjectAnimator.ofFloat(imageView, "translationY", 500f);
            animationY.setDuration(7000);
            animationY.start();

            ObjectAnimator animationX = ObjectAnimator.ofFloat(imageView, "translationX", 500f);
            animationX.setStartDelay(7000);
            animationX.setDuration(7000);
            animationX.start();
        }
    }

    public void createWhitePiece(){
        imageView = new ImageView(this); // initialize ImageView
        lp = new RelativeLayout.LayoutParams(80, 80);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT); // A position in layout.
        imageView.setLayoutParams(lp);
        imageView.setImageResource(R.drawable.peca_brancaa);
        RelativeLayout layout = findViewById(R.id.process_RelativeLayout);
        layout.addView(imageView);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ObjectAnimator animationY = ObjectAnimator.ofFloat(imageView, "translationY", -500f);
            animationY.setDuration(7000);
            animationY.start();

            ObjectAnimator animationX = ObjectAnimator.ofFloat(imageView, "translationX", -500f);
            animationX.setStartDelay(7000);
            animationX.setDuration(7000);
            animationX.start();
        }
    }

    public void createGrayPiece(){
        imageView = new ImageView(this); // initialize ImageView
        lp = new RelativeLayout.LayoutParams(80, 80);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT); // A position in layout.
        imageView.setLayoutParams(lp);
        imageView.setImageResource(R.drawable.peca_cinzaa);
        RelativeLayout layout = findViewById(R.id.process_RelativeLayout);
        layout.addView(imageView);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ObjectAnimator animationY = ObjectAnimator.ofFloat(imageView, "translationY", -50f);
            animationY.setDuration(7000);
            animationY.start();

            ObjectAnimator animationX = ObjectAnimator.ofFloat(imageView, "translationX", -50f);
            animationX.setStartDelay(7000);
            animationX.setDuration(7000);
            animationX.start();
        }
    }

    public void moveCylinder(String letter){
        if(letter.equals("A")){
            Animation animation = AnimationUtils.loadAnimation(ProcessActivity.this, R.anim.righttoleft);
            animation.setDuration(3300);
            //animation.setStartTime();
            cylinderA.startAnimation(animation);
        }else if (letter.equals("B")){
            Animation animation = AnimationUtils.loadAnimation(ProcessActivity.this, R.anim.righttoleft);
            animation.setDuration(3300);
            cylinderB.startAnimation(animation);
        }else{
            Animation animation = AnimationUtils.loadAnimation(ProcessActivity.this, R.anim.righttoleft);
            animation.setDuration(3300);
            cylinderC.startAnimation(animation);
        }
    }

    /*---------------------- Aux methods -------------*/

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

    //Messages
    public void showMessage(String message){
        mTxt = findViewById(R.id.textView_test);
        mTxt.setText(message);
    }

    public void delayMessage(int delay, final String message){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showMessage(message);
            }
        }, delay);
    }
}
