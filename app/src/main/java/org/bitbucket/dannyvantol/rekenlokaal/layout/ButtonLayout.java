package org.bitbucket.dannyvantol.rekenlokaal.layout;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import org.bitbucket.dannyvantol.rekenlokaal.GameEngine;
import org.bitbucket.dannyvantol.rekenlokaal.R;

public abstract class ButtonLayout {
    public static final int MARGIN = 30;

    protected GameEngine gameEngine;
    protected LinearLayout buttonContainer;

    protected Button[] buttons;
    protected int[] values;

    public ButtonLayout(GameEngine gameEngine, LinearLayout buttonContainer) {
        this.gameEngine = gameEngine;
        this.buttonContainer = buttonContainer;
    }

    public abstract void generate();

    public void setValues(int[] values) {
        this.values = values;

        for (int i = 0; i < this.values.length; i++) {
            final int value = this.values[i];
            final Button button = this.buttons[i];

            button.setBackgroundResource(R.drawable.button);
            button.setText(Integer.toString(value));
            button.setEnabled(true);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ButtonLayout.this.gameEngine.answer(button, value);
                }
            });
        }
    };
}
