package com.example.qudqj_000.i_wanna_live;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by qudqj_000 on 2017-06-12.
 */

public class Skill4 {
    public int x, y ;
    private int dy;
    Bitmap skill4;
    public int sw, sh;


    public Skill4(int x, Context context){
        this.x = x;
        y = 0;
        dy = 30;
        skill4 = BitmapFactory.decodeResource(context.getResources(),R.drawable.pant_q);
        sw = skill4.getWidth()/2;
        sh = skill4.getHeight()/2;
    }

    public boolean Move(){
        y += dy;

        if(y>GameView.height)
            return true;
        else
            return false;
    }
}
