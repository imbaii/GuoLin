package mopgoo.huang.guolin.index.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import mopgoo.huang.guolin.R;
import mopgoo.huang.guolin.rotate3danimation.Rotate3dAnimation;

public class Rotate3dActivity extends Activity {
    private ImageView zheng;
    private ImageView fan;
    private RelativeLayout root;
    private float centerX,centerY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root= (RelativeLayout) LayoutInflater.from(Rotate3dActivity.this).inflate(R.layout.activity_rotate3d,null,false);
        setContentView(root);
        zheng= (ImageView) findViewById(R.id.zheng);
        fan= (ImageView) findViewById(R.id.fan);
        zheng.post(new Runnable() {
            @Override
            public void run() {
                centerX=zheng.getMeasuredWidth()/2;
                centerY=zheng.getMeasuredHeight()/2;
            }
        });
        zheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Rotate3dAnimation rotation = new Rotate3dAnimation(0, 90, centerX, centerY,
                        310.0f, true);
                // 动画持续时间500毫秒
                rotation.setDuration(500);
                // 动画完成后保持完成的状态
                rotation.setFillAfter(true);
                rotation.setInterpolator(new AccelerateInterpolator());
                // 设置动画的监听器
                rotation.setAnimationListener(new ZhengAnimationListener());
                zheng.startAnimation(rotation);
            }
        });
        fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Rotate3dAnimation rotation = new Rotate3dAnimation(360, 270, centerX,
                        centerY, 310.0f, true);
                // 动画持续时间500毫秒
                rotation.setDuration(500);
                // 动画完成后保持完成的状态
                rotation.setFillAfter(true);
                rotation.setInterpolator(new AccelerateInterpolator());
                // 设置动画的监听器
                rotation.setAnimationListener(new FanAnimationListener());
                fan.startAnimation(rotation);
            }
        });
    }
    public class ZhengAnimationListener implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            zheng.setVisibility(View.GONE);
            zheng.setClickable(false);
            fan.setVisibility(View.VISIBLE);
            fan.setClickable(true);
            fan.requestFocus();
            final Rotate3dAnimation rotation = new Rotate3dAnimation(270, 360, centerX, centerY,
                    310.0f, false);
            rotation.setDuration(500);
            rotation.setFillAfter(true);
            rotation.setInterpolator(new AccelerateInterpolator());
            fan.startAnimation(rotation);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
    public class FanAnimationListener implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            fan.setVisibility(View.GONE);
            fan.setClickable(false);
            zheng.setVisibility(View.VISIBLE);
            zheng.setClickable(true);
            zheng.requestFocus();
            final Rotate3dAnimation rotation = new Rotate3dAnimation(90, 0, centerX, centerY,
                    310.0f, false);
            rotation.setDuration(500);
            rotation.setFillAfter(true);
            rotation.setInterpolator(new AccelerateInterpolator());
            zheng.startAnimation(rotation);

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
