package com.ortho.medicare.medicareortho.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ortho.medicare.medicareortho.R;
import com.ortho.medicare.medicareortho.activities.CategoryDetailActivity;
import com.ortho.medicare.medicareortho.adapters.ProductListAdapter;
import com.ortho.medicare.medicareortho.adapters.SearchViewAdapter;
import com.ortho.medicare.medicareortho.customviews.CustomSearchView;
import com.ortho.medicare.medicareortho.customviews.CustomTextView;
import com.ortho.medicare.medicareortho.models.CategoryModel;
import com.ortho.medicare.medicareortho.models.ProductDetailsModel;
import com.ortho.medicare.medicareortho.responsehandlers.AboutUsResponse;
import com.ortho.medicare.medicareortho.responsehandlers.CategoryListResponse;
import com.ortho.medicare.medicareortho.utils.AppLog;
import com.ortho.medicare.medicareortho.utils.CommonUtil;
import com.ortho.medicare.medicareortho.utils.IntentStrings;
import com.ortho.medicare.medicareortho.utils.ToastUtils;
import com.ortho.medicare.medicareortho.webserviceutils.Constant;
import com.ortho.medicare.medicareortho.webserviceutils.RequestParams;
import com.ortho.medicare.medicareortho.webserviceutils.ServiceHandler;

import java.util.ArrayList;
import java.util.List;

public class ProductListFragment extends Fragment implements ServiceHandler.GetResponse {

    private RecyclerView mRecyclerView;
    private ArrayList<CategoryModel> mCategoriesList;
    private CustomTextView mNoData;
    private CustomTextView mToolBarTitle;
    private List<ProductDetailsModel> mProductDetailsList;
    private CustomSearchView mSearchView;

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
        setListeners();
    }

    @Override
    public void processFinish(String output, int request, boolean success) {
        if (request == 0) {
            handleCatListResp(output);
        }
    }

    private void initialize(View view) {

        mSearchView = getActivity().findViewById(R.id.toolbar_iv_search);
        mSearchView.setVisibility(View.VISIBLE);

        mRecyclerView = view.findViewById(R.id.frg_product_recycler_list);
        mNoData = view.findViewById(R.id.frg_product_txt_no_data);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mToolBarTitle = getActivity().findViewById(R.id.toolbar_title);

        mToolBarTitle.setText(R.string.str_products);

        mCategoriesList = new ArrayList<>();
        mRecyclerView.setAdapter(new ProductListAdapter(getActivity(), mCategoriesList));
        callProductCatApi();
    }

    private void setListeners() {
        mSearchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ProductDetailsModel detailsModel = ((SearchViewAdapter) mSearchView
                        .getAdapter()).getCurrentItem(position);

                Intent intent = new Intent(getActivity()
                        , CategoryDetailActivity.class);
                intent.putExtra(IntentStrings.ITEM_ID
                        , "" + detailsModel.getId());
                intent.putExtra(IntentStrings.CATEGORY_ID
                        , "" + detailsModel.getProductId());
                startActivity(intent);
            }
        });

        mSearchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().findViewById(R.id.toolbar_title).setVisibility(View.GONE);
            }
        });

        mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                getActivity().findViewById(R.id.toolbar_title).setVisibility(View.VISIBLE);
                return false;
            }
        });

    }

    private void callProductCatApi() {
        mProductDetailsList = new ArrayList<>();

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
                        for (int i = 0; i < mCategoriesList.size(); i++) {
                            if (mCategoriesList.get(i).getmProductList() != null
                                    && mCategoriesList.get(i).getmProductList().size() > 0) {
                                mProductDetailsList.addAll(mCategoriesList.get(i)
                                        .getmProductList());
                            }
                        }
                    } else {
                        mRecyclerView.setVisibility(View.GONE);
                        mNoData.setVisibility(View.VISIBLE);
                    }
                } else {
                    ToastUtils.makeText(getActivity(), "" + listResponse.getMsg());
                }
            }

            SearchViewAdapter adapter = new SearchViewAdapter(getActivity()
                    , android.R.layout.simple_expandable_list_item_1, mProductDetailsList);

            mSearchView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.makeText(getActivity(), getString(R.string.str_something_went_worng));
        }
    }
}
