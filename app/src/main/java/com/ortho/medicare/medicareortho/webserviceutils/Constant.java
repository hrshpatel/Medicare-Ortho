package com.ortho.medicare.medicareortho.webserviceutils;

/**
 * Constant.java : this class is used to handle url of app
 *
 * @author : Harsh Patel
 * @version : 0.0.1
 * @Date : 13/02/2018
 */
public class Constant {

    /**
     * this class contains method types of Webservices
     *
     * @author : Harsh Patel
     * @version : 0.0.1
     * @Date :  13/02/2018
     * @since : 0.0.1
     */
    public static class Type {
        public static String post = "POST";
        public static String get = "GET";
    }

    /**
     * this class contains keys of Webservices
     *
     * @author : Harsh Patel
     * @version : 0.0.1
     * @Date :  13/02/2018
     * @since : 0.0.1
     */
    public static class WebServicesKeys {

        public static String mAppKey = "APPKEY";
        public static String mId = "id";
    }

    /**
     * this class contains Urls of Webservices
     *
     * @author : Harsh Patel
     * @version : 0.0.1
     * @Date :  013/02/2018
     * @since : 0.0.1
     */
    public static class Urls {
        //        private static String BASE_URL = "http://aquaflexinks.in/medicareortho/api/";
        private static String BASE_URL = "http://medicareortho.com/api/";

        public static String ABOUT_US = BASE_URL + "about_us";
        public static String CONTACT_US = BASE_URL + "contact";
        public static String PRODUCT_LIST = BASE_URL + "product_description";
        public static String PRODUCT_DETAIL = BASE_URL + "product_detail";
        public static String QUALITY = BASE_URL + "quality";
    }
}
