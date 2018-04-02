package com.ortho.medicare.medicareortho.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Harsh on 21-03-2018.
 */

public class ToastUtils {

    public static void makeText(Context mContext, String message, int length) {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }

}
