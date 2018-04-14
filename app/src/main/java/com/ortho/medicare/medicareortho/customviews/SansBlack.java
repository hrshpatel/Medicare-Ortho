package com.ortho.medicare.medicareortho.customviews;

import android.content.Context;
import android.graphics.Typeface;

public class SansBlack {

    private static SansBlack instance;
    private static Typeface typeface;

    public static SansBlack getInstance(Context context) {
        synchronized (SansBlack.class) {
            if (instance == null) {
                instance = new SansBlack();
                typeface = Typeface.createFromAsset(context.getResources().getAssets(), "Black.otf");
            }
            return instance;
        }
    }

    public Typeface getTypeFace() {
        return typeface;
    }
}