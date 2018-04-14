package com.ortho.medicare.medicareortho.customviews;

import android.content.Context;
import android.graphics.Typeface;

public class SansRegular {

    private static SansRegular instance;
    private static Typeface typeface;

    public static SansRegular getInstance(Context context) {
        synchronized (SansRegular.class) {
            if (instance == null) {
                instance = new SansRegular();
                typeface = Typeface.createFromAsset(context.getResources().getAssets(), "Regular.otf");
            }
            return instance;
        }
    }

    public Typeface getTypeFace() {
        return typeface;
    }
}