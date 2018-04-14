package com.ortho.medicare.medicareortho.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ortho.medicare.medicareortho.R;

public class ProductListFragment extends Fragment {

    private RecyclerView mRecyclerView;

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

    private void initialize(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.frg_product_recycler_list);


    }
}
