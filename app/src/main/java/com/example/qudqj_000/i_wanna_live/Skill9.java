package com.example.qudqj_000.i_wanna_live;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by qudqj_000 on 2017-06-12.
 */

public class Skill9 {
    public int x, y ;
    private int dy;
    Bitmap skill9;
    public int sw, sh;


    public Skill9(int x, Context context){
        this.x = x;
        y = 0;
        dy = 40;
        skill9 = BitmapFactory.decodeResource(context.getResources(),R.drawable.cait_q);
        sw = skill9.getWidth()/2;
        sh = skill9.getHeight()/2;
    }

    public boolean Move(){
        y += dy;

        if(y>GameView.height)
            return true;
        else
            return false;
    }
}
