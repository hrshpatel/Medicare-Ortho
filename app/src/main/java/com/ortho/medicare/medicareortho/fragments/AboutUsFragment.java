package com.ortho.medicare.medicareortho.fragments;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ortho.medicare.medicareortho.R;
import com.ortho.medicare.medicareortho.responsehandlers.AboutUsResponse;
import com.ortho.medicare.medicareortho.utils.AppLog;
import com.ortho.medicare.medicareortho.utils.CommonUtil;
import com.ortho.medicare.medicareortho.utils.ToastUtils;
import com.ortho.medicare.medicareortho.webserviceutils.Constant;
import com.ortho.medicare.medicareortho.webserviceutils.RequestParams;
import com.ortho.medicare.medicareortho.webserviceutils.ServiceHandler;

public class AboutUsFragment extends Fragment implements ServiceHandler.GetResponse {

    private TextView mTxtDescription;
    private TextView mTxtOverview;
    private TextView mToolBarTitle;

    public AboutUsFragment() {

    }

    public static AboutUsFragment newInstance() {
        AboutUsFragment fragment = new AboutUsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_us, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeView(view);
        callAboutUsWs();
    }

    @Override
    public void processFinish(String output, int request, boolean success) {
        if (request == 0) {
            handleAboutUs(output);
        }
    }

    private void initializeView(View view) {
        mTxtOverview = (TextView) view.findViewById(R.id.frg_about_txt_overview);
        mTxtDescription = (TextView) view.findViewById(R.id.frg_about_txt_desc);

        mToolBarTitle = (TextView) getActivity().findViewById(R.id.toolbar_title);

        mToolBarTitle.setText(R.string.str_about_us);
    }

    private void callAboutUsWs() {
        ServiceHandler serviceHandler = new ServiceHandler(getActivity(), Constant.Type.post
                , Constant.Urls.ABOUT_US, RequestParams.getAboutUsBody(), true, 0);
        serviceHandler.delegate = this;
        serviceHandler.execute();
    }

    private void handleAboutUs(String output) {
        AppLog.LogE("handleAboutUs", output);
        Gson gson = new Gson();
        try {
            AboutUsResponse aboutUsResponse = gson.fromJson(output, AboutUsResponse.class);
            if (aboutUsResponse != null) {
                boolean response = aboutUsResponse.getResponse().equalsIgnoreCase("true");
                if (response) {
                    if (!CommonUtil.isNullString(aboutUsResponse.getData().getOverview())) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            mTxtOverview.setText(Html.fromHtml(
                                    aboutUsResponse.getData().getOverview()
                                    , Html.FROM_HTML_MODE_COMPACT));
                        } else {
                            mTxtOverview.setText(Html.fromHtml(
                                    aboutUsResponse.getData().getOverview()));
                        }
                    }
                    if (!CommonUtil.isNullString(aboutUsResponse.getData().getDescription())) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            mTxtDescription.setText(Html.fromHtml(
                                    aboutUsResponse.getData().getDescription()
                                    , Html.FROM_HTML_MODE_COMPACT));
                        } else {
                            mTxtDescription.setText(Html.fromHtml(
                                    aboutUsResponse.getData().getDescription()));
                        }
                    }
                } else {
                    ToastUtils.makeText(getActivity(), "" + aboutUsResponse.getMsg()
                            , Toast.LENGTH_SHORT);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.makeText(getActivity(), getString(R.string.str_something_went_worng)
                    , Toast.LENGTH_SHORT);
        }
    }

}
