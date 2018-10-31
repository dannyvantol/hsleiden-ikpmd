package org.bitbucket.dannyvantol.rekenlokaal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import org.bitbucket.dannyvantol.rekenlokaal.util.Difficulty;

public class ChooseDifficultyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_difficulty);
    }

    public void selectDifficulty(View v){
        switch(v.getId()) {
            case(R.id.difficlutyEasy):
                goToPractice(Difficulty.EASY);
                break;
            case(R.id.difficlutyNormal):
                    goToPractice(Difficulty.EASY);
                break;
            case(R.id.difficlutyHard):
                    goToPractice(Difficulty.EASY);
                break;
        }
    }

    private void goToPractice(Difficulty difficulty) {
        Intent intent = new Intent();

        Bundle bundle = new Bundle();
        bundle.putSerializable("difficulty", difficulty);

        intent.putExtras(bundle);

        startActivity(intent);
    }
}
