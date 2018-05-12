package com.ortho.medicare.medicareortho.mailutils;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.ortho.medicare.medicareortho.utils.AppLog;
import com.ortho.medicare.medicareortho.utils.CommonUtil;
import com.ortho.medicare.medicareortho.utils.ProgressDialogUtil;
import com.ortho.medicare.medicareortho.utils.ToastUtils;

public class SendMailAsync extends AsyncTask<String, Integer, Void> {

    private final String userName;
    private Activity mActivity;
    private String receiverMail;
    private String mBody;

    public SendMailAsync(Activity mActivity
            , String receiverMail, String userName, String mBody) {
        this.userName = userName;
        this.mActivity = mActivity;
        this.receiverMail = receiverMail;
        this.mBody = mBody;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        ProgressDialogUtil.dismissProgress();
    }

    protected Void doInBackground(String... params) {
        Mail m = new Mail(CommonUtil.USERNAME, CommonUtil.PASSWORD);

        String[] toArr = {receiverMail};
        m.setTo(toArr);
        m.setFrom(CommonUtil.USERNAME);
        m.setSubject("Thank you for filling Inquiry Form.");
        m.setBody(mBody);

        try {
            if (!m.send()) {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mActivity, "Email was not sent.", Toast.LENGTH_LONG).show();
                    }
                });
            } else {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.makeText(mActivity, "Your inquiry has been sent.");
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            AppLog.LogE("MailApp", "Could not send email" + e);
            ProgressDialogUtil.dismissProgress();
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mActivity, "Email was not sent.", Toast.LENGTH_LONG).show();
                }
            });
        }
        return null;
    }
}
