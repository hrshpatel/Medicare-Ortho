package com.ortho.medicare.medicareortho.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ortho.medicare.medicareortho.R;
import com.ortho.medicare.medicareortho.customviews.CustomTextView;
import com.ortho.medicare.medicareortho.models.ProductDetailsModel;
import com.ortho.medicare.medicareortho.utils.CommonUtil;

import java.util.ArrayList;

/**
 * Created by Harsh on 14-04-2018.
 */

public class DetailsListAdapter extends RecyclerView.Adapter<DetailsListAdapter.DetailsHolder> {

    private ArrayList<ProductDetailsModel> productDetailsModels;
    private Activity mActivity;

    public DetailsListAdapter(Activity mActivity,
                              ArrayList<ProductDetailsModel> productDetailsModels) {
        this.productDetailsModels = productDetailsModels;
        this.mActivity = mActivity;
    }

    @Override
    public DetailsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new DetailsHolder(view);
    }

    @Override
    public void onBindViewHolder(DetailsHolder holder, final int position) {
        CommonUtil.loadImage(mActivity, holder.ivItemImage
                , productDetailsModels.get(position).getImage(), R.mipmap.ic_launcher);

        holder.itemRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));

        if (productDetailsModels.get(position).getProductDetails() != null
                && productDetailsModels.get(position).getProductDetails().size() > 0
                && CommonUtil.isNullString(productDetailsModels.get(position)
                .getProductDetails().get(0).getSize())) {
            holder.itemSize.setVisibility(View.GONE);
        } else {
            holder.itemSize.setVisibility(View.VISIBLE);
        }

        if (productDetailsModels.get(position).getProductDetails() != null
                && productDetailsModels.get(position).getProductDetails().size() > 0
                && CommonUtil.isNullString(productDetailsModels.get(position)
                .getProductDetails().get(0).getPrice())) {
            holder.itemPrice.setVisibility(View.GONE);
        } else {
            holder.itemPrice.setVisibility(View.VISIBLE);
        }

        if (productDetailsModels.get(position).getProductDetails() != null
                && productDetailsModels.get(position).getProductDetails().size() > 0
                && CommonUtil.isNullString(productDetailsModels.get(position)
                .getProductDetails().get(0).getSrNo())) {
            holder.itemSrNo.setVisibility(View.GONE);
        } else {
            holder.itemSrNo.setVisibility(View.VISIBLE);
        }

        if (productDetailsModels.get(position).getProductDetails() != null
                && productDetailsModels.get(position).getProductDetails().size() > 0
                && CommonUtil.isNullString(productDetailsModels.get(position)
                .getProductDetails().get(0).getName())) {
            holder.itemName.setVisibility(View.GONE);
        } else {
            holder.itemName.setVisibility(View.VISIBLE);
        }

        holder.itemRecyclerView.setAdapter(new ItemsAdapter(mActivity
                , productDetailsModels.get(position).getProductDetails()));
    }

    @Override
    public int getItemCount() {
        return productDetailsModels.size();
    }

    public class DetailsHolder extends RecyclerView.ViewHolder {
        private CustomTextView itemPrice;
        private RecyclerView itemRecyclerView;
        private CustomTextView itemSize;
        private CustomTextView itemSrNo;
        private ImageView ivItemImage;
        private View mainView;
        private CustomTextView itemName;

        public DetailsHolder(View itemView) {
            super(itemView);
            mainView = itemView.findViewById(R.id.item_product);
            itemPrice = (CustomTextView) itemView.findViewById(R.id.item_product_tv_price);
            itemName = (CustomTextView) itemView.findViewById(R.id.item_product_tv_name);
            itemSrNo = (CustomTextView) itemView.findViewById(R.id.item_product_tv_srno);
            itemSize = (CustomTextView) itemView.findViewById(R.id.item_product_tv_size);
            ivItemImage = (ImageView) itemView.findViewById(R.id.item_product_iv_image);
            itemRecyclerView = (RecyclerView) itemView.findViewById(R.id.item_product_items_recyclerview);
        }
    }
}
