package com.ortho.medicare.medicareortho.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.ortho.medicare.medicareortho.R;
import com.ortho.medicare.medicareortho.adapters.DetailsListAdapter;
import com.ortho.medicare.medicareortho.customviews.CustomTextView;
import com.ortho.medicare.medicareortho.models.ProductDetailsModel;
import com.ortho.medicare.medicareortho.responsehandlers.CategoryDetailsResponse;
import com.ortho.medicare.medicareortho.responsehandlers.CategoryListResponse;
import com.ortho.medicare.medicareortho.utils.AppLog;
import com.ortho.medicare.medicareortho.utils.IntentStrings;
import com.ortho.medicare.medicareortho.utils.ToastUtils;
import com.ortho.medicare.medicareortho.webserviceutils.Constant;
import com.ortho.medicare.medicareortho.webserviceutils.RequestParams;
import com.ortho.medicare.medicareortho.webserviceutils.ServiceHandler;

import java.util.ArrayList;

public class CategoryDetailActivity extends AppCompatActivity implements ServiceHandler.GetResponse, View.OnClickListener {

    private RecyclerView mRecyclerView;
    private CustomTextView mNoData;
    private ArrayList<ProductDetailsModel> mDetailsList;
    private CustomTextView mToolBarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);

        initialize();
        if (getIntent().hasExtra(IntentStrings.CATEGORY_ID)) {
            callProductCatApi(getIntent().getStringExtra(IntentStrings.CATEGORY_ID));
        }
    }

    @Override
    public void processFinish(String output, int request, boolean success) {
        if (request == 0) {
            handleDetailsResponse(output);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_iv_back:
                onBackPressed();
                break;
        }
    }

    private void initialize() {
        mToolBarTitle = (CustomTextView) findViewById(R.id.toolbar_title);

        mToolBarTitle.setText(R.string.str_product_details);

        findViewById(R.id.toolbar_iv_back).setVisibility(View.VISIBLE);
        findViewById(R.id.toolbar_iv_back).setOnClickListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.act_cat_recyclerview);
        mNoData = (CustomTextView) findViewById(R.id.act_cat_no_data);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDetailsList = new ArrayList<>();
        mRecyclerView.setAdapter(new DetailsListAdapter(this, mDetailsList));
    }

    private void callProductCatApi(String id) {
        ServiceHandler serviceHandler = new ServiceHandler(this, Constant.Type.post
                , Constant.Urls.PRODUCT_DETAIL, RequestParams.getDetailsBody(this, id)
                , true, 0);
        serviceHandler.delegate = this;
        serviceHandler.execute();
    }

    private void handleDetailsResponse(String output) {
        AppLog.LogE("handleDetailsResponse", output);

        Gson gson = new Gson();
        try {
            CategoryDetailsResponse detailsResponse = gson.fromJson(output
                    , CategoryDetailsResponse.class);
            if (detailsResponse != null) {
                boolean response = detailsResponse.getResponse().equalsIgnoreCase("true");
                if (response) {
                    if (detailsResponse.getData().size() > 0) {
                        mDetailsList.clear();
                        mDetailsList.addAll(detailsResponse.getData());
                        mRecyclerView.getAdapter().notifyDataSetChanged();
                        mRecyclerView.setVisibility(View.VISIBLE);
                        mNoData.setVisibility(View.GONE);
                    } else {
                        mRecyclerView.setVisibility(View.GONE);
                        mNoData.setVisibility(View.VISIBLE);
                    }
                } else {
                    ToastUtils.makeText(this, "" + detailsResponse.getMsg());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.makeText(this, getString(R.string.str_something_went_worng));
        }
    }

}
