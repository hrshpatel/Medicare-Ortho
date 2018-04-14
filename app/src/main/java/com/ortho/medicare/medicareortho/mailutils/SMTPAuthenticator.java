package com.ortho.medicare.medicareortho.mailutils;

import com.ortho.medicare.medicareortho.utils.CommonUtil;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator {
    public SMTPAuthenticator() {

        super();
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        if (CommonUtil.USERNAME.length() > 0 && CommonUtil.PASSWORD.length() > 0) {

            return new PasswordAuthentication(CommonUtil.USERNAME, CommonUtil.PASSWORD);
        }

        return null;
    }
}