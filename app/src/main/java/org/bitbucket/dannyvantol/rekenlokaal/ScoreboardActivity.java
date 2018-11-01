package org.bitbucket.dannyvantol.rekenlokaal;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.bitbucket.dannyvantol.rekenlokaal.Database.DatabaseHelper;
import org.bitbucket.dannyvantol.rekenlokaal.Database.DatabaseInfo;

public class ScoreboardActivity extends AppCompatActivity {

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        linearLayout = (LinearLayout) findViewById(R.id.linearVBox);

        Cursor cursor = getFromDatabase();
        cursor.moveToFirst();

        while (!cursor.isNull(cursor.getPosition())) {
            TextView textView = new TextView(this);
            textView.setText("Moelijkheid: " + cursor.getString(cursor.getColumnIndex("difficulty")) +
                    " Tafel: " + cursor.getString(cursor.getColumnIndex("table")) +
                    " Cijfer: " + cursor.getString(cursor.getColumnIndex("grade")));
            textView.setGravity(Gravity.CENTER);

            linearLayout.addView(textView);
        }
    }

    private Cursor getFromDatabase() {
        DatabaseHelper databaseHelper = DatabaseHelper.getHelper(this);

        Cursor cursor = databaseHelper.query(DatabaseInfo.Tests.TESTS, new String[]{
                "*"
        }, null, null, null, null, null);

        return cursor;
    }
}
