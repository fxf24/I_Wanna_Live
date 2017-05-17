package com.example.qudqj_000.i_wanna_live;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView iv1, img;
    float x=0, y=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        program();
    }

    void program(){
        iv1 = (ImageView)findViewById(R.id.minion);
        iv1.setY(getPhoneHeight()-320);

        iv1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        x=iv1.getX();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        x=event.getX()+x;
                        if(x>=getPhoneWidth()-118){
                            x = getPhoneWidth()-118;
                        }
                        iv1.setX(x);
                        break;
                    case MotionEvent.ACTION_UP:
                        Toast.makeText(getApplicationContext(), iv1.getX()+", "+iv1.getY(), Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

        randomImageView();
    }

    void randomImageView(){
        Random random = new Random();
        int r= random.nextInt(100);

        img = (ImageView)findViewById(R.id.skill);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    y += 1;
                    try {
                        Thread.sleep(1);
                        handler.sendEmptyMessage(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    void endOfTheGame(){
        if(iv1.getX()==img.getX() && iv1.getY()==img.getY()){
            finish();
        }
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0){
                img.setY(y);

                if(y>=getPhoneHeight()-319){
                    y=0;
                }
                endOfTheGame();
            }
        }
    };

    public float getPhoneWidth(){
        return ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
    }

    public float getPhoneHeight(){
        return ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getHeight();
    }

}

