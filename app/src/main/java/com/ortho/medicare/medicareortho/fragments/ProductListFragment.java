package com.ortho.medicare.medicareortho.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.ortho.medicare.medicareortho.R;
import com.ortho.medicare.medicareortho.adapters.ProductListAdapter;
import com.ortho.medicare.medicareortho.customviews.CustomTextView;
import com.ortho.medicare.medicareortho.models.CategoryModel;
import com.ortho.medicare.medicareortho.responsehandlers.AboutUsResponse;
import com.ortho.medicare.medicareortho.responsehandlers.CategoryListResponse;
import com.ortho.medicare.medicareortho.utils.AppLog;
import com.ortho.medicare.medicareortho.utils.CommonUtil;
import com.ortho.medicare.medicareortho.utils.ToastUtils;
import com.ortho.medicare.medicareortho.webserviceutils.Constant;
import com.ortho.medicare.medicareortho.webserviceutils.RequestParams;
import com.ortho.medicare.medicareortho.webserviceutils.ServiceHandler;

import java.util.ArrayList;

public class ProductListFragment extends Fragment implements ServiceHandler.GetResponse {

    private RecyclerView mRecyclerView;
    private ArrayList<CategoryModel> mCategoriesList;
    private CustomTextView mNoData;
    private CustomTextView mToolBarTitle;

    public ProductListFragment() {
        // Required empty public constructor
    }

    public static ProductListFragment newInstance() {
        ProductListFragment fragment = new ProductListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initialize(view);
    }

    @Override
    public void processFinish(String output, int request, boolean success) {
        if (request == 0) {
            handleCatListResp(output);
        }
    }

    private void initialize(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.frg_product_recycler_list);
        mNoData = (CustomTextView) view.findViewById(R.id.frg_product_txt_no_data);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mToolBarTitle = (CustomTextView) getActivity().findViewById(R.id.toolbar_title);

        mToolBarTitle.setText(R.string.str_products);

        mCategoriesList = new ArrayList<>();
        mRecyclerView.setAdapter(new ProductListAdapter(getActivity(), mCategoriesList));
        callProductCatApi();
    }

    private void callProductCatApi() {
        ServiceHandler serviceHandler = new ServiceHandler(getActivity(), Constant.Type.post
                , Constant.Urls.PRODUCT_LIST, RequestParams.getAboutUsBody(getActivity())
                , true, 0);
        serviceHandler.delegate = this;
        serviceHandler.execute();
    }

    private void handleCatListResp(String output) {
        AppLog.LogE("handleCatListResp", output);

        Gson gson = new Gson();
        try {
            CategoryListResponse listResponse = gson.fromJson(output
                    , CategoryListResponse.class);
            if (listResponse != null) {
                boolean response = listResponse.getResponse().equalsIgnoreCase("true");
                if (response) {
                    if (listResponse.getData().size() > 0) {
                        mCategoriesList.clear();
                        mCategoriesList.addAll(listResponse.getData());
                        mRecyclerView.getAdapter().notifyDataSetChanged();
                        mRecyclerView.setVisibility(View.VISIBLE);
                        mNoData.setVisibility(View.GONE);
                    } else {
                        mRecyclerView.setVisibility(View.GONE);
                        mNoData.setVisibility(View.VISIBLE);
                    }
                } else {
                    ToastUtils.makeText(getActivity(), "" + listResponse.getMsg());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.makeText(getActivity(), getString(R.string.str_something_went_worng));
        }
    }
}
