package com.example.qudqj_000.i_wanna_live;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Random;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    ImageView iv1, img, img2;
    float x=0, y=0;
    RelativeLayout rl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        program();
    }

    void program(){
        rl = (RelativeLayout)findViewById(R.id.rl);
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
                        if(x<=1){
                            x = 0;
                        }
                        iv1.setX(x);
                        break;
                    case MotionEvent.ACTION_UP:
//                        Toast.makeText(getApplicationContext(), iv1.getX()+", "+iv1.getY(), Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

        skills();
    }

    void skills(){
        img = (ImageView)findViewById(R.id.skill);
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        img2 = (ImageView) inflater.inflate(R.layout.skill1, rl, false);
        float imx = getPhoneWidth()/2-((float)img2.getWidth()/2);
        img2.setX(imx);

        rl.addView(img2);

        td.setDaemon(true);
        td.start();
    }

    Thread td = new Thread(){
        @Override
        public void run() {
            while(true) {
                y += 1;
                try {
                    Thread.sleep(1);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            img.setY(y);
                            img2.setY(y-500);

                            if(y>=getPhoneHeight()-319){
//                                img.destroyDrawingCache();
                                y = 0;
                            }
                            endOfTheGame(img);
                            endOfTheGame(img2);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    void endOfTheGame(ImageView img){
        float xNow = iv1.getX();
        float yNow = iv1.getY();

        float missileX = img.getX();
        float missileY = img.getY();

        if(missileX<=xNow && (missileX+img.getWidth())>xNow){
            if(missileY<=yNow && (missileY+img.getWidth())>yNow){
                Toast.makeText(getApplicationContext(), String.valueOf(missileX + img.getWidth()), Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    Handler handler = new Handler();

    public float getPhoneWidth(){
        return ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
    }

    public float getPhoneHeight(){
        return ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getHeight();
    }

}

