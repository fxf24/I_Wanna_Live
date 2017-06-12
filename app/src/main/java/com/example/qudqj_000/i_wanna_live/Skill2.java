package com.example.qudqj_000.i_wanna_live;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by qudqj_000 on 2017-06-10.
 */

public class Skill2 {
    public int x, y ;
    private int dy;
    Bitmap skill2;
    public int sw, sh;


    public Skill2(int x, Context context){
        this.x = x;
        y = 0;
        dy = 15;
        skill2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.anivia_q);
        int dw = skill2.getWidth() * 2;
        int dh = skill2.getHeight() * 2;
        skill2 = Bitmap.createScaledBitmap(skill2, dw, dh, false);
        sw = skill2.getWidth()/2;
        sh = skill2.getHeight()/2;

    }

    public boolean Move(){
        y += dy;

        if(y>GameView.height)
            return true;
        else
            return false;
    }
}
