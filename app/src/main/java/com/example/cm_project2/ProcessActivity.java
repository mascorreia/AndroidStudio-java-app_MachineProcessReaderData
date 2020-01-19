package com.example.cm_project2;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
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
    Button btnStatistics;
    TextView mTxt;
    RelativeLayout.LayoutParams lp;
    ImageView imageView;
    final Handler handler = new Handler();
    String s2_value;
    String s3_value;
    String s1_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);

        //Initialize database
        myDb = new DatabaseHelper(this);

        customView = findViewById(R.id.customView);

        //Cylinders
        cylinderA = findViewById(R.id.cylinderA);
        cylinderB = findViewById(R.id.cylinderB);
        cylinderC = findViewById(R.id.cylinderC);
        btnStatistics = findViewById(R.id.btnStatistics);

        //Start Animation
        processAnimation();

        goStatistics();
    }

    public void goStatistics() {
        btnStatistics.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ProcessActivity.this, StatisticsActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }

    @SuppressWarnings("SpellCheckingInspection")
    public void processAnimation() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < myDb.countLines(); i++) { //3 ou myDb.countLines()
                    for (int j = 1; j < 18; j++) {
                        String col_name = getColumn(j, i);
                        String col_value = getSensorData(j, i);
                        switch (col_name) {
                            case "S1":
                                //S1 ou SI: Se existe uma peça presente no tapete na posição ninicial: bit a 1, caso contrário:nbit a 0
                                if (col_value.equals("1")) {
                                    s1_value = "1";
                                    delayMessage(0, "Encontra-se uma peça na posição inicial!");
                                }
                                break;
                            case "S2":
                                //S2 ou SM: se a peça é de metal: bit a 1, caso contrário: bit a 0
                                if (col_value.equals("1")) {
                                    delayMessage(3000, "A peça é de metal!");
                                    createPiece("metal");
                                }
                                break;
                            case "S3":
                                //S3: Se a peça é branca: bit a 1, caso contrário: bit a 0
                                if (col_value.equals("1")) {
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            delayMessage(10000, "A peça é branca!");
                                            createPiece("white");
                                        }
                                    }, 10000);
                                    //customView.swapColorGreen();
                                }/* else {
                                    s3_value = "0";
                                    //Log.d("TAG", "S2 " + s2_value + " = " + " S3 ?" + s3_value.equals(s2_value));
                                    if (s1_value.equals("1") && s3_value.equals(s2_value)) {
                                        //createPiece("black");
                                    }
                                }*/
                                break;
                            case "S4":
                                //S4 ou SA: deteta se a peça foi arrumada (avanço) com sucesso pelo cilindro A e este voltou à posição original (recuo): bit a 1,caso contrário: bit a 0
                                if (col_value.equals("1")) {
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            movePiece("metal", true, true);
                                            moveCylinder("A");
                                            delayMessage(9000, "A peça foi arrumada com sucesso pelo Cilindro A!");
                                        }
                                    }, 4000);
                                }
                                break;
                            case "S5":
                                //S5 ou SB: deteta se a peça foi arrumada (avanço) com sucesso pelo cilindro B e este voltou à posição original (recuo): bit a 1, caso contrário: bit a 0
                                if (col_value.equals("1")) {
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            movePiece("white", true, true);
                                            moveCylinder("B");
                                            delayMessage(10000, "A peça foi arrumada com sucesso pelo Cilindro B!");
                                        }
                                    }, 10000);
                                }
                                break;
                            case "S6":
                                //S6 ou SC: deteta se a peça foi arrumada (avanço) com sucesso pelo cilindro C e este voltou à posição original (recuo): bit a 1, caso contrário: bit a 0
                                if (col_value.equals("1")) {
                                    movePiece("black", true, true);
                                    moveCylinder("C");
                                    //delayMessage(8000,"A peça foi arrumada com sucesso pelo Cilindro C!");
                                }
                                break;
                            case "S7":
                                //Do Something

                                break;
                            case "S8":
                                //Do Something
                                break;
                            case "C1":
                                //C1 (HL1): Estado Laranja (ligado: bit a 1, desligado: bit a 0)
                                if (col_value.equals("1")) {
                                    customView.swapColorOrange();
                                }
                                break;
                            case "C2":
                                //C2 (HL2): Estado Verde (ligado: bit a 1, desligado: bit a 0)
                                if (col_value.equals("1")) {
                                    customView.swapColorGreen();
                                }
                                break;
                            case "C3":
                                //C3 (HL3): Estado Vermelho (ligado: bit a 1, desligado: bit a 0)
                                if (col_value.equals("1")) {
                                    customView.swapColorRed();
                                }
                                break;
                            case "C4":
                                //C4 (SB1): Ordem de Execução Start (quando pressionado: bit a 1, caso contrário bit a 0)
                                if (col_value.equals("1")) {
                                    customView.swapColorStart();
                                }
                                break;
                            case "C5":
                                //C5 (SB2): Ordem de Execução Stop (quando pressionado: bit a 1, caso contrário bit a 0)
                                if (col_value.equals("1")) {
                                    customView.swapColorStop();
                                }
                                break;
                            case "C6":
                                //C6 (SA): Modo de Seleção (bit a 0) ou Modo de Execução (bit a 1)
                                if (col_value.equals("1")) {
                                    customView.swapColorSelect();
                                }
                                break;
                            case "C7":
                                //C7 (QS): Emergência (quando pressionado: bit a 1, caso contrário bit a 0)
                                if (col_value.equals("1")) {
                                    customView.swapColorEmer();
                                }
                                break;
                            case "C8":
                                //Componente não existente com bit sempre a 0
                                break;
                            case "C9":
                                //Do Something
                                break;
                            case "DATE":
                                //Do Something
                                break;
                        }
                    }
                }
            }
        }, 3000);
    }

    /*------- Create, move pieces and move cylinder --------*/

    public void createPiece(String color) {
        if (color.equals("white")) {
            imageView = new ImageView(this); // initialize ImageView
            lp = new RelativeLayout.LayoutParams(80, 80);
            lp.setMargins(800, 1850, 0, 0);
            imageView.setLayoutParams(lp);
            imageView.setImageResource(R.drawable.peca_brancaa);
            RelativeLayout layout = findViewById(R.id.process_RelativeLayout);
            layout.addView(imageView);


        } else if (color.equals("metal")) {
            imageView = new ImageView(this); // initialize ImageView
            lp = new RelativeLayout.LayoutParams(80, 80);
            lp.setMargins(800, 1850, 0, 0);
            imageView.setLayoutParams(lp);
            imageView.setImageResource(R.drawable.peca_cinzaa);
            RelativeLayout layout = findViewById(R.id.process_RelativeLayout);
            layout.addView(imageView);

        } else {
            imageView = new ImageView(this); // initialize ImageView
            lp = new RelativeLayout.LayoutParams(80, 80);
            lp.setMargins(800, 1850, 0, 0);
            imageView.setLayoutParams(lp);
            imageView.setImageResource(R.drawable.peca_pretaa);
            RelativeLayout layout = findViewById(R.id.process_RelativeLayout);
            layout.addView(imageView);
        }
    }

    public void movePiece(String color, boolean moveY, boolean moveX) {
        if (color.equals("metal")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (moveY) {
                    ObjectAnimator animationY = ObjectAnimator.ofFloat(imageView, "translationY", -380f);
                    animationY.setDuration(4000);
                    animationY.start();
                }
                if (moveX) {
                    ObjectAnimator animationX = ObjectAnimator.ofFloat(imageView, "translationX", -595f);
                    animationX.setStartDelay(5000);
                    animationX.setDuration(4000);
                    animationX.start();
                }
            }
        } else if (color.equals("white")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (moveY) {
                    ObjectAnimator animationY = ObjectAnimator.ofFloat(imageView, "translationY", -743f);
                    animationY.setDuration(4000);
                    animationY.start();
                }
                if (moveX) {
                    ObjectAnimator animationX = ObjectAnimator.ofFloat(imageView, "translationX", -595f);
                    animationX.setStartDelay(4000);
                    animationX.setDuration(4000);
                    animationX.start();
                }
            }
        } else if (color.equals("black")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (moveY) {
                    ObjectAnimator animationY = ObjectAnimator.ofFloat(imageView, "translationY", -1090f);
                    animationY.setDuration(7000);
                    animationY.start();
                }
                if (moveX) {
                    ObjectAnimator animationX = ObjectAnimator.ofFloat(imageView, "translationX", -595f);
                    animationX.setStartDelay(7000);
                    animationX.setDuration(7000);
                    animationX.start();
                }
            }
        } else {
            //Do nothing
        }
    }

    public void moveCylinder(String letter) {
        if (letter.equals("A")) {
            Animation animation = AnimationUtils.loadAnimation(ProcessActivity.this, R.anim.righttoleft_a);
            animation.setDuration(5000);
            cylinderA.startAnimation(animation);
        } else if (letter.equals("B")) {
            Animation animation = AnimationUtils.loadAnimation(ProcessActivity.this, R.anim.righttoleft_b);
            animation.setDuration(3300);
            cylinderB.startAnimation(animation);
        } else {
            Animation animation = AnimationUtils.loadAnimation(ProcessActivity.this, R.anim.righttoleft_c);
            animation.setDuration(3300);
            cylinderC.startAnimation(animation);
        }
    }

    /*---------------------- Aux methods -------------*/

    public String getSensorData(int column, int line) {
        Cursor res = myDb.getAllData(line);
        if (res.getCount() == 0) {
            //show message;
            Toast.makeText(ProcessActivity.this, "Erro! Não foram encontrados dados!", Toast.LENGTH_LONG).show();
        }

        StringBuilder buffer = new StringBuilder();
        while (res.moveToNext()) {
            buffer.append(res.getString(column));
        }
        //Show all data
        return buffer.toString();
    }

    public String getColumn(int column, int line) {
        Cursor res = myDb.getAllData(line);
        StringBuilder buffer = new StringBuilder();
        while (res.moveToNext()) {
            buffer.append(res.getColumnName(column));
        }
        return buffer.toString();
    }

    public void showMessage(String message) {
        mTxt = findViewById(R.id.textView_test);
        mTxt.setText(message);
    }

    public void delayMessage(int delay, final String message) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showMessage(message);
            }
        }, delay);
    }
}
