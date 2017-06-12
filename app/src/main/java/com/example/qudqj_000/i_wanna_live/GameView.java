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

import java.util.ArrayList;

/**
 * Created by qudqj_000 on 2017-06-07.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    GameThread gameThread;
    public SurfaceHolder holder;
    public Context mContext;
    Bitmap background, minion;
    int mw, mh;
    float mX = 0, mY;
    String scoreTxt;

    ArrayList<Skill1> skill1s;
    ArrayList<Skill2> skill2s;
    ArrayList<Skill3> skill3s;
    ArrayList<Skill4> skill4s;
    ArrayList<Skill5> skill5s;
    ArrayList<Skill6> skill6s;
    ArrayList<Skill7> skill7s;
    ArrayList<Skill8> skill8s;
    ArrayList<Skill9> skill9s;
    ArrayList<Skill10> skill10s;

    Skill1 skill1;
    Skill2 skill2;
    Skill3 skill3;
    Skill4 skill4;
    Skill5 skill5;
    Skill6 skill6;
    Skill7 skill7;
    Skill8 skill8;
    Skill9 skill9;
    Skill10 skill10;

    public static int width, height;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        holder = surfaceHolder;
        mContext = context;
        gameThread = new GameThread(context, surfaceHolder);

        Display display = ((WindowManager) context.getSystemService(context.WINDOW_SERVICE)).getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();

        Resources res = context.getResources();

        background = BitmapFactory.decodeResource(res, R.drawable.background);
        background = Bitmap.createScaledBitmap(background, width, height, false);

        minion = BitmapFactory.decodeResource(res, R.drawable.minion);
        mw = minion.getWidth() / 3;
        mh = minion.getHeight() / 3;
        minion = Bitmap.createScaledBitmap(minion, mw, mh, false);
        mY = height - (mh + mh / 2);

        skillSetting();
    }

    void skillSetting() {
        skill1 = new Skill1(0, mContext);
        skill2 = new Skill2(0, mContext);
        skill3 = new Skill3(0, mContext);
        skill4 = new Skill4(0, mContext);
        skill5 = new Skill5(0, mContext);
        skill6 = new Skill6(0, mContext);
        skill7 = new Skill7(0, mContext);
        skill8 = new Skill8(0, mContext);
        skill9 = new Skill9(0, mContext);
        skill10 = new Skill10(0, mContext);

        skill1s = new ArrayList<>();
        skill2s = new ArrayList<>();
        skill3s = new ArrayList<>();
        skill4s = new ArrayList<>();
        skill5s = new ArrayList<>();
        skill6s = new ArrayList<>();
        skill7s = new ArrayList<>();
        skill8s = new ArrayList<>();
        skill9s = new ArrayList<>();
        skill10s = new ArrayList<>();
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
//        gameThread.setDaemon(true);
        gameThread.start();
//        skillThread.setDaemon(true);
//        skillThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        StopGame();
    }

    public void StopGame() {
        gameThread.StopThread();
    }

    public void PauseGame() {
        gameThread.PauseNResume(true);
    }

    public void ResumeGame() {
        gameThread.PauseNResume(false);
    }

    public void RestartGame() {

        gameThread = null;
        gameThread = new GameThread(mContext, holder);
        gameThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float dx = event.getX();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            if(event.getX()>=mX && event.getX()<=(mX+mw)){
//                mX = dx - mw/2;
//                if(mX<0)
//                    mX = 0;
//                if(mX>width-mw)
//                    mX = width - mw;
//            }
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (event.getX() >= mX && event.getX() <= (mX + mw)) {
                mX = dx - mw / 2;
                if (mX < 0)
                    mX = 0;
                if (mX > width - mw)
                    mX = width - mw;
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {

        }

        return true;
    }

    class GameThread extends Thread {
        boolean canRun = true;
        boolean isWait = false;
        int time_count = 0;
        int skill1_count = -1;
        int skill2_count = -1;
        int skill3_count = -1;
        int skill4_count = -1;
        int skill5_count = -1;
        int skill6_count = -1;
        int skill7_count = -1;
        int skill8_count = -1;
        int skill9_count = -1;
        int skill10_count = -1;
        int stage = 1;

        Context context;
        int score = 0;
        Paint paint;
        Paint anti = new Paint();

        public GameThread(Context context, SurfaceHolder surfaceHolder) {
            this.context = context;
            paint = new Paint();
            paint.setColor(Color.WHITE);
            paint.setAntiAlias(true);
            paint.setTextSize(50);
            paint.setTypeface(Typeface.create("", Typeface.BOLD));
            anti.setAntiAlias(true);
        }

        private void StopThread() {
            canRun = false;
            onDeadListener.youDied(scoreTxt);
            synchronized (this) {
                this.notify();
            }
        }

        private void PauseNResume(boolean pr) {
            isWait = pr;
            synchronized (this) {
                this.notify();
            }
        }

        private void drawAll(Canvas canvas) {
            if (canvas != null) {
                canvas.drawBitmap(background, 0, 0, null);
            }
            if (canvas != null)
                canvas.drawBitmap(minion, mX, mY, null);
            drawScore(canvas);
            drawSkill1(canvas);
            drawSkill2(canvas);
            drawSkill3(canvas);
            drawSkill4(canvas);
            drawSkill5(canvas);
            drawSkill6(canvas);
            drawSkill7(canvas);
            drawSkill8(canvas);
            drawSkill9(canvas);
            drawSkill10(canvas);
        }


        @Override
        public void run() {
            Canvas canvas = null;
            while (canRun) {
                canvas = holder.lockCanvas();

                try {
                    synchronized (holder) {
                        time_count++;
                        drawAll(canvas);
                        skillShots();
                    }
                }
                finally {
                    if(canvas !=null)
                        holder.unlockCanvasAndPost(canvas);
                    EndOfTheGame();
                }

                synchronized (this) {
                    if (isWait) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        void skillShots(){
            if (time_count % 30 == 0) {
                skill1_count++;
                if (skill1_count > 4)
                    skill1_count = 0;
                createSkill1(skill1_count);

            }

            if (time_count >= 330) {
                stage = 2;
                if (time_count % 60 == 0) {
                    skill2_count++;
                    if (skill2_count > 1)
                        skill2_count = 0;
                    createSkill2(skill2_count);
                }
            }

            if (time_count >= 660) {
                stage =3;
                if (time_count % 30 == 0) {
                    skill3_count++;
                    if (skill3_count > 3)
                        skill3_count = 0;
                    createSkill3(skill3_count);
                }
            }

            if (time_count >= 990) {
                stage =4;
                if (time_count % 30 == 0) {
                    skill4_count++;
                    if (skill4_count > 1)
                        skill4_count = 0;
                    createSkill4(skill4_count);
                }
            }

            if (time_count >= 1320) {
                stage =5;
                if (time_count % 30 == 0) {
                    skill5_count++;
                    if (skill5_count > 2)
                        skill5_count = 0;
                    createSkill5(skill5_count);
                }
            }

            if (time_count >= 1650) {
                stage =6;
                if (time_count % 30 == 0) {
                    skill6_count++;
                    if (skill6_count > 4)
                        skill6_count = 0;
                    createSkill6(skill6_count);
                }
            }

            if (time_count >= 1980) {
                stage =7;
                if (time_count % 60 == 0) {
                    skill7_count++;
                    if (skill7_count > 2)
                        skill7_count = 0;
                    createSkill7(skill7_count);
                }
            }

            if (time_count >= 2310) {
                stage =8;
                if (time_count % 60 == 0) {
                    skill8_count++;
                    if (skill8_count > 3)
                        skill8_count = 0;
                    createSkill8(skill8_count);
                }
            }

            if (time_count >= 2670) {
                stage =9;
                if (time_count % 30 == 0) {
                    skill9_count++;
                    if (skill9_count > 3)
                        skill9_count = 0;
                    createSkill9(skill9_count);
                }
            }

            if (time_count >= 3000) {
                stage =10;
                if (time_count % 60 == 0) {
                    skill10_count++;
                    if (skill10_count > 1)
                        skill10_count = 0;
                    createSkill10(skill10_count);
                }
            }
        }
        private void drawScore(Canvas canvas) {
            if (canvas != null) {
                scoreTxt = String.valueOf(score) + "점";
                canvas.drawText("STAGE " + String.valueOf(stage), 0, height*5/100, paint);
                canvas.drawText("SCORE : " + String.valueOf(score), width / 4, height * 5 / 100, paint);
            }
        }

        private void drawSkill1(Canvas canvas) {
            for (int i = skill1s.size() - 1; i >= 0; i--) {
                if (skill1s.get(i).Move()) {
                    skill1s.remove(i);
                    score += 10;
                }
            }

            for (Skill1 sk1 : skill1s) {
                if (canvas != null)
                    canvas.drawBitmap(sk1.skill1, sk1.x, sk1.y, null);
            }
        }

        private void drawSkill2(Canvas canvas) {
            for (int i = skill2s.size() - 1; i >= 0; i--) {
                if (skill2s.get(i).Move()) {
                    skill2s.remove(i);
                    score += 20;
                }
            }

            for (Skill2 sk2 : skill2s) {
                if (canvas != null)
                    canvas.drawBitmap(sk2.skill2, sk2.x, sk2.y, null);
            }
        }

        private void drawSkill3(Canvas canvas) {
            for (int i = skill3s.size() - 1; i >= 0; i--) {
                if (skill3s.get(i).Move()) {
                    skill3s.remove(i);
                    score += 30;
                }
            }

            for (Skill3 sk3 : skill3s) {
                if (canvas != null)
                    canvas.drawBitmap(sk3.skill3, sk3.x, sk3.y, null);
            }
        }

        private void drawSkill4(Canvas canvas) {
            for (int i = skill4s.size() - 1; i >= 0; i--) {
                if (skill4s.get(i).Move()) {
                    skill4s.remove(i);
                    score += 40;
                }
            }

            for (Skill4 sk4 : skill4s) {
                if (canvas != null)
                    canvas.drawBitmap(sk4.skill4, sk4.x, sk4.y, null);
            }
        }

        private void drawSkill5(Canvas canvas) {
            for (int i = skill5s.size() - 1; i >= 0; i--) {
                if (skill5s.get(i).Move()) {
                    skill5s.remove(i);
                    score += 50;
                }
            }

            for (Skill5 sk5 : skill5s) {
                if (canvas != null)
                    canvas.drawBitmap(sk5.skill5, sk5.x, sk5.y, null);
            }
        }

        private void drawSkill6(Canvas canvas) {
            for (int i = skill6s.size() - 1; i >= 0; i--) {
                if (skill6s.get(i).Move()) {
                    skill6s.remove(i);
                    score += 60;
                }
            }

            for (Skill6 sk6 : skill6s) {
                if (canvas != null)
                    canvas.drawBitmap(sk6.skill6, sk6.x, sk6.y, null);
            }
        }

        private void drawSkill7(Canvas canvas) {
            for (int i = skill7s.size() - 1; i >= 0; i--) {
                if (skill7s.get(i).Move()) {
                    skill7s.remove(i);
                    score += 70;
                }
            }

            for (Skill7 sk : skill7s) {
                if (canvas != null)
                    canvas.drawBitmap(sk.skill7, sk.x, sk.y, anti);
            }
        }

        private void drawSkill8(Canvas canvas) {
            for (int i = skill8s.size() - 1; i >= 0; i--) {
                if (skill8s.get(i).Move()) {
                    skill8s.remove(i);
                    score += 80;
                }
            }

            for (Skill8 sk : skill8s) {
                if (canvas != null)
                    canvas.drawBitmap(sk.skill8, sk.x, sk.y, anti);
            }
        }

        private void drawSkill9(Canvas canvas) {
            for (int i = skill9s.size() - 1; i >= 0; i--) {
                if (skill9s.get(i).Move()) {
                    skill9s.remove(i);
                    score += 90;
                }
            }

            for (Skill9 sk : skill9s) {
                if (canvas != null)
                    canvas.drawBitmap(sk.skill9, sk.x, sk.y, null);
            }
        }

        private void drawSkill10(Canvas canvas) {
            for (int i = skill10s.size() - 1; i >= 0; i--) {
                if (skill10s.get(i).Move()) {
                    skill10s.remove(i);
                    score += 100;
                }
            }

            for (Skill10 sk : skill10s) {
                if (canvas != null)
                    canvas.drawBitmap(sk.skill10, sk.x, sk.y, null);
            }
        }

        void createSkill1(int count) {
            switch (count) {
                case 0:
                    skill1s.add(new Skill1(0, mContext));
                    break;
                case 1:
                    skill1s.add(new Skill1(width / 2 - skill1.sw, mContext));
                    break;
                case 2:
                    skill1s.add(new Skill1(width - skill1.sw * 2, mContext));
                    break;
                case 3:
                    skill1s.add(new Skill1(width * 3 / 4 - skill1.sw, mContext));
                    break;
                case 4:
                    skill1s.add(new Skill1(width / 4, mContext));
                    break;
            }
        }

        void createSkill2(int count) {
            switch (count) {
                case 0:
                    skill2s.add(new Skill2(width / 3 - skill2.sw, mContext));
                    break;
                case 1:
                    skill2s.add(new Skill2(width * 2 / 3 - skill2.sw, mContext));
                    break;
            }
        }

        void createSkill3(int count) {
            switch (count) {
                case 0:
                    skill3s.add(new Skill3(width / 5 - skill3.sw, mContext));
                    break;
                case 1:
                    skill3s.add(new Skill3(width * 2 / 5 - skill3.sw, mContext));
                    break;
                case 2:
                    skill3s.add(new Skill3(width * 3 / 5 - skill3.sw, mContext));
                    break;
                case 3:
                    skill3s.add(new Skill3(width * 4 / 5 - skill3.sw, mContext));
                    break;
            }
        }

        void createSkill4(int count) {
            switch (count) {
                case 0:
                    skill4s.add(new Skill4(width / 4 - skill4.sw, mContext));
                    break;
                case 1:
                    skill4s.add(new Skill4(width * 3 / 4 - skill4.sw, mContext));
                    break;
            }
        }

        void createSkill5(int count) {
            switch (count) {
                case 0:
                    skill5s.add(new Skill5(0, mContext));
                    break;
                case 1:
                    skill5s.add(new Skill5(width / 2 - skill5.sw, mContext));
                    break;
                case 2:
                    skill5s.add(new Skill5(width - skill5.sw * 2, mContext));
                    break;
            }
        }

        void createSkill6(int count) {
            switch (count) {
                case 0:
                    skill6s.add(new Skill6(width * 5 / 6 - skill6.sw, mContext));
                    break;
                case 1:
                    skill6s.add(new Skill6(width * 4 / 6 - skill6.sw, mContext));
                    break;
                case 2:
                    skill6s.add(new Skill6(width * 3 / 6 - skill6.sw, mContext));
                    break;
                case 3:
                    skill6s.add(new Skill6(width * 2 / 6 - skill6.sw, mContext));
                    break;
                case 4:
                    skill6s.add(new Skill6(width / 5 - skill6.sw, mContext));
                    break;
            }
        }

        void createSkill7(int count) {
            switch (count) {
                case 0:
                    skill7s.add(new Skill7(width  / 4 - skill7.sw, mContext));
                    break;
                case 1:
                    skill7s.add(new Skill7(width * 2 / 4 - skill7.sw, mContext));
                    break;
                case 2:
                    skill7s.add(new Skill7(width * 3 / 4 - skill7.sw, mContext));
                    break;
            }
        }

        void createSkill8(int count) {
            switch (count) {
                case 0:
                    skill8s.add(new Skill8(width  / 5 - skill8.sw, mContext));
                    break;
                case 1:
                    skill8s.add(new Skill8(width * 4 / 5 - skill8.sw, mContext));
                    break;
                case 2:
                    skill8s.add(new Skill8(width * 2 / 5 - skill8.sw, mContext));
                    break;
                case 3:
                    skill8s.add(new Skill8(width * 3 / 5 - skill8.sw, mContext));
                    break;
            }
        }

        void createSkill9(int count) {
            switch (count) {
                case 0:
                    skill9s.add(new Skill9(width  / 5 - skill9.sw, mContext));
                    break;
                case 1:
                    skill9s.add(new Skill9(width * 2 / 5 - skill9.sw, mContext));
                    break;
                case 2:
                    skill9s.add(new Skill9(width * 3 / 5 - skill9.sw, mContext));
                    break;
                case 3:
                    skill9s.add(new Skill9(width * 4 / 5 - skill9.sw, mContext));
                    break;
            }
        }

        void createSkill10(int count) {
            switch (count) {
                case 0:
                    skill10s.add(new Skill10(0, mContext));
                    break;
                case 1:
                    skill10s.add(new Skill10(width - skill10.sw*2, mContext));
                    break;
            }
        }

        private void EndOfTheGame() {
            for (Skill1 sk : skill1s) {
                if (Math.abs((mX + mw / 2) - (sk.x + sk.sw / 2)) < mw / 2 && Math.abs((mY + mh / 2) - (sk.y + sk.sh / 2)) < mh / 2)
                    StopThread();
            }
            for (Skill2 sk : skill2s) {
                if (Math.abs((mX + mw / 2) - (sk.x + sk.sw / 2)) < mw / 2 && Math.abs((mY + mh / 2) - (sk.y + sk.sh / 2)) < mh / 2)
                    StopThread();
            }
            for (Skill3 sk : skill3s) {
                if (Math.abs((mX + mw / 2) - (sk.x + sk.sw / 2)) < mw / 2 && Math.abs((mY + mh / 2) - (sk.y + sk.sh / 2)) < mh / 2)
                    StopThread();
            }
            for (Skill4 sk : skill4s) {
                if (Math.abs((mX + mw / 2) - (sk.x + sk.sw / 2)) < mw / 2 && Math.abs((mY + mh / 2) - (sk.y + sk.sh / 2)) < mh / 2)
                    StopThread();
            }
            for (Skill5 sk : skill5s) {
                if (Math.abs((mX + mw / 2) - (sk.x + sk.sw / 2)) < mw / 2 && Math.abs((mY + mh / 2) - (sk.y + sk.sh / 2)) < mh / 2)
                    StopThread();
            }
            for (Skill6 sk : skill6s) {
                if (Math.abs((mX + mw / 2) - (sk.x + sk.sw / 2)) < mw / 2 && Math.abs((mY + mh / 2) - (sk.y + sk.sh / 2)) < mh / 2)
                    StopThread();
            }
            for (Skill7 sk : skill7s) {
                if (Math.abs((mX + mw / 2) - (sk.x + sk.sw / 2)) < mw / 2 && Math.abs((mY + mh / 2) - (sk.y + sk.sh / 2)) < mh / 2)
                    StopThread();
            }
            for (Skill8 sk : skill8s) {
                if (Math.abs((mX + mw / 2) - (sk.x + sk.sw / 2)) < mw / 2 && Math.abs((mY + mh / 2) - (sk.y + sk.sh / 2)) < mh / 2)
                    StopThread();
            }
            for (Skill9 sk : skill9s) {
                if (Math.abs((mX + mw / 2) - (sk.x + sk.sw / 2)) < mw / 2 && Math.abs((mY + mh / 2) - (sk.y + sk.sh / 2)) < mh / 2)
                    StopThread();
            }
            for (Skill10 sk : skill10s) {
                if (Math.abs((mX + mw / 2) - (sk.x + sk.sw / 2)) < mw / 2 && Math.abs((mY + mh / 2) - (sk.y + sk.sh / 2)) < mh / 2)
                    StopThread();
            }

        }
    }
    public OnDeadListener onDeadListener;

    interface OnDeadListener{
        void youDied(String score);
    }

    public void setOnDeadListener(OnDeadListener onDeadListener){
        this.onDeadListener = onDeadListener;
    }
}
