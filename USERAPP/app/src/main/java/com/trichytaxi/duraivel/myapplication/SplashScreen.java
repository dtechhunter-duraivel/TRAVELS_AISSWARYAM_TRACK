package com.trichytaxi.duraivel.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SplashScreen extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 2500;
    TextView tv,tv2,tv4,con;
    ImageView img;
    String a="0";
    //SharedPreferences pref;
    Intent mainIntent;
    RequestQueue SQueue;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        SQueue = Volley.newRequestQueue(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        pref = getApplicationContext().getSharedPreferences("sma", Context.MODE_MULTI_PROCESS); // 0 - for private mode
              //SharedPreferences.Editor editor = pref.edit();
        if(pref.contains("flag")) {
            a = pref.getString("flag", "0");
        }
        img= (ImageView)findViewById(R.id.t1);
        tv2=(TextView)findViewById(R.id.t2) ;
        tv4=(TextView)findViewById(R.id.t6);
        Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anim);
        Animation anim2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        img.startAnimation(anim);
        tv2.startAnimation(anim);
        tv4.startAnimation(anim);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the First Page. */

if(a.equals("1")) {

    mainIntent = new Intent(SplashScreen
            .this, MakeJourney.class);

}
else
{
    mainIntent = new Intent(SplashScreen
            .this, TabbedView.class);
}
    SplashScreen.this.startActivity(mainIntent);
    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    SplashScreen.this.finish();

            }
        }, SPLASH_DISPLAY_LENGTH);

    }


}
