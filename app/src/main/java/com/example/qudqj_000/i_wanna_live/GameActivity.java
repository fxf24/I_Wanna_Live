package com.example.qudqj_000.i_wanna_live;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GameActivity extends AppCompatActivity {
    GameView gameView;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameView = (GameView)findViewById(R.id.gameView);
        intent = getIntent();

        gameView.setOnDeadListener(new GameView.OnDeadListener() {
            @Override
            public void youDied(String score) {
                intent.putExtra("score", score);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
