package com.ortho.medicare.medicareortho.customviews;

import android.content.Context;
import android.graphics.Typeface;

public class SansBold {

    private static SansBold instance;
    private static Typeface typeface;

    public static SansBold getInstance(Context context) {
        synchronized (SansBold.class) {
            if (instance == null) {
                instance = new SansBold();
                typeface = Typeface.createFromAsset(context.getResources().getAssets(), "Semibold.otf");
            }
            return instance;
        }
    }

    public Typeface getTypeFace() {
        return typeface;
    }
}