package com.ortho.medicare.medicareortho.customviews;

import android.content.Context;
import android.graphics.Typeface;

public class SansSemiBold {

    private static SansSemiBold instance;
    private static Typeface typeface;

    public static SansSemiBold getInstance(Context context) {
        synchronized (SansSemiBold.class) {
            if (instance == null) {
                instance = new SansSemiBold();
                typeface = Typeface.createFromAsset(context.getResources().getAssets(), "Semibold.otf");
            }
            return instance;
        }
    }

    public Typeface getTypeFace() {
        return typeface;
    }
}