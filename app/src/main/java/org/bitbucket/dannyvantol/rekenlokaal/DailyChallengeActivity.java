package org.bitbucket.dannyvantol.rekenlokaal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import com.google.firebase.database.*;
import org.bitbucket.dannyvantol.rekenlokaal.util.Product;

import java.util.HashMap;

public class DailyChallengeActivity extends GameEngine {

    HashMap<Product, Integer> failures = new HashMap<>();
    int grade = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();

        this.table = 1;

        System.out.println("Reading data");
        System.out.println(reference.child(""));
        reference.child("").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println(dataSnapshot.getValue());
                System.out.println(dataSnapshot.child("0").getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
    void onEndGame() {
        Bundle bundle = getIntent().getExtras();
        bundle.putSerializable("failures", this.failures);
        bundle.putInt("grade", this.grade);

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
