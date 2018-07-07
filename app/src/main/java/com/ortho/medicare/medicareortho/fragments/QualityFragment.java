package com.ortho.medicare.medicareortho.fragments;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.google.gson.Gson;
import com.ortho.medicare.medicareortho.R;
import com.ortho.medicare.medicareortho.customviews.CustomTextView;
import com.ortho.medicare.medicareortho.responsehandlers.CategoryListResponse;
import com.ortho.medicare.medicareortho.responsehandlers.QualityResponse;
import com.ortho.medicare.medicareortho.utils.AppLog;
import com.ortho.medicare.medicareortho.utils.CommonUtil;
import com.ortho.medicare.medicareortho.utils.ToastUtils;
import com.ortho.medicare.medicareortho.webserviceutils.Constant;
import com.ortho.medicare.medicareortho.webserviceutils.RequestParams;
import com.ortho.medicare.medicareortho.webserviceutils.ServiceHandler;

import java.net.URL;

public class QualityFragment extends Fragment implements ServiceHandler.GetResponse, OnPageChangeListener, OnLoadCompleteListener {

    private CustomTextView mToolBarTitle;
    private CustomTextView mTxtSubTitle;
    private CustomTextView mTxtDescription;
    private PDFView mPdfView;

    public QualityFragment() {
        // Required empty public constructor
    }

    public static QualityFragment newInstance() {
        QualityFragment fragment = new QualityFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quality, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initialize(view);

    }

    @Override
    public void processFinish(String output, int request, boolean success) {
        if (request == 0) {
            handleQualityResponse(output);
        }
    }

    @Override
    public void onPageChanged(int page, int pageCount) {

    }

    @Override
    public void loadComplete(int nbPages) {

    }

    private void initialize(View view) {
        getActivity().findViewById(R.id.toolbar_iv_search).setVisibility(View.GONE);

        mToolBarTitle = getActivity().findViewById(R.id.toolbar_title);
        mToolBarTitle.setText(R.string.str_quailty);

        mTxtSubTitle = view.findViewById(R.id.frg_quality_txt_title);
        mTxtDescription = view.findViewById(R.id.frg_quality_txt_desc);

        mPdfView = view.findViewById(R.id.frg_quality_pdf_view);

        callQualityApi();
    }

    private void callQualityApi() {
        ServiceHandler serviceHandler = new ServiceHandler(getActivity(), Constant.Type.post
                , Constant.Urls.QUALITY, RequestParams.getAboutUsBody(getActivity())
                , true, 0);
        serviceHandler.delegate = this;
        serviceHandler.execute();
    }

    private void handleQualityResponse(String output) {
        AppLog.LogE("handleQualityResponse", output);

        Gson gson = new Gson();
        try {
            QualityResponse qualityResponse = gson.fromJson(output
                    , QualityResponse.class);
            if (qualityResponse != null) {
                boolean response = qualityResponse.getResponse().equalsIgnoreCase("true");
                if (response && qualityResponse.getData() != null) {
                    if (!CommonUtil.isNullString(qualityResponse.getData().getSubTitle())) {
                        mTxtSubTitle.setText(qualityResponse.getData().getSubTitle());
                    } else {
                        mTxtSubTitle.setVisibility(View.GONE);
                    }

                    if (!CommonUtil.isNullString(qualityResponse.getData().getDescription())) {
                        mTxtDescription.setText(qualityResponse.getData().getDescription());
                    } else {
                        mTxtDescription.setVisibility(View.GONE);
                    }

                    if (!CommonUtil.isNullString(qualityResponse.getData().getDocument())) {
                        mPdfView.fromAsset("medicare_orthopaedic_implantsinstruments.pdf")
                                .defaultPage(0)
                                .enableSwipe(true)

                                .swipeHorizontal(false)
                                .onPageChange(this)
                                .enableAnnotationRendering(true)
                                .onLoad(this)
                                .scrollHandle(new DefaultScrollHandle(getActivity()))
                                .load();
                    } else {
                        mPdfView.setVisibility(View.GONE);
                    }
                } else {
                    ToastUtils.makeText(getActivity(), "" + qualityResponse.getMsg());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.makeText(getActivity(), getString(R.string.str_something_went_worng));
        }

    }

}
