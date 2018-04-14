package com.ortho.medicare.medicareortho.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ortho.medicare.medicareortho.R;
import com.ortho.medicare.medicareortho.customviews.CustomTextView;
import com.ortho.medicare.medicareortho.mailutils.SendMailAsync;
import com.ortho.medicare.medicareortho.utils.CommonUtil;
import com.ortho.medicare.medicareortho.utils.ProgressDialogUtil;
import com.ortho.medicare.medicareortho.utils.ToastUtils;

public class InquiryFragment extends Fragment implements View.OnClickListener {

    private CustomTextView mToolBarTitle;
    private TextInputEditText mEdtName;
    private TextInputEditText mEdtAddress;
    private TextInputEditText mEdtCity;
    private TextInputEditText mEdtCountry;
    private TextInputEditText mEdtEmail;
    private TextInputEditText mEdtPhone;
    private TextInputEditText mEdtPinCode;
    private TextInputEditText mEdtRemark;
    private TextInputEditText mEdtState;
    private Button mBtnSubmit;

    public InquiryFragment() {

    }

    public static InquiryFragment newInstance() {
        InquiryFragment fragment = new InquiryFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inquiry, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeView(view);
        setListeners();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.frg_inquiry_btn_submit:
                validateData();
                break;
        }
    }

    private void initializeView(View view) {
        mToolBarTitle = (CustomTextView) getActivity().findViewById(R.id.toolbar_title);

        mToolBarTitle.setText(R.string.str_inquiry);

        mEdtName = (TextInputEditText) view.findViewById(R.id.frg_inquiry_edt_name);
        mEdtAddress = (TextInputEditText) view.findViewById(R.id.frg_inquiry_edt_address);
        mEdtCity = (TextInputEditText) view.findViewById(R.id.frg_inquiry_edt_city);
        mEdtCountry = (TextInputEditText) view.findViewById(R.id.frg_inquiry_edt_country);
        mEdtEmail = (TextInputEditText) view.findViewById(R.id.frg_inquiry_edt_email);
        mEdtPhone = (TextInputEditText) view.findViewById(R.id.frg_inquiry_edt_phone);
        mEdtPinCode = (TextInputEditText) view.findViewById(R.id.frg_inquiry_edt_pin);
        mEdtRemark = (TextInputEditText) view.findViewById(R.id.frg_inquiry_edt_remark);
        mEdtState = (TextInputEditText) view.findViewById(R.id.frg_inquiry_edt_state);

        mBtnSubmit = (Button) view.findViewById(R.id.frg_inquiry_btn_submit);

    }

    private void setListeners() {
        mBtnSubmit.setOnClickListener(this);
    }

    private void validateData() {

        String email = mEdtEmail.getText().toString();
        String name = mEdtName.getText().toString();
        String remark = mEdtRemark.getText().toString();

        if (CommonUtil.isNullString(email)) {
            ToastUtils.makeText(getActivity(), "Enter email address.");
        } else if (!CommonUtil.checkEmail(email)) {
            ToastUtils.makeText(getActivity(), "Enter valid email address.");
        } else if (CommonUtil.isNullString(name)) {
            ToastUtils.makeText(getActivity(), "Enter name.");
        } else if (CommonUtil.isNullString(remark)) {
            ToastUtils.makeText(getActivity(), "Enter remarks.");
        } else {
            ProgressDialogUtil.showProgress(getActivity(), "", "", false);
            try {
                String body = "Hello " + mEdtName.getText().toString()
                        + ",\nYour filled details is as follows."
                        + "\n\nName - " + name
                        + "\nEmail - " + email
                        + "\nAddress - " + mEdtAddress.getText()
                        + "\nCity - " + mEdtCity.getText()
                        + "\nState - " + mEdtState.getText()
                        + "\nCountry - " + mEdtCountry.getText()
                        + "\nPinCode - " + mEdtPinCode.getText()
                        + "\nContact - " + mEdtPhone.getText()
                        + "\nRemarks - " + remark
                        + "\n\nThank you,\nTeam MedicareOrtho";
                new SendMailAsync(getActivity(), mEdtEmail.getText().toString()
                        , mEdtName.getText().toString(), body).execute();
            } catch (Exception e) {
                e.printStackTrace();
                ProgressDialogUtil.dismissProgress();
            }
        }
    }

}
