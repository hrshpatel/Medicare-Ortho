package com.ortho.medicare.medicareortho.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.ortho.medicare.medicareortho.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * {@link CommonUtil} : this class contains common methods that are used in different classes
 *
 * @author Harsh Patel
 * @version : 0.0.1
 * @Date : 2/06/2017
 */
public class CommonUtil {

    public static final String USERNAME = "medicareorthoapk@gmail.com";
    public static String PASSWORD = "medicare123#";

    public final static int ADD_CANINE_RESULT = 121;
    public final static int EDIT_PROFILE_RESULT = 122;
    public final static int SCHEDULE_LIST_RESULT = 123;

    public final static Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9-]+[_A-Za-z0-9-]*(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    public final static Pattern INVALID_EMAIL_PATTERN = Pattern.compile("^[0-9-]+[_0-9-]*(\\.[_0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    public final static Pattern PASSWORD_VALIDATION = Pattern.compile("[A-Za-z0-9\\@\\#\\_\\'\\^\\*\\=\\:\\-\\+\\`]+$");
    public final static Pattern FIRST_LAST_NAME_PATTERN = Pattern.compile("^[A-Za-z0-9]+[A-Za-z-\\.\\-\\_\\']*$");
    public static final int CANINE_DETAILS_REQ = 1202;
    public static final int ADD_CANINE_REQ = 1010;
    public static final int EDIT_CANINE_RESULT = 124;
    public static final int ADD_CANINE_DETAILS_REQ = 2020;
    public static final int EDIT_PROFILE_REQ = 102;
    private static final Pattern CANINE_NAME_PATTERN = Pattern.compile("[A-Za-z0-9]+$");
    public static final int SUBSCRIPTION_REQ = 10002;

    public static String IMAGE_PATH = "";

    public static boolean foreGround;

    /**
     * checks if the email is correct or not
     *
     * @param email email to verify
     * @return true if email is correct
     * @Date :  6/06/2017
     * @author : Harsh Patel
     * @version : 0.0.1
     * @since : 0.0.1
     */
    public static boolean checkEmail(String email) {
        if (!INVALID_EMAIL_PATTERN.matcher(email).matches() && EMAIL_PATTERN.matcher(email).matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * checks if the name is correct or not
     *
     * @param name name to verify
     * @return true if name matches the pattern
     * @Date :  6/06/2017
     * @author : Harsh Patel
     * @version : 0.0.1
     * @since : 0.0.1
     */
    public static boolean checkFirstLastName(String name) {
        return FIRST_LAST_NAME_PATTERN.matcher(name).matches();
    }

    /**
     * checks if the password is correct or not
     *
     * @param password password to verify
     * @return true if password matches the pattern
     * @Date :  6/06/2017
     * @author : Harsh Patel
     * @version : 0.0.1
     * @since : 0.0.1
     */
    public static boolean checkPassword(String password) {
        return PASSWORD_VALIDATION.matcher(password).matches();
    }

    /**
     * checks if the string is blank.
     *
     * @param string .
     * @return true if string is blank
     * @Date :  6/06/2017
     * @author : Jeel Raja
     * @version : 0.0.1
     * @since : 0.0.1
     */
    public static boolean isNullString(String string) {
        try {
            if (string == null || string.trim().equalsIgnoreCase("null") || string.trim().length() < 0 || string.trim().equals("")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * to check if there is internet connection or not
     *
     * @Date :  6/06/2017
     * @author : Jeel Raja
     * @version : 0.0.1
     * @since : 0.0.1
     */
    public static boolean isInternetAvailable(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                return true;
            } else {
                Toast.makeText(context, "" + context.getResources().getString(R.string.internetmsg), Toast.LENGTH_SHORT).show();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * to show keyboard
     *
     * @Date :  6/06/2017
     * @author : Jeel Raja
     * @version : 0.0.1
     * @since : 0.0.1
     */
    public static void showSoftKeyboard(Context context) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), InputMethodManager.SHOW_FORCED, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * to hide keyboard
     *
     * @Date :  6/06/2017
     * @author : Jeel Raja
     * @version : 0.0.1
     * @since : 0.0.1
     */
    public static void hideSoftKeyboard(Activity context) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception ignored) {
//            ignored.printStackTrace();
        }
    }

    /**
     * to display toast on screen
     *
     * @Date :  6/06/2017
     * @author : Jeel Raja
     * @version : 0.0.1
     * @since : 0.0.1
     */
    public static Void displayToast(Context context, String strToast) {
        Toast.makeText(context, strToast, Toast.LENGTH_SHORT).show();
        return null;
    }

    /**
     * Converts file into byte array.
     *
     * @param file
     * @return byte[] of file
     * @Date :  12/06/2017
     * @author : Harsh Patel
     * @version : 0.0.1
     * @since : 0.0.1
     */
    public static byte[] convertFileToByte(File file, Bitmap bitmap) {
        byte[] b = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(b);

            fileInputStream.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        if (bitmap != null) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 75, bos);
            b = bos.toByteArray();
        }
        return b;
    }

    /**
     * loads image in the imageview passed in parameter
     *
     * @param activity
     * @param imageView
     * @param imageUri
     */
    public static void loadImage(Activity activity, ImageView imageView, String imageUri, int placeHolder) {
    }

    /**
     * This method is used to Format date in desired format.
     *
     * @param dateFromJSON
     * @return formatted date in string form
     * @Date : 14/06/2017
     * @author : Harsh Patel
     */
    public static String dateFormater(String dateFromJSON, String expectedFormat) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        String convertedDate = null;
        try {
            date = dateFormat.parse(dateFromJSON);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(expectedFormat);
            convertedDate = simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertedDate;
    }

    /**
     * This method is used to Format date in desired format.
     *
     * @param dateFromJSON
     * @return formatted date in string form
     * @Date : 14/06/2017
     * @author : Harsh Patel
     */
    public static String dateFormater(String dateFromJSON, String expectedFormat, String oldFormat) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(oldFormat);
        Date date = null;
        String convertedDate = null;
        try {
            date = dateFormat.parse(dateFromJSON);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(expectedFormat);
            convertedDate = simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertedDate;
    }

    /**
     * Checks if facebook app is installed or not.
     *
     * @Date : 23/06/2017
     * @author : Harsh Patel
     */
    public static boolean isFacebookInstalled(Activity mContext) {

        boolean app_installed = false;
        try {
            ApplicationInfo info = mContext.getPackageManager().getApplicationInfo("com.facebook.katana", 0);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

    /**
     * checks if date is between the given range.
     *
     * @param minDate
     * @param maxDate
     * @param selectedDate
     * @return returns true if the date is in given range
     * @Date : 15/06/2017
     * @author : Harsh Patel
     */
    public static boolean checkDate(Date minDate, Date maxDate, Date selectedDate) {
        if (minDate.getYear() == selectedDate.getYear() && minDate.getMonth() == selectedDate.getMonth()
                && minDate.getDate() == selectedDate.getDate()) {
            return true;
        } else {
            return minDate.compareTo(selectedDate) * selectedDate.compareTo(maxDate) >= 0;
        }
    }

    public static boolean checkNotifDate(Date notifDate, Date currentDate) {
        long diff = currentDate.getTime() - notifDate.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) >= 7;
    }

    /**
     * checks if canine name is valid or not.
     *
     * @param mStrCanineName
     * @return returns true if name is valid
     * @Date : 23/06/2017
     * @author : Harsh Patel
     */
    public static boolean checkCanineName(String mStrCanineName) {
        return CANINE_NAME_PATTERN.matcher(mStrCanineName).matches();
    }

    /**
     * This method is responsible for getting Path from Image URI
     *
     * @Date : 09/06/2017
     * @author : Jeel Raja
     */
    public static String getRealPathFromURI(Context context, Uri uri) {
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            String filePath = cursor.getString(idx);
            cursor.close();
            return filePath;
        } else {
            Toast.makeText(context, R.string.read_image_fail, Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    /**
     * This method is responsible for getting file Path from Image URI
     *
     * @Date : 12/07/2017
     * @author : Harsh Patel
     */
    public static String getFilePath(Context context, Uri data) {
        String path = "";

        // For non-gallery application
        path = data.getPath();

        // For gallery application
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(data, filePathColumn, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            path = cursor.getString(columnIndex);
            cursor.close();
        }
        return path;
    }

    /**
     * This method is responsible for getting Bitmap from Image URI
     *
     * @Date : 12/07/2017
     * @author : Harsh Patel
     */
    public static Bitmap getBitmapFromUri(Context context, Uri data) {
        Bitmap bitmap = null;

        // Starting fetch image from file
        InputStream is = null;
        try {
            is = context.getContentResolver().openInputStream(data);
            // First decode with inJustDecodeBounds=true to check dimensions
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            // BitmapFactory.decodeFile(path, options);
            BitmapFactory.decodeStream(is, null, options);
            // Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false;
            is = context.getContentResolver().openInputStream(data);
            bitmap = BitmapFactory.decodeStream(is, null, options);
            if (bitmap == null) {
                Toast.makeText(context, R.string.image_not_loaded, Toast.LENGTH_SHORT).show();
                return null;
            }
            is.close();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * This method is responsible for getting rotated Bitmap from Image.
     *
     * @Date : 12/07/2017
     * @author : Harsh Patel
     */
    public static Bitmap getRotatedBitmap(String path, Bitmap bitmap) {
        Bitmap rotatedBitmap = bitmap;
        Matrix matrix = new Matrix();
        ExifInterface exif = null;
        int orientation = 1;
        try {
            if (path != null) {
                // Getting Exif information of the file
                exif = new ExifInterface(path);
            }
            if (exif != null) {
                orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 0);
                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        matrix.preRotate(270);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        matrix.preRotate(90);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        matrix.preRotate(180);
                        break;
                }
                // Rotates the image according to the orientation
                rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight()
                        , matrix, true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rotatedBitmap;
    }

    /**
     * This method is responsible for resize image bitmap
     *
     * @Date : 09/06/2017
     * @author : Jeel Raja
     */
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 0) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

}
