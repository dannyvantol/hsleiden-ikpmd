package org.bitbucket.dannyvantol.rekenlokaal;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import org.bitbucket.dannyvantol.rekenlokaal.fonts.FontCollection;

public class SplashActivity extends AppCompatActivity {

    TextView splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        FontCollection fontCollection = FontCollection.getInstance(this);


        splash = (TextView) findViewById(R.id.splash);
        splash.setTypeface(fontCollection.candyCane);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(
                        new Intent(SplashActivity.this, MainActivity.class)
                );
            }
        }, 1500);
    }
}
