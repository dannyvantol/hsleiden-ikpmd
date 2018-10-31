package org.bitbucket.dannyvantol.rekenlokaal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PracticeActivity extends AppCompatActivity {

    private Bundle bundle;
    private int table;
    private int difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        this.bundle = getIntent().getExtras();
    }
}
