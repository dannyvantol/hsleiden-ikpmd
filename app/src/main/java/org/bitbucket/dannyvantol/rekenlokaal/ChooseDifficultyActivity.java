package org.bitbucket.dannyvantol.rekenlokaal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.bitbucket.dannyvantol.rekenlokaal.util.Difficulty;

public class ChooseDifficultyActivity extends AppCompatActivity {

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_difficulty);

        this.bundle = getIntent().getExtras();
    }

    public void selectDifficulty(View v){
        switch(v.getId()) {
            case(R.id.difficultyEasy):
                goToPractice(Difficulty.EASY);
                break;
            case(R.id.difficultyNormal):
                    goToPractice(Difficulty.NORMAL);
                break;
            case(R.id.difficultyHard):
                    goToPractice(Difficulty.HARD);
                break;
        }
    }

    private void goToPractice(Difficulty difficulty) {
        Intent intent = new Intent(this, PracticeActivity.class);

        this.bundle.putSerializable("difficulty", difficulty);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
