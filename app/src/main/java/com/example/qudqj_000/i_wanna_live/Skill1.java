package com.example.qudqj_000.i_wanna_live;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by qudqj_000 on 2017-06-07.
 */


public class Skill1 {
    public int x, y ;
    private int dy;
    Bitmap skill1;
    public int sw, sh;


    public Skill1(int x, Context context){
        this.x = x;
        y = 0;
        dy = 15;
        skill1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.ez_q);
        sw = skill1.getWidth()/2;
        sh = skill1.getHeight()/2;
    }

    public boolean Move(){
        y += dy;

        if(y>GameView.height)
           return true;
        else
           return false;
    }
}
