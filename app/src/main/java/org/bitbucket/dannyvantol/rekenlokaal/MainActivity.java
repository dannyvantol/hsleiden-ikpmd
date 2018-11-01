package org.bitbucket.dannyvantol.rekenlokaal;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import org.bitbucket.dannyvantol.rekenlokaal.Database.DatabaseHelper;
import org.bitbucket.dannyvantol.rekenlokaal.Database.DatabaseInfo;

public class MainActivity extends AppCompatActivity {

    Button buttonOefenen;
    Button buttonScoreboard;
    Button butttonDaily;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonOefenen = (Button) findViewById(R.id.ButtonOefenen);
        buttonScoreboard = (Button) findViewById(R.id.ButtonScoreboard);
        butttonDaily = (Button) findViewById(R.id.ButtonDaily);

        buttonOefenen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ChooseTableActivity.class));
            }
        });

        buttonScoreboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToDatabase();
            }
        });

        butttonDaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFromDatabase();
            }
        });
    }

    private void addToDatabase() {
        DatabaseHelper databaseHelper = DatabaseHelper.getHelper(this);

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseInfo.TestsColumn.DIFFICULTY, 1);
        contentValues.put(DatabaseInfo.TestsColumn.TABLE, 7);
        contentValues.put(DatabaseInfo.TestsColumn.GRADE, 9);

        databaseHelper.insert(DatabaseInfo.Tests.TESTS, null, contentValues);
    }

    private void getFromDatabase() {
        DatabaseHelper databaseHelper = DatabaseHelper.getHelper(this);

        Cursor cursor = databaseHelper.query(DatabaseInfo.Tests.TESTS, new String[]{
            "*"
        },null, null, null, null, null);

        cursor.moveToFirst();

        String cijfer = (String) cursor.getString(cursor.getColumnIndex("grade"));

        Log.d("Je cijfer is: ", cijfer + "!");
    }
}
