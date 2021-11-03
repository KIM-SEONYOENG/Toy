package com.example.toy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Random;

public class MainLanderActivity extends AppCompatActivity {
    String[] name = new String[5];
    boolean[][] lander = new boolean[10][5];
    int n;
    int touch=0;

    private Background background;
    private ConstraintLayout canvasContainer;
    private ImageView imgView;
    private LinearLayout.LayoutParams layoutParams;

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lander);

        findId();
        setName();

        canvasContainer.addView(background);

        initLander();

        Random random = new Random();
        n = random.nextInt(5)+1;
        setImg(n);
        Log.d("당첨자는", String.valueOf(n));

        canvasContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                touch++;
                if(touch==6)
                    touch=0;
                background.invalidate();
            }
        });

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                touch=1;
                background.invalidate();
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                touch=2;
                background.invalidate();
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                touch=3;
                background.invalidate();
            }
        });
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                touch=4;
                background.invalidate();
            }
        });
        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                touch=5;
                background.invalidate();
            }
        });

    }

    private void findId() {
        background = new Background(this);
        canvasContainer = (ConstraintLayout) findViewById(R.id.back);
        imgView = (ImageView) findViewById(R.id.image);
        layoutParams = (LinearLayout.LayoutParams) imgView.getLayoutParams();
        tv1 = (TextView)findViewById(R.id.textView);
        tv2 = (TextView)findViewById(R.id.textView2);
        tv3 = (TextView)findViewById(R.id.textView3);
        tv4 = (TextView) findViewById(R.id.textView4);
        tv5 = (TextView) findViewById(R.id.textView5);
    }

    private void setName() {
        Intent intent = getIntent();
        name = intent.getStringArrayExtra("name");

        tv1.setText(name[0]);
        tv2.setText(name[1]);
        tv3.setText(name[2]);
        tv4.setText(name[3]);
        tv5.setText(name[4]);
    }

    public void setImg(int n) {
        int v = 0;
        switch (n) {
            case 1:
                v = 100;
                break;
            case 2:
                v = 240;
                break;
            case 3:
                v = 450;
                break;
            case 4:
                v = 680;
                break;
            case 5:
                v = 880;
        }

        layoutParams.leftMargin = v;
        imgView.setLayoutParams(layoutParams);
    }

    public void initLander() {
        for(int i=0; i<10; i++) {
            for (int j = 0; j < 5; j++) {
                Random rand = new Random();
                if (rand.nextInt(100) % 4 == 0 && j != 4) {
                    lander[i][j] = true;
                    j++;
                }
            }
        }
    }


    class Background extends View {
        public Background(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Paint basic = new Paint();
            basic.setStrokeWidth(10);
            basic.setColor(Color.BLACK);
            canvas.drawLine(110, 150, 110, 1500, basic);
            canvas.drawLine(330, 150, 330, 1500, basic);
            canvas.drawLine(550, 150, 550, 1500, basic);
            canvas.drawLine(770, 150, 770, 1500, basic);
            canvas.drawLine(990, 150, 990, 1500, basic);

            setLine(110, 250, 0, canvas, basic);
            setLine(330, 250, 1, canvas, basic);
            setLine(550, 250, 2, canvas, basic);
            setLine(770, 250, 3, canvas, basic);
            setLine(990, 250, 4, canvas, basic);

            Paint hLight = new Paint();
            hLight.setStrokeWidth(10);
            hLight.setColor(Color.RED);
            switch (touch) {
                case 1 :
                    canvas.drawLine(110, 150, 110, 250, hLight);
                    setLine(110, 250, 0, canvas, hLight);
                    break;
                case 2 :
                    canvas.drawLine(330, 150, 330, 250, hLight);
                    setLine(330, 250, 1, canvas, hLight);
                    break;
                case 3 :
                    canvas.drawLine(550, 150, 550, 250, hLight);
                    setLine(550, 250, 2, canvas, hLight);
                    break;
                case 4 :
                    canvas.drawLine(770, 150, 770, 250, hLight);
                    setLine(770, 250, 3, canvas, hLight);
                    break;
                case 5 :
                    canvas.drawLine(990, 150, 990, 250, hLight);
                    setLine(990, 250, 4, canvas, hLight);
            }
        }

        public void setLine(int x, int y, int n, Canvas canvas, Paint paint) {
            int i = 0;
            int j = n;

            while (i<10) {
                if(j!=0 && lander[i][j-1]) {
                    canvas.drawLine(x, y, x-220, y, paint);
                    x-=220;
                    j--;
                }
                else if(lander[i][j]) {
                    canvas.drawLine(x, y, x+220, y, paint);
                    x+=220;
                    j++;
                }
                canvas.drawLine(x,y, x, y+120, paint);
                y+=125;
                i++;
            }
        }
    }
}




























