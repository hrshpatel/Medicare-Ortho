package com.ortho.medicare.medicareortho.adapters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ortho.medicare.medicareortho.R;
import com.ortho.medicare.medicareortho.activities.CategoryDetailActivity;
import com.ortho.medicare.medicareortho.customviews.CustomTextView;
import com.ortho.medicare.medicareortho.models.CategoryModel;
import com.ortho.medicare.medicareortho.utils.IntentStrings;

import java.util.ArrayList;

/**
 * Created by Harsh on 14-04-2018.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListHolder> {

    private ArrayList<CategoryModel> categoryModelArrayList;
    private Activity mActivity;

    public ProductListAdapter(Activity mActivity,
                              ArrayList<CategoryModel> categoryModelArrayList) {
        this.categoryModelArrayList = categoryModelArrayList;
        this.mActivity = mActivity;
    }

    @Override
    public ProductListAdapter.ProductListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product_cat, parent, false);
        return new ProductListHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductListAdapter.ProductListHolder holder, final int position) {
        holder.itemName.setText(categoryModelArrayList.get(position).getName());
        if (position % 2 == 0) {
            holder.mainView.setBackgroundColor(Color.WHITE);
        } else {
            holder.mainView.setBackgroundColor(Color.LTGRAY);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, CategoryDetailActivity.class);
                intent.putExtra(IntentStrings.CATEGORY_ID
                        , categoryModelArrayList.get(position).getId());
                mActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryModelArrayList.size();
    }

    public class ProductListHolder extends RecyclerView.ViewHolder {
        private View mainView;
        private CustomTextView itemName;

        public ProductListHolder(View itemView) {
            super(itemView);
            mainView = itemView.findViewById(R.id.item_cat);
            itemName = (CustomTextView) itemView.findViewById(R.id.item_cat_name);
        }
    }
}
