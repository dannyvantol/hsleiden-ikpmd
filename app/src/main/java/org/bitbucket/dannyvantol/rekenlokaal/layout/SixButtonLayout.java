package org.bitbucket.dannyvantol.rekenlokaal.layout;

import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import org.bitbucket.dannyvantol.rekenlokaal.GameEngine;
import org.bitbucket.dannyvantol.rekenlokaal.util.ButtonGenerator;

public class SixButtonLayout extends ButtonLayout {
    public SixButtonLayout(GameEngine gameEngine, LinearLayout linearLayout) {
        super(gameEngine, linearLayout);
    }

    @Override
    public void generate() {
        this.buttons = ButtonGenerator.generate(this.gameEngine, 6);

        LinearLayout horizontalRowOne = new LinearLayout(this.gameEngine);
        horizontalRowOne.setOrientation(LinearLayout.HORIZONTAL);
        horizontalRowOne.setGravity(Gravity.CENTER);
        horizontalRowOne.setMinimumHeight(200);

        horizontalRowOne.addView(this.buttons[0]);
        horizontalRowOne.addView(this.buttons[1]);
        horizontalRowOne.addView(this.buttons[2]);

        LinearLayout horizontalRowTwo = new LinearLayout(this.gameEngine);
        horizontalRowTwo.setOrientation(LinearLayout.HORIZONTAL);
        horizontalRowTwo.setGravity(Gravity.CENTER);
        horizontalRowTwo.setMinimumHeight(200);

        horizontalRowTwo.addView(this.buttons[3]);
        horizontalRowTwo.addView(this.buttons[4]);
        horizontalRowTwo.addView(this.buttons[5]);

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

        this.buttons[3].setLayoutParams(rightMargin);
        this.buttons[5].setLayoutParams(leftMargin);


        this.buttonContainer.addView(horizontalRowOne);
        this.buttonContainer.addView(horizontalRowTwo);
    }
}
