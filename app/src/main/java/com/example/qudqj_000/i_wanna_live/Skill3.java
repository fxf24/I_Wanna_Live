package com.example.qudqj_000.i_wanna_live;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by qudqj_000 on 2017-06-12.
 */

public class Skill3{
    public int x, y ;
    private int dy;
    Bitmap skill3;
    public int sw, sh;


    public Skill3(int x, Context context){
        this.x = x;
        y = 0;
        dy = 25;
        skill3 = BitmapFactory.decodeResource(context.getResources(),R.drawable.varus_q);
        sw = skill3.getWidth()/2;
        sh = skill3.getHeight()/2;

    }

    public boolean Move(){
        y += dy;

        if(y>GameView.height)
            return true;
        else
            return false;
    }
}
