package mopgoo.huang.guolin.index.activity;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.LinearLayout;

import mopgoo.huang.guolin.R;

public class SlideMenuActivity extends AppCompatActivity implements
        View.OnTouchListener {
    private final static int LEFTPADDING = 280;
    private LinearLayout menu, content;
    private int screenW, menuW;
    private int touchslop;
    private int velocity;
    private View root;
    private LinearLayout.LayoutParams menuparams;
    private VelocityTracker velocityTracker;
    private ValueAnimator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root=getLayoutInflater().inflate(R.layout.activity_slide_menu,null,false);
        setContentView(root);
        menu = (LinearLayout) findViewById(R.id.menu);
        content = (LinearLayout) findViewById(R.id.content);
        initData();
      root.setOnTouchListener(this);
    }

    private void initData() {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        touchslop= ViewConfiguration.get(getBaseContext()).getScaledTouchSlop();
        screenW = metrics.widthPixels;
        menuparams = (LinearLayout.LayoutParams) menu.getLayoutParams();
        menuparams.width = screenW - LEFTPADDING;
        menuW = menuparams.width;
        menuparams.leftMargin = -menuW;
        content.getLayoutParams().width = screenW;
    }

    private float lx,dx;
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        initVelocityTracker(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lx=event.getX();
                if(animator!=null)
                    animator.cancel();
                break;
            case MotionEvent.ACTION_UP:
                menuparams= (LinearLayout.LayoutParams) menu.getLayoutParams();
                velocityTracker.computeCurrentVelocity(1000);
                velocity= Math.abs((int) velocityTracker.getXVelocity());
                if(-menuparams.leftMargin>menuW/2) {
                    slidmenu(menuparams.leftMargin,-menuW);
                }else{
                    slidmenu(menuparams.leftMargin,0);
                }
                velocityTracker.recycle();
                velocityTracker=null;
                break;
            case MotionEvent.ACTION_MOVE:
                dx=(event.getX()-lx);
                lx=event.getX();
                if(menuparams.leftMargin+(int)dx<=0&&menuparams.leftMargin+(int)dx>-menuW) {
                    menuparams.leftMargin = menuparams.leftMargin + (int) dx;
                    Log.e("dx",dx+"");
                    menu.setLayoutParams(menuparams);
                }
                break;
        }

        return true;
    }

    private void slidmenu(int leftMargin, int location) {
        if(null!=animator)
            animator.cancel();
        animator = ValueAnimator.ofInt(leftMargin,location);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                menuparams.leftMargin =(int)animation.getAnimatedValue();
                menu.setLayoutParams(menuparams);
            }
        });
        animator.setDuration(500);
        animator.start();
    }


    private void initVelocityTracker(MotionEvent event) {
        if (null == velocityTracker) {
            velocityTracker = VelocityTracker.obtain();
        }
        velocityTracker.addMovement(event);
    }


}
