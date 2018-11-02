package org.bitbucket.dannyvantol.rekenlokaal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import com.google.firebase.database.*;
import org.bitbucket.dannyvantol.rekenlokaal.util.ArrayShuffle;
import org.bitbucket.dannyvantol.rekenlokaal.util.Product;

import java.util.HashMap;
import java.util.Set;

public class DailyChallengeActivity extends GameEngine {

    HashMap<Product, Integer> failures = new HashMap<>();
    int grade = 0;
    boolean played = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DatabaseReference reference = MainActivity.DATABASE.getReference();
        this.mapper.clear();
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 0;

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Product product = new Product((String) snapshot.child("product").getValue(), Integer.parseInt(Long.toString((long) snapshot.child("value").getValue())));

                    DailyChallengeActivity.this.products[i] = product.getProduct();
                    DailyChallengeActivity.this.mapper.put(product.getProduct(), product.getValue());

                    i++;
                }

                DailyChallengeActivity.this.counter = 0;
                DailyChallengeActivity.this.played = true;
                DailyChallengeActivity.this.loop();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                DailyChallengeActivity.this.finish();
            }
        });
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
    protected void loop() {

        if (this.hasNext()) {
            this.resetTimer();
            this.current = this.next();
            this.table = Integer.parseInt(Character.toString(this.current.getProduct().charAt(0)));

            int[] random = this.mathEngine.generateRandomNumbers(this.difficultyMapper.get(this.difficulty), new int[]{this.current.getValue()});
            random[0] = this.current.getValue();

            ArrayShuffle.integer(random);

            this.product.setText(this.current.getProduct());
            this.buttonLayout.setValues(random);
        } else if (played) {
            this.mediaPlayer.stop();
            this.onEndGame();
            this.finish();
        }
    }

    @Override
    void onEndGame() {
        Bundle bundle = getIntent().getExtras();
        bundle.putSerializable("failures", this.failures);
        bundle.putInt("grade", this.grade);

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
