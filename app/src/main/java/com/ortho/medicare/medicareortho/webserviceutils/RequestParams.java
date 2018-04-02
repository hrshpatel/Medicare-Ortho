package com.ortho.medicare.medicareortho.webserviceutils;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

public class RequestParams {

    public static RequestBody getAboutUsBody() {
        return new FormEncodingBuilder()
                .add(Constant.WebServicesKeys.mAppKey, "da85d87b84495e41e24a55ea05bc7247")
                .build();
    }

}
