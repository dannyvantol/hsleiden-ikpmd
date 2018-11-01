package org.bitbucket.dannyvantol.rekenlokaal.layout;

import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import org.bitbucket.dannyvantol.rekenlokaal.GameEngine;
import org.bitbucket.dannyvantol.rekenlokaal.util.ButtonGenerator;

public class ThreeButtonLayout extends ButtonLayout {

    public ThreeButtonLayout(GameEngine gameEngine, LinearLayout linearLayout) {
        super(gameEngine, linearLayout);
    }

    @Override
    public void generate() {
        this.buttons = ButtonGenerator.generate(this.gameEngine, 3);

        LinearLayout horizontalRow = new LinearLayout(this.gameEngine);
        horizontalRow.setOrientation(LinearLayout.HORIZONTAL);
        horizontalRow.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams rightMargin = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        rightMargin.setMargins(0, 0, ButtonLayout.MARGIN, 0);

        LinearLayout.LayoutParams leftMargin = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        leftMargin.setMargins(ButtonLayout.MARGIN, 0, 0, 0);

        this.buttons[0].setLayoutParams(rightMargin);
        this.buttons[2].setLayoutParams(leftMargin);


        for (Button button: this.buttons) {
            horizontalRow.addView(button);
        }

        this.buttonContainer.addView(horizontalRow);
    }
}
