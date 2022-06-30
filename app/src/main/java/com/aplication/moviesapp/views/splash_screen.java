package com.aplication.moviesapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.aplication.moviesapp.R;
import com.aplication.moviesapp.databinding.ActivitySplashScreenBinding;

public class splash_screen extends AppCompatActivity {
    ActivitySplashScreenBinding activitySplashScreenBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
         activitySplashScreenBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen );

     //   Animation animation = AnimationUtils.loadAnimation(this , R.anim.scale_transaction);
       // this.activitySplashScreenBinding.appNameTv.startAnimation(animation);

        AnimationDrawable animationDrawable =  (AnimationDrawable)this.activitySplashScreenBinding.iconSPlash.getBackground();
          animationDrawable.start();

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
             goToMainScreen();
            }
        }, 2000);


}

private  void goToMainScreen( ) {
        Intent intent = new Intent(this , MainActivity.class);
        startActivity(intent);
        this.finish();
}
}