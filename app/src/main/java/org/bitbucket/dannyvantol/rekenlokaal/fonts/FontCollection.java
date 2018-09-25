package org.bitbucket.dannyvantol.rekenlokaal.fonts;

import android.content.Context;
import android.graphics.Typeface;

public class FontCollection {
    private static FontCollection instance;

    public static synchronized FontCollection getInstance(Context context) {
        if (instance == null)
            instance = new FontCollection(context);

        return instance;
    }

    public Typeface candyCane;

    public FontCollection(Context context) {
        candyCane = Typeface.createFromAsset(context.getAssets(), "fonts/candy_cane.ttf");
    }
}
