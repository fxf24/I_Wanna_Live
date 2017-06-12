package com.example.qudqj_000.i_wanna_live;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by qudqj_000 on 2017-06-12.
 */

public class Skill8 {
    public int x, y ;
    private int dy;
    Bitmap skill8;
    public int sw, sh;


    public Skill8(int x, Context context){
        this.x = x;
        y = 0;
        dy = 10;
        skill8 = BitmapFactory.decodeResource(context.getResources(),R.drawable.eco_q);
        int dw = skill8.getWidth()*2;
        int dh = skill8.getHeight()*2;
        skill8 = Bitmap.createScaledBitmap(skill8, dw, dh, false);
        sw = skill8.getWidth()/2;
        sh = skill8.getHeight()/2;
    }

    public boolean Move(){
        y += dy;

        if(y>GameView.height)
            return true;
        else
            return false;
    }
}
