package com.example.qudqj_000.i_wanna_live;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by qudqj_000 on 2017-06-07.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    GameThread gameThread;
    public static SurfaceHolder holder;
    public static Context mContext;
    Bitmap background, minion;
    int mw, mh;
    float mX = 0, mY ;
    ArrayList<Skill1> skill1s;
    ArrayList<Skill2> skill2s;
    Skill1 skill1;
    Skill2 skill2;

    public static int width, height;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        holder = surfaceHolder;
        mContext = context;
        gameThread = new GameThread(context, surfaceHolder);

        Display display = ((WindowManager)context.getSystemService(context.WINDOW_SERVICE)).getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();

        Resources res = context.getResources();

        background = BitmapFactory.decodeResource(res, R.drawable.background);
        background = Bitmap.createScaledBitmap(background, width, height, false);

        minion = BitmapFactory.decodeResource(res, R.drawable.minion);
        mw = minion.getWidth()/3;
        mh = minion.getHeight()/3;
        minion = Bitmap.createScaledBitmap(minion,mw,mh,false);
        mY = height-(mh+mh/2);

        skill1 = new Skill1(0,context);
        skill2 = new Skill2(0,context);

        skill1s = new ArrayList<>();
        skill2s = new ArrayList<>();
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gameThread.setDaemon(true);
        gameThread.start();
//        skillThread.setDaemon(true);
//        skillThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        RestartGame();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        StopGame();
    }

    public void StopGame(){
        gameThread.StopThread();
    }

    public void PauseGame(){
        gameThread.PauseNResume(true);
    }

    public void ResumeGame(){
        gameThread.PauseNResume(false);
    }

    public void RestartGame(){
        gameThread.StopThread();

        gameThread = null;
        gameThread = new GameThread(mContext, holder);
        gameThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float dx = event.getX();

        if(event.getAction() == MotionEvent.ACTION_DOWN){
//            if(event.getX()>=mX && event.getX()<=(mX+mw)){
//                mX = dx - mw/2;
//                if(mX<0)
//                    mX = 0;
//                if(mX>width-mw)
//                    mX = width - mw;
//            }
        }
        else if(event.getAction() == MotionEvent.ACTION_MOVE){
            if(event.getX()>=mX && event.getX()<=(mX+mw)){
                mX = dx - mw/2;
                if(mX<0)
                    mX = 0;
                if(mX>width-mw)
                    mX = width - mw;
            }
        }
        else if(event.getAction() == MotionEvent.ACTION_UP){

        }

        return true;
    }

    class GameThread extends Thread{
        boolean canRun = true;
        boolean isWait = false;
        int time_count;
        int skill1_count = -1;
        int skill2_count = -1;
        Context context;
        int score = 0;
        Paint paint;

        public GameThread(Context context, SurfaceHolder surfaceHolder){
            this.context = context;
            paint = new Paint();
            paint.setColor(Color.WHITE);
            paint.setAntiAlias(true);
            paint.setTextSize(50);
            paint.setTypeface(Typeface.create("", Typeface.BOLD));
        }

        public void moveAll(){}

        private void drawAll(Canvas canvas){
            if(canvas != null) {
                canvas.drawBitmap(background, 0, 0, null);
            }
            if(canvas != null)
                canvas.drawBitmap(minion, mX, mY, null);
        }

        private void drawScore(Canvas canvas){
            if(canvas !=null)
                canvas.drawText("SCORE : " + String.valueOf(score), width/4, height*5/100, paint);
        }
        void createSkill1(int count){
            switch(count){
                case 0:
                    skill1s.add(new Skill1(0, mContext));
                    break;
                case 1:
                    skill1s.add(new Skill1(width/2 - skill1.sw, mContext));
                    break;
                case 2:
                    skill1s.add(new Skill1(width - skill1.sw*2, mContext));
                    break;
                case 3:
                    skill1s.add(new Skill1(width*3/4 - skill1.sw, mContext));
                    break;
                case 4:
                    skill1s.add(new Skill1(width/4, mContext));
                    break;
            }
        }

        void createSkill2(int count){
            switch(count){
                case 0:
                    skill2s.add(new Skill2(width/3 - skill2.sw, mContext));
                    break;
                case 1:
                    skill2s.add(new Skill2(width*2/3 - skill2.sw, mContext));
                    break;
            }
        }

        @Override
        public void run() {
            Canvas canvas;
            while(canRun){
                canvas = holder.lockCanvas();

                try{
                    synchronized (holder){
                        time_count++;
                        score++;
                        moveAll();
                        drawAll(canvas);
                        drawSkill1(canvas);
                        drawSkill2(canvas);
                        drawScore(canvas);

                        if(time_count%33==0){
                            skill1_count++;
                            if(skill1_count>4)
                                skill1_count = 0;
                            createSkill1(skill1_count);

                        }

                        if(time_count>=330){
//                            if(time_count <=330) {
//                                isWait = true;
//                                sleep(2000);
//                                isWait = false;
//                            }
                            if(time_count%36==0){
                                skill2_count++;
                                if(skill2_count>1)
                                    skill2_count = 0;
                                createSkill2(skill2_count);
                            }
                        }
                        EndOfTheGame();
                    }
                }
//                catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                finally {
                    if(canvas !=null)
                        holder.unlockCanvasAndPost(canvas);
                }

                synchronized (this){
                    if(isWait){
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }

        private void drawSkill1(Canvas canvas){
            for(int i = skill1s.size()-1; i>=0; i--){
                if(skill1s.get(i).Move())
                    skill1s.remove(i);
            }

            for(Skill1 sk1: skill1s){
                if(canvas != null)
                    canvas.drawBitmap(sk1.skill1, sk1.x,sk1.y,null);
            }
        }

        private void drawSkill2(Canvas canvas){
            for(int i = skill2s.size()-1; i>=0; i--){
                if(skill2s.get(i).Move())
                    skill2s.remove(i);
            }

            for(Skill2 sk2: skill2s){
                if(canvas != null)
                    canvas.drawBitmap(sk2.skill2, sk2.x,sk2.y,null);
            }
        }

        private void EndOfTheGame(){
            for(Skill1 sk1:skill1s){
                if(Math.abs((mX + mw/2)-(sk1.x + sk1.sw/2))<mw/2 && Math.abs((mY + mh/2)-(sk1.y + sk1.sh/2))<mh/2){
                    StopThread();
                }
            }
            for(Skill2 sk2: skill2s){
                if(Math.abs((mX + mw/2)-(sk2.x + sk2.sw/2))<mw/2 && Math.abs((mY + mh/2)-(sk2.y + sk2.sh/2))<mh/2){
                    StopThread();
                }
            }
        }

        private void StopThread(){
            canRun = false;
            synchronized (this){
                this.notify();
            }
        }

        private void PauseNResume(boolean pr){
            isWait = pr;
            synchronized (this){
                this.notify();
            }
        }
    }
}
