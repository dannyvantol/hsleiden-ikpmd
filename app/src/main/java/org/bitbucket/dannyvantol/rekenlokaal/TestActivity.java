package org.bitbucket.dannyvantol.rekenlokaal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import org.bitbucket.dannyvantol.rekenlokaal.util.Product;

import java.util.HashMap;

public class TestActivity extends GameEngine {

    HashMap<Product, Integer> failures = new HashMap<>();
    int grade = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.enableTimer();
    }

    @Override
    void onCorrectAnswer(Button button, Product product, int chosen) {
        this.grade++;
        this.loop();
    }

    @Override
    void onIncorrectAnswer(Button button, Product product, int chosen) {
        this.failures.put(product, chosen);
        this.loop();
    }

    @Override
    void onEndGame() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("failures", this.failures);
        bundle.putInt("grade", this.grade);

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
