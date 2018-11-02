package org.bitbucket.dannyvantol.rekenlokaal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import org.bitbucket.dannyvantol.rekenlokaal.util.Product;

import java.util.HashMap;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle bundle = getIntent().getExtras();

        HashMap<Product, Integer> failures = (HashMap<Product, Integer>)bundle.getSerializable("failures");
        int grade = 10 - failures.size();

        TextView textView = (TextView) findViewById(R.id.grade);
        textView.setText(Integer.toString(grade));
    }
}
