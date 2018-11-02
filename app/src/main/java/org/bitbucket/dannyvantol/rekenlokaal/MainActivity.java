package org.bitbucket.dannyvantol.rekenlokaal;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.bitbucket.dannyvantol.rekenlokaal.Database.DatabaseHelper;
import org.bitbucket.dannyvantol.rekenlokaal.Database.DatabaseInfo;
import org.bitbucket.dannyvantol.rekenlokaal.util.GameMode;

public class MainActivity extends AppCompatActivity {

    private Button buttonOefenen;
    private Button buttonScoreboard;
    private Button butttonDaily;
    private Button butttonScoreboard;

    private MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonOefenen = (Button) findViewById(R.id.ButtonOefenen);
        buttonScoreboard = (Button) findViewById(R.id.ButtonScoreboard);
        butttonDaily = (Button) findViewById(R.id.ButtonDaily);
        butttonScoreboard = (Button) findViewById(R.id.ButtonScoreboard);

        this.mediaPlayer = MediaPlayer.create(this, R.raw.kahoot_lobby);
        this.mediaPlayer.setLooping(true);
        this.mediaPlayer.start();
    }

    public void practice(View view) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("mode", GameMode.PRACTICE);

        Intent intent = new Intent(MainActivity.this, ChooseTableActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    public void test(View view) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("mode", GameMode.TEST);

        Intent intent = new Intent(MainActivity.this, ChooseTableActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    public void scoreboard(View view) {
        Intent intent = new Intent(MainActivity.this, ScoreboardActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.mediaPlayer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.mediaPlayer.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.mediaPlayer.stop();
    }
}
