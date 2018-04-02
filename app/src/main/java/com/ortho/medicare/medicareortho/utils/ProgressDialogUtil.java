package com.ortho.medicare.medicareortho.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.Nullable;

import com.ortho.medicare.medicareortho.helpers.LoadingDialog;

/**
 * {@link ProgressDialogUtil} : constant progress dialog class used to show progress dialog in simpler way,
 * call method:
 * <i>showProgress(Context, String, String, boolean)</i> to show progress,
 * <i>dismissProgress() to dismiss it.</i>
 *
 * @author Harsh Patel
 * @version 1.0.0
 * @Date : 03/02/2018
 * @see #showProgress(Context, String, String, boolean)
 * @see #dismissProgress()
 * @see ProgressDialog
 */
public class ProgressDialogUtil {
    /**
     * The constant progressDialog.
     */
    public static LoadingDialog progressDialog;

    private ProgressDialogUtil() {

    }

    /**
     * singleton method used to retrieve {@link ProgressDialog} instance.
     *
     * @param c : param of {@link Context} type
     * @return the instance
     * @Date : 03/08/2017
     * @author Harsh Patel
     * @see ProgressDialogUtil
     */
    public static LoadingDialog getInstance(Context c) {
        if (progressDialog != null) {
            synchronized (LoadingDialog.class) {
                return progressDialog;
            }
        }
        return progressDialog = new LoadingDialog(c);
    }

    /**
     * static method used to show progress using {@link ProgressDialog}.
     *
     * @param c          : param of {@link Context} type
     * @param title      : param of {@link String} type used for {@link ProgressDialog} title can be {@link Nullable}
     * @param message    : param of {@link String} type used for {@link ProgressDialog} message can be {@link Nullable}
     * @param cancelable : param of boolean used as cancelable flag to {@link ProgressDialog}
     * @Date : 03/08/2017
     * @author Harsh Patel
     * @see ProgressDialogUtil
     */
    public static void showProgress(Context c, @Nullable String title, @Nullable String message, boolean cancelable) {
        try {
            getInstance(c);
            progressDialog.loading();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * static method to dismiss progress.
     *
     * @Date : 03/08/2017
     * @author Harsh Patel
     * @see ProgressDialogUtil
     */
    public static void dismissProgress() {
        if (progressDialog != null) {
            progressDialog.dismissDialog();
        }
        progressDialog = null;
    }
}
