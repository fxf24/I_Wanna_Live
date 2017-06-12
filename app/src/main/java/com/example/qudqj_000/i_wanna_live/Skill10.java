package com.example.qudqj_000.i_wanna_live;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by qudqj_000 on 2017-06-12.
 */

public class Skill10 {
    public int x, y ;
    private int dy;
    Bitmap skill10;
    public int sw, sh;


    public Skill10(int x, Context context){
        this.x = x;
        y = 0;
        dy = 50;
        skill10 = BitmapFactory.decodeResource(context.getResources(),R.drawable.ez_ult);
        int dw = skill10.getWidth()*2;
        int dh = skill10.getHeight();
        skill10 = Bitmap.createScaledBitmap(skill10, dw, dh, false);

        sw = skill10.getWidth()/2;
        sh = skill10.getHeight()/2;
    }

    public boolean Move(){
        y += dy;

        if(y>GameView.height)
            return true;
        else
            return false;
    }
}
