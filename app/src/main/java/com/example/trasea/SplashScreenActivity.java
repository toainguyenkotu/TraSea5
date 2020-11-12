package com.example.trasea;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import org.w3c.dom.Text;

public class SplashScreenActivity extends AppCompatActivity {
    ImageView iv_background;
    LottieAnimationView iv_splash_man_riding,iv_splash_progressbar;
    TextView tv_welcome,tv_trase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        tv_welcome = findViewById(R.id.tv_welcome);
        tv_trase = findViewById(R.id.tv_trase);
        iv_splash_man_riding = findViewById(R.id.iv_splash_man_riding);
        iv_splash_progressbar = findViewById(R.id.iv_splash_progressbar);
        iv_background = findViewById(R.id.iv_background);

        iv_background.animate().translationY(-2600).setDuration(700).setStartDelay(4000);
        tv_welcome.animate().translationY(2600).setDuration(700).setStartDelay(4000);
        iv_splash_man_riding.animate().translationY(2600).setDuration(700).setStartDelay(4000);
        iv_splash_progressbar.animate().translationY(2600).setDuration(700).setStartDelay(4000);
        tv_trase.animate().translationY(2600).setDuration(700).setStartDelay(4000);




    }
}