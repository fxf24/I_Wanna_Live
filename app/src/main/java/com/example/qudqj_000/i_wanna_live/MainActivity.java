package com.example.qudqj_000.i_wanna_live;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;


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
        }
    }
}

