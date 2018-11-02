package org.bitbucket.dannyvantol.rekenlokaal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import org.bitbucket.dannyvantol.rekenlokaal.util.Product;

import java.util.HashMap;

public class PracticeActivity extends GameEngine {

    @Override
    void onCorrectAnswer(Button button, Product product, int chosen) {
        button.setBackgroundResource(R.drawable.button_correct);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                PracticeActivity.this.loop();
            }
        }, 1000);
    }

    @Override
    void onIncorrectAnswer(Button button, Product product, int chosen) {

    }

    @Override
    void onEndGame() {

    }
}
