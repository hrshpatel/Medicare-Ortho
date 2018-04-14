package com.ortho.medicare.medicareortho.helpers;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ortho.medicare.medicareortho.R;
import com.ortho.medicare.medicareortho.customviews.CirculerProgressView;
import com.ortho.medicare.medicareortho.customviews.CustomTextView;

public class LoadingDialog {

    public Dialog mDialog;
    CirculerProgressView progressBar;
    Thread updateThread;
    CustomTextView tv_loading;
    private Context context;
    View view;
    private boolean isLDShow = false;

    public LoadingDialog(Context context) {
        this.context = context;
    }

    public void loading() {
        try {
            if (isLDShow) {
                hideLoadingDialog();
            } else {
                createDialog(false, "");
                mDialog.show();
                isLDShow = true;
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            if (isLDShow && mDialog != null) {
                hideLoadingDialog();
            }
        }
    }

    public void loadingText(String text) {
        try {
            if (isLDShow) {
                hideLoadingDialog();
            } else {
                createDialog(true, text);
                mDialog.show();
                isLDShow = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (isLDShow && mDialog != null) {
                hideLoadingDialog();
            }
        }
    }

    private void hideLoadingDialog() {
        tv_loading = (CustomTextView) view.findViewById(R.id.tv_loding);
        isLDShow = false;
        if (mDialog != null) {
            tv_loading.setVisibility(View.GONE);
            if (mDialog.isShowing()) {
                Context context = ((ContextWrapper) mDialog.getContext()).getBaseContext();
                if (context instanceof Activity) {

                    // Api >=17
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        if (!((Activity) context).isFinishing() && !((Activity) context).isDestroyed()) {
                            dismissWithExceptionHandling(mDialog);
                        }
                    } else {

                        // Api < 17. Unfortunately cannot check for isDestroyed()
                        if (!((Activity) context).isFinishing()) {
                            dismissWithExceptionHandling(mDialog);
                        }
                    }
                } else
                    // if the Context used wasn't an Activity, then dismiss it too
                    dismissWithExceptionHandling(mDialog);
            }
            mDialog = null;
        }
    }


    /**
     * Dismiss {@link android.app.ProgressDialog} with try catch
     *
     * @param dialog instance of {@link android.app.ProgressDialog} to dismiss
     */
    public void dismissWithExceptionHandling(Dialog dialog) {
        try {
            dialog.dismiss();
        } catch (final Exception e) {
            e.printStackTrace();
        } finally {
            dialog = null;
        }
    }

    private void createDialog(boolean text, String text_to_Set) {
        mDialog = null;
        mDialog = new Dialog(context, R.style.loading_dialog);
        view = LayoutInflater.from(context).inflate(
                R.layout.dialog_loading, null);
        progressBar = (CirculerProgressView) view.findViewById(R.id.progressBar1);
        startAnimationThreadStuff(1000);
        tv_loading = (CustomTextView) view.findViewById(R.id.tv_loding);
        if (text) {
            tv_loading.setVisibility(View.VISIBLE);
            tv_loading.setText(text_to_Set);
        } else {
            tv_loading.setVisibility(View.GONE);
        }

        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setCancelable(false);
        mDialog.setContentView(view);
    }

    public void dismissDialog() {
        hideLoadingDialog();
    }

    private void startAnimationThreadStuff(long delay) {
        if (updateThread != null && updateThread.isAlive()) {
            updateThread.interrupt();
        }
        // Start animation after a delay so there's no missed frames while the app loads up
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!progressBar.isIndeterminate()) {
                    progressBar.setProgress(0f);
                    // Run thread to update progress every quarter second until full
                    updateThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (progressBar.getProgress() < progressBar.getMaxProgress() && !Thread
                                    .interrupted()) {
                                // Must set progress in UI thread
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressBar.setProgress(progressBar.getProgress() + 10);
                                    }
                                });
                                SystemClock.sleep(250);
                            }
                        }
                    });
                    updateThread.start();
                }
                // Alias for resetAnimation, it's all the same
                progressBar.startAnimation();
            }
        }, delay);
    }

}
