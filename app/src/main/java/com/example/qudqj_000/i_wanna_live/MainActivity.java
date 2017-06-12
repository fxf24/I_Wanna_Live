package com.example.qudqj_000.i_wanna_live;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    LinearLayout home;
    GameView gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameView = (GameView)findViewById(R.id.gameView);
        gameView.PauseGame();
        home = (LinearLayout)findViewById(R.id.home_Menu);
//        program();
    }

    public void onClick(View v){
        if(v.getId() == R.id.start){
            gameView.ResumeGame();
            home.setVisibility(View.INVISIBLE);
            gameView.setVisibility(View.VISIBLE);
            gameView.setOnDeadListener(new GameView.OnDeadListener() {
                @Override
                public void youDied(final String score) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext() ,score,Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
        if(v.getId() == R.id.ranking){

        }
    }

    Handler handler = new Handler();
}

