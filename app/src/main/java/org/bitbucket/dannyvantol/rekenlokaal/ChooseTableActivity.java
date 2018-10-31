package org.bitbucket.dannyvantol.rekenlokaal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseTableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_table);

    }

    public void selectTable(View v){
        switch (v.getId()) {
            case (R.id.ButtonTable1):
                goToChooseDifficulty(1);
                break;
            case (R.id.ButtonTable2):
                goToChooseDifficulty(2);
                break;
            case (R.id.ButtonTable3):
                goToChooseDifficulty(3);
                break;
            case (R.id.ButtonTable4):
                goToChooseDifficulty(4);
                break;
            case (R.id.ButtonTable5):
                goToChooseDifficulty(5);
                break;
            case (R.id.ButtonTable6):
                goToChooseDifficulty(6);
                break;
            case (R.id.ButtonTable7):
                goToChooseDifficulty(7);
                break;
            case (R.id.ButtonTable8):
                goToChooseDifficulty(8);
                break;
            case (R.id.ButtonTable9):
                goToChooseDifficulty(9);
                break;
            case (R.id.ButtonTable10):
                goToChooseDifficulty(10);
                break;
        }
    }

    private void goToChooseDifficulty(int table) {
        Intent intent = new Intent(ChooseTableActivity.this, ChooseDifficultyActivity.class);

        Bundle bundle = new Bundle();
        bundle.putInt("table", table);

        intent.putExtras(bundle);

        startActivity(intent);
    }
}
