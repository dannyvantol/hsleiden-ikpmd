package org.bitbucket.dannyvantol.rekenlokaal.layout;

import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import org.bitbucket.dannyvantol.rekenlokaal.GameEngine;
import org.bitbucket.dannyvantol.rekenlokaal.util.ButtonGenerator;

public class FourButtonLayout extends ButtonLayout {
    public FourButtonLayout(GameEngine gameEngine, LinearLayout linearLayout) {
        super(gameEngine, linearLayout);
    }

    @Override
    public void generate() {
        this.buttons = ButtonGenerator.generate(this.gameEngine, 4);

        LinearLayout horizontalRowOne = new LinearLayout(this.gameEngine);
        horizontalRowOne.setOrientation(LinearLayout.HORIZONTAL);
        horizontalRowOne.setGravity(Gravity.CENTER);
        horizontalRowOne.setMinimumHeight(200);

        horizontalRowOne.addView(this.buttons[0]);
        horizontalRowOne.addView(this.buttons[1]);

        LinearLayout horizontalRowTwo = new LinearLayout(this.gameEngine);
        horizontalRowTwo.setOrientation(LinearLayout.HORIZONTAL);
        horizontalRowTwo.setGravity(Gravity.CENTER);
        horizontalRowTwo.setMinimumHeight(200);

        horizontalRowTwo.addView(this.buttons[2]);
        horizontalRowTwo.addView(this.buttons[3]);

        LinearLayout.LayoutParams rightMargin = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        rightMargin.setMargins(0, 0, ButtonLayout.MARGIN, 0);

        LinearLayout.LayoutParams rightBottomMargin = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        rightBottomMargin.setMargins(0, 0, ButtonLayout.MARGIN, ButtonLayout.MARGIN);


        LinearLayout.LayoutParams leftMargin = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        leftMargin.setMargins(ButtonLayout.MARGIN, 0, 0, 0);

        LinearLayout.LayoutParams leftBottomMargin = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        leftBottomMargin.setMargins(ButtonLayout.MARGIN, 0, 0, ButtonLayout.MARGIN);

        this.buttons[0].setLayoutParams(rightMargin);
        this.buttons[1].setLayoutParams(leftMargin);

        this.buttons[2].setLayoutParams(rightMargin);
        this.buttons[3].setLayoutParams(leftMargin);

        this.buttonContainer.addView(horizontalRowOne);
        this.buttonContainer.addView(horizontalRowTwo);
    }
}
