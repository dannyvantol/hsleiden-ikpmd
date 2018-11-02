package org.bitbucket.dannyvantol.rekenlokaal;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.bitbucket.dannyvantol.rekenlokaal.Database.DatabaseHelper;
import org.bitbucket.dannyvantol.rekenlokaal.Database.DatabaseInfo;

public class ScoreboardActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        TableLayout tableLayout = (TableLayout) findViewById(R.id.table);
        TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);

        Cursor cursor = getFromDatabase();
        cursor.moveToFirst();

        while (cursor.moveToNext()){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            TextView tafel = new TextView(this);
            tafel.setText(cursor.getString(cursor.getColumnIndex(DatabaseInfo.TestsColumn.TABLE)));
            tafel.setLayoutParams(params);

            TextView moeilijkheid = new TextView(this);
            moeilijkheid.setText(cursor.getString(cursor.getColumnIndex(DatabaseInfo.TestsColumn.DIFFICULTY)));
            moeilijkheid.setLayoutParams(params);

            TextView cijfer = new TextView(this);
            cijfer.setText(cursor.getString(cursor.getColumnIndex(DatabaseInfo.TestsColumn.GRADE)));
            cijfer.setLayoutParams(params);
            cijfer.setGravity(Gravity.RIGHT);

            tableRow.addView(tafel);
            tableRow.addView(moeilijkheid);
            tableRow.addView(cijfer);

            tableLayout.addView(tableRow);
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
