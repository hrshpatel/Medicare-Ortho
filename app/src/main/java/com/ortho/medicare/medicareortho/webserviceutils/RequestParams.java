package com.ortho.medicare.medicareortho.webserviceutils;

import android.app.Activity;

import com.ortho.medicare.medicareortho.R;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

public class RequestParams {

    public static RequestBody getAboutUsBody(Activity activity) {
        return new FormEncodingBuilder()
                .add(Constant.WebServicesKeys.mAppKey, activity.getString(R.string.app_key_ws))
                .build();
    }

    public static RequestBody getContactUsBody(Activity activity) {
        return new FormEncodingBuilder()
                .add(Constant.WebServicesKeys.mAppKey, activity.getString(R.string.app_key_ws))
                .build();
    }

}
