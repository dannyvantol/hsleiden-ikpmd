package org.bitbucket.dannyvantol.rekenlokaal.util;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.widget.Button;
import org.bitbucket.dannyvantol.rekenlokaal.GameEngine;
import org.bitbucket.dannyvantol.rekenlokaal.R;

public class ButtonGenerator {
    public static Button[] generate(Activity activity, int times) {
        Button[] buttons = new Button[times];

        for (int i = 0; i < times; i++) {
            Button button = new Button(activity);

            button.setBackgroundResource(R.drawable.button);
            button.setTextColor(Color.WHITE);

            button.setTypeface(ResourcesCompat.getFont(activity, R.font.candy_cane));
            button.setTextSize(40);
            button.setText("EMPTY");

            buttons[i] = button;
        }

        return buttons;
    }
}
