package com.example.qudqj_000.i_wanna_live;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by qudqj_000 on 2017-06-12.
 */

public class Skill7 {
    public int x, y ;
    private int dy;
    Bitmap skill7;
    public int sw, sh;


    public Skill7(int x, Context context){
        this.x = x;
        y = GameView.height;
        dy = 30;
        skill7 = BitmapFactory.decodeResource(context.getResources(),R.drawable.ahri_q);
        int dw = skill7.getWidth()*2;
        int dh = skill7.getHeight()*2;
        skill7 = Bitmap.createScaledBitmap(skill7, dw, dh, false);
        sw = skill7.getWidth()/2;
        sh = skill7.getHeight()/2;
    }

    public boolean Move(){
        y -= dy;

        if(y<0)
            return true;
        else
            return false;
    }
}
