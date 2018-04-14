package com.ortho.medicare.medicareortho.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ortho.medicare.medicareortho.R;
import com.ortho.medicare.medicareortho.customviews.CustomTextView;
import com.ortho.medicare.medicareortho.responsehandlers.AboutUsResponse;
import com.ortho.medicare.medicareortho.responsehandlers.ContactResponse;
import com.ortho.medicare.medicareortho.utils.AppLog;
import com.ortho.medicare.medicareortho.utils.CommonUtil;
import com.ortho.medicare.medicareortho.utils.ToastUtils;
import com.ortho.medicare.medicareortho.webserviceutils.Constant;
import com.ortho.medicare.medicareortho.webserviceutils.RequestParams;
import com.ortho.medicare.medicareortho.webserviceutils.ServiceHandler;

public class ContactFragment extends Fragment implements ServiceHandler.GetResponse {

    private CustomTextView mTxtAddress;
    private CustomTextView mTxtPhone;
    private CustomTextView mTxtMobile;
    private CustomTextView mTxtWebsite;
    private CustomTextView mTxtEmail;
    private CustomTextView mToolBarTitle;

    public ContactFragment() {
    }

    public static ContactFragment newInstance() {
        ContactFragment fragment = new ContactFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeView(view);
        callContactUsWs();
    }

    @Override
    public void processFinish(String output, int request, boolean success) {
        if (request == 0) {
            handleContactUs(output);
        }
    }

    private void initializeView(View view) {

        mTxtAddress = (CustomTextView) view.findViewById(R.id.frg_contact_txt_address);
        mTxtPhone = (CustomTextView) view.findViewById(R.id.frg_contact_txt_phone);
        mTxtMobile = (CustomTextView) view.findViewById(R.id.frg_contact_txt_mobile);
        mTxtEmail = (CustomTextView) view.findViewById(R.id.frg_contact_txt_email);
        mTxtWebsite = (CustomTextView) view.findViewById(R.id.frg_contact_txt_website);

        mToolBarTitle = (CustomTextView) getActivity().findViewById(R.id.toolbar_title);

        mToolBarTitle.setText(R.string.str_contact_us);
    }

    private void callContactUsWs() {
        ServiceHandler serviceHandler = new ServiceHandler(getActivity(), Constant.Type.post
                , Constant.Urls.CONTACT_US, RequestParams.getContactUsBody(getActivity())
                , true, 0);
        serviceHandler.delegate = this;
        serviceHandler.execute();
    }

    private void handleContactUs(String output) {
        AppLog.LogE("handleContactUs", output);
        Gson gson = new Gson();
        try {
            ContactResponse contactResponse = gson.fromJson(output, ContactResponse.class);
            if (contactResponse != null) {
                boolean response = contactResponse.getResponse().equalsIgnoreCase("true");
                if (response) {
                    if (!CommonUtil.isNullString(contactResponse.getData().getEmailId())) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            mTxtEmail.setText(Html.fromHtml(
                                    contactResponse.getData().getEmailId()
                                    , Html.FROM_HTML_MODE_COMPACT));
                        } else {
                            mTxtEmail.setText(Html.fromHtml(
                                    contactResponse.getData().getEmailId()));
                        }
                    }
                    if (!CommonUtil.isNullString(contactResponse.getData().getMobileNumber())) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            mTxtMobile.setText(Html.fromHtml(
                                    contactResponse.getData().getMobileNumber()
                                    , Html.FROM_HTML_MODE_COMPACT));
                        } else {
                            mTxtMobile.setText(Html.fromHtml(
                                    contactResponse.getData().getMobileNumber()));
                        }
                    }

                    if (!CommonUtil.isNullString(contactResponse.getData().getPhoneNumber())) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            mTxtPhone.setText(Html.fromHtml(
                                    contactResponse.getData().getPhoneNumber()
                                    , Html.FROM_HTML_MODE_COMPACT));
                        } else {
                            mTxtPhone.setText(Html.fromHtml(
                                    contactResponse.getData().getPhoneNumber()));
                        }
                    }

                    if (!CommonUtil.isNullString(contactResponse.getData().getWebsiteLink())) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            mTxtWebsite.setText(Html.fromHtml(
                                    contactResponse.getData().getWebsiteLink()
                                    , Html.FROM_HTML_MODE_COMPACT));
                        } else {
                            mTxtWebsite.setText(Html.fromHtml(
                                    contactResponse.getData().getWebsiteLink()));
                        }
                    }

                    if (!CommonUtil.isNullString(contactResponse.getData().getOfficeAddress())) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            mTxtAddress.setText(Html.fromHtml(
                                    contactResponse.getData().getOfficeAddress()
                                    , Html.FROM_HTML_MODE_COMPACT));
                        } else {
                            mTxtAddress.setText(Html.fromHtml(
                                    contactResponse.getData().getOfficeAddress()));
                        }
                    }
                } else {
                    ToastUtils.makeText(getActivity(), "" + contactResponse.getMsg());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.makeText(getActivity(), getString(R.string.str_something_went_worng));
        }
    }

}
