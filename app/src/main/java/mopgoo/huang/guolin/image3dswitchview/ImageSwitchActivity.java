package mopgoo.huang.guolin.image3dswitchview;

import android.app.Activity;
import android.os.Bundle;

import mopgoo.huang.guolin.R;

public class ImageSwitchActivity extends Activity {

    private Image3DSwitchView imageSwitchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_switch);
        imageSwitchView = (Image3DSwitchView) findViewById(R.id.image_switch_view);
        imageSwitchView.setOnImageSwitchListener(new Image3DSwitchView.OnImageSwitchListener() {
            @Override
            public void onImageSwitch(int currentImage) {
                // Log.d("TAG", "current image is " + currentImage);
            }
        });
        imageSwitchView.setCurrentImage(1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        imageSwitchView.clear();
    }
}
