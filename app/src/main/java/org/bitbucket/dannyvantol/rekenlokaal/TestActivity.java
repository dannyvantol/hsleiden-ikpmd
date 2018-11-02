package org.bitbucket.dannyvantol.rekenlokaal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.bitbucket.dannyvantol.rekenlokaal.util.Product;

import java.util.HashMap;

public class TestActivity extends GameEngine {

    HashMap<Product, Integer> failures = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.enableTimer();
    }

    @Override
    void onCorrectAnswer(Product product, int chosen) {
    }

    @Override
    void onIncorrectAnswer(Product product, int chosen) {
        failures.put(product, chosen);
        this.loop();
    }

    @Override
    void onEndGame() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("failures", this.failures);

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
