package com.ortho.medicare.medicareortho.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ortho.medicare.medicareortho.R;
import com.ortho.medicare.medicareortho.customviews.CustomTextView;
import com.ortho.medicare.medicareortho.models.ProductDetailsModel;
import com.ortho.medicare.medicareortho.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Harsh on 21-04-2018.
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemHolder> {

    private Activity mActivity;
    private List<ProductDetailsModel.ProductDetails> productDetailsArrayList;

    public ItemsAdapter(Activity mActivity
            , List<ProductDetailsModel.ProductDetails> productDetailsArrayList) {
        this.mActivity = mActivity;
        this.productDetailsArrayList = productDetailsArrayList;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.details_item
                , parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        if (!CommonUtil.isNullString(productDetailsArrayList.get(position).getSize())) {
            holder.itemSize.setVisibility(View.VISIBLE);
            holder.itemSize.setText(productDetailsArrayList.get(position).getSize());
        } else {
            holder.itemSize.setVisibility(View.GONE);
        }

        if (!CommonUtil.isNullString(productDetailsArrayList.get(position).getSrNo())) {
            holder.itemSrNo.setText(productDetailsArrayList.get(position).getSrNo());
            holder.itemSrNo.setVisibility(View.VISIBLE);
        } else {
            holder.itemSrNo.setVisibility(View.GONE);
        }

        if (!CommonUtil.isNullString(productDetailsArrayList.get(position).getName())) {
            holder.itemName.setText(productDetailsArrayList.get(position).getName());
            holder.itemName.setVisibility(View.VISIBLE);
        } else {
            holder.itemName.setVisibility(View.GONE);
        }

        if (!CommonUtil.isNullString(productDetailsArrayList.get(position).getPrice())) {
            holder.itemPrice.setText(productDetailsArrayList.get(position).getPrice()
                    + " " + mActivity.getString(R.string.rupee));
            holder.itemPrice.setVisibility(View.VISIBLE);
        } else {
            holder.itemPrice.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return productDetailsArrayList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        private CustomTextView itemPrice;
        private CustomTextView itemSize;
        private CustomTextView itemSrNo;
        private CustomTextView itemName;

        public ItemHolder(View itemView) {
            super(itemView);
            itemName = (CustomTextView) itemView.findViewById(R.id.item_product_tv_name);
            itemPrice = (CustomTextView) itemView.findViewById(R.id.item_product_tv_price);
            itemSrNo = (CustomTextView) itemView.findViewById(R.id.item_product_tv_sr_no);
            itemSize = (CustomTextView) itemView.findViewById(R.id.item_product_tv_size);
        }
    }
}
