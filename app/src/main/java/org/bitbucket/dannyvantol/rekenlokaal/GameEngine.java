package org.bitbucket.dannyvantol.rekenlokaal;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.bitbucket.dannyvantol.rekenlokaal.layout.ButtonLayout;
import org.bitbucket.dannyvantol.rekenlokaal.layout.FourButtonLayout;
import org.bitbucket.dannyvantol.rekenlokaal.layout.SixButtonLayout;
import org.bitbucket.dannyvantol.rekenlokaal.layout.ThreeButtonLayout;
import org.bitbucket.dannyvantol.rekenlokaal.util.ArrayShuffle;
import org.bitbucket.dannyvantol.rekenlokaal.util.Difficulty;
import org.bitbucket.dannyvantol.rekenlokaal.util.MathEngine;
import org.bitbucket.dannyvantol.rekenlokaal.util.Product;

import java.util.HashMap;

public abstract class GameEngine extends Activity {
    private static int LIMIT = 10;
    private Bundle bundle;

    protected int table;
    private Difficulty difficulty;
    private TextView product;

    private int counter = 0;

    private String[] products;
    private HashMap<String, Integer> mapper = new HashMap<>();

    private LinearLayout buttonContainer;
    private ButtonLayout buttonLayout;

    private MathEngine mathEngine;
    private Product current;

    private HashMap<Difficulty, Integer> difficultyMapper = new HashMap<>();

    private MediaPlayer mediaPlayer;
    private ProgressBar progressBar;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_engine);

        this.bundle = getIntent().getExtras();

        this.table = this.bundle.getInt("table");
        this.difficulty = (Difficulty) this.bundle.getSerializable("difficulty");
        this.mathEngine = new MathEngine(this.table, this.table * 10);

        this.product = (TextView) findViewById(R.id.product);
        this.buttonContainer = (LinearLayout) findViewById(R.id.buttonContainer);
        this.difficultyMapper.put(Difficulty.EASY, 3);
        this.difficultyMapper.put(Difficulty.NORMAL, 4);
        this.difficultyMapper.put(Difficulty.HARD, 6);

        this.mediaPlayer = MediaPlayer.create(this, R.raw.kahoot_original);
        this.mediaPlayer.setLooping(true);
        this.mediaPlayer.start();

        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.progressBar.setVisibility(View.INVISIBLE);

        this.generateLayout();

        try {
            this.generateRandomOrderProducts();
        } catch (NullPointerException exception) {

        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        this.loop();
    }

    protected void generateLayout() {
        switch (this.difficulty) {
            case EASY:
                this.buttonLayout = new ThreeButtonLayout(this, this.buttonContainer);
                break;
            case NORMAL:
                this.buttonLayout = new FourButtonLayout(this, this.buttonContainer);
                break;
            case HARD:
                this.buttonLayout = new SixButtonLayout(this, this.buttonContainer);
                break;
        }

        this.buttonLayout.generate();
    }

    private boolean hasNext() {
        return counter < this.mapper.size();
    }

    private Product next() {
        String productKey = this.products[counter];
        Product product = new Product(productKey, this.mapper.get(productKey));

        this.counter++;

        return product;
    }

    protected void enableTimer() {
        this.progressBar.setVisibility(View.VISIBLE);
        this.countDownTimer = new CountDownTimer(10000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                int progress = (int) millisUntilFinished / 100;
                GameEngine.this.progressBar.setProgress(progress);
                System.out.println((int) millisUntilFinished / 100);
            }

            @Override
            public void onFinish() {
                GameEngine.this.loop();
            }
        };

        this.resetTimer();
    }

    protected void resetTimer() {
        if (this.countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer.start();
        }
    }

    public void answer(Button button, int answer) {
        button.setEnabled(false);

        if (answer == this.current.getValue()) {
            this.onCorrectAnswer(button, this.current, answer);
        } else {
            button.setBackgroundResource(R.drawable.button_incorrect);
            this.onIncorrectAnswer(button, this.current, answer);
        }
    }

    private void generateRandomOrderProducts() {
        this.products = new String[10];
        int[] products = new int[10];

        for (int i = 1; i <= GameEngine.LIMIT; i++) {
            products[i - 1] = i * this.table;
            this.products[i - 1] = Integer.toString(this.table) + " " + getResources().getString(R.string.multiplier) + " " + Integer.toString(i);
        }

        for (int x = 0; x < GameEngine.LIMIT; x++) {
            this.mapper.put(this.products[x], products[x]);
        }

        System.out.println(this.products[0]);
        ArrayShuffle.string(this.products);
    }

    protected void loop() {

        if (this.hasNext()) {
            this.resetTimer();
            this.current = this.next();

            int[] random = this.mathEngine.generateRandomNumbers(this.difficultyMapper.get(this.difficulty), new int[]{this.current.getValue()});
            random[0] = this.current.getValue();

            ArrayShuffle.integer(random);

            this.product.setText(this.current.getProduct());
            this.buttonLayout.setValues(random);
        } else {
            this.mediaPlayer.stop();
            this.onEndGame();
            this.finish();
        }
    }

    abstract void onCorrectAnswer(Button button, Product product, int chosen);

    abstract void onIncorrectAnswer(Button button, Product product, int chosen);

    abstract void onEndGame();

    @Override
    protected void onPause() {
        super.onPause();
        this.mediaPlayer.stop();

        if (this.countDownTimer != null)
            this.countDownTimer.cancel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.mediaPlayer.stop();

        if (this.countDownTimer != null)
            this.countDownTimer.cancel();
    }
}
