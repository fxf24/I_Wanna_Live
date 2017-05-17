package com.example.qudqj_000.i_wanna_live;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView iv1;
    float x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv1 = (ImageView)findViewById(R.id.minion);

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
                }
                return true;
            }
        });
    }

    public float getPhoneWidth(){
        return ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
    }
}
