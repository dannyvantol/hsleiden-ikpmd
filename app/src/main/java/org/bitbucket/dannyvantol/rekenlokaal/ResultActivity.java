package org.bitbucket.dannyvantol.rekenlokaal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import org.bitbucket.dannyvantol.rekenlokaal.util.Product;

import java.util.HashMap;
import java.util.Set;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle bundle = getIntent().getExtras();

        HashMap<Product, Integer> failures = (HashMap<Product, Integer>)bundle.getSerializable("failures");

        TextView textView = (TextView) findViewById(R.id.grade);
        textView.setText(Integer.toString(bundle.getInt("grade")));

        Set<Product> productsSet = failures.keySet();

        TableLayout tableLayout = (TableLayout) findViewById(R.id.table);
        TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);

        for (Product product: productsSet) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            TextView som = new TextView(this);
            som.setText(product.getProduct());
            som.setLayoutParams(params);

            TextView correctAnswer = new TextView(this);
            correctAnswer.setText(Integer.toString(product.getValue()));
            correctAnswer.setLayoutParams(params);

            TextView yourAnswer = new TextView(this);
            yourAnswer.setGravity(Gravity.RIGHT);
            yourAnswer.setText(Integer.toString(failures.get(product)));
            yourAnswer.setLayoutParams(params);

            tableRow.addView(som);
            tableRow.addView(correctAnswer);
            tableRow.addView(yourAnswer);

            tableLayout.addView(tableRow);
        }
    }
}
