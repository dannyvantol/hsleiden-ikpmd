package org.bitbucket.dannyvantol.rekenlokaal;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import com.google.firebase.database.*;
import org.bitbucket.dannyvantol.rekenlokaal.util.GameMode;

public class MainActivity extends AppCompatActivity {

    private Button buttonDaily;

    private MediaPlayer mediaPlayer;
    public static final FirebaseDatabase DATABASE = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.buttonDaily = (Button) findViewById(R.id.ButtonDaily);

        this.mediaPlayer = MediaPlayer.create(this, R.raw.kahoot_lobby);
        this.mediaPlayer.setLooping(true);
        this.mediaPlayer.start();

        DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {
                    buttonDaily.setEnabled(true);
                    buttonDaily.setBackgroundResource(R.drawable.button);
                } else {
                    buttonDaily.setEnabled(false);
                    buttonDaily.setBackgroundResource(R.drawable.button_disables);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.err.println("Listener was cancelled");
            }
        });
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

    public void dailychallenge(View view) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("mode", GameMode.DAILY);
        bundle.putInt("table", 1);

        Intent intent = new Intent(MainActivity.this, ChooseDifficultyActivity.class);
        intent.putExtras(bundle);

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
