package org.bitbucket.dannyvantol.rekenlokaal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import org.bitbucket.dannyvantol.rekenlokaal.util.ArrayShuffle;
import org.bitbucket.dannyvantol.rekenlokaal.util.Difficulty;

import java.util.Collection;
import java.util.HashMap;

public class PracticeActivity extends AppCompatActivity {

    private static int LIMIT = 10;

    private Bundle bundle;

    private int table;
    private Difficulty difficulty;

    private String[] productKeys;

    private HashMap<String, Integer> mapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        this.bundle = getIntent().getExtras();

        this.table = this.bundle.getInt("table");
        this.difficulty = (Difficulty) this.bundle.getSerializable("difficulty");

        this.productKeys = new String[PracticeActivity.LIMIT];
        this.mapper = new HashMap<>();

    }

    private int[] generateProducts() {
        int[] products = new int[10];
        for (int i = 1; i <= PracticeActivity.LIMIT; i++) {
            products[i - 1] = i * this.table;
            this.productKeys[i - 1] = Integer.toString(this.table) + " " + R.string.multiplier + " " + Integer.toString(i * this.table);
        }

        for (int x = 0; x < PracticeActivity.LIMIT; x++) {
            this.mapper.put(this.productKeys[x], products[x]);
        }

        ArrayShuffle.integer(this.productKeys);

        return products;
    }

    public void increaseAnswer() {

    }
}
