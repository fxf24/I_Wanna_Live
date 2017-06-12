package com.example.qudqj_000.i_wanna_live;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by qudqj_000 on 2017-06-12.
 */

public class Skill5 {
    public int x, y ;
    private int dy;
    Bitmap skill5;
    public int sw, sh;


    public Skill5(int x, Context context){
        this.x = x;
        y = 0;
        dy = 35;
        skill5 = BitmapFactory.decodeResource(context.getResources(),R.drawable.sivir_q);
        sw = skill5.getWidth()/2;
        sh = skill5.getHeight()/2;
    }

    public boolean Move(){
        y += dy;

        if(y>GameView.height)
            return true;
        else
            return false;
    }
}
