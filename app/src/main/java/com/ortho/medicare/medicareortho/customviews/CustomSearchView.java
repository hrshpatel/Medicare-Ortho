package com.ortho.medicare.medicareortho.customviews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.SearchView;
import android.util.AttributeSet;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

public class CustomSearchView extends SearchView {

    private SearchView.SearchAutoComplete mSearchAutoComplete;
    private ArrayAdapter<?> adapter;

    public CustomSearchView(Context context) {
        super(context);
        initialize();
    }

    public CustomSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    @SuppressLint("RestrictedApi")
    public void initialize() {
        mSearchAutoComplete = (android.support.v7.widget.SearchView.SearchAutoComplete)
                findViewById(android.support.v7.appcompat.R.id.search_src_text);
        mSearchAutoComplete.setThreshold(1);
        this.setAdapter(null);
        this.setOnItemClickListener(null);
    }

    @Override
    public void setSuggestionsAdapter(android.support.v4.widget.CursorAdapter adapter) {
        // don't let anyone touch this
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        mSearchAutoComplete.setOnItemClickListener(listener);
    }

    public void setAdapter(ArrayAdapter<?> adapter) {
        this.adapter = adapter;
        mSearchAutoComplete.setAdapter(adapter);
    }

    public ArrayAdapter<?> getAdapter() {
        return adapter;
    }

    public void setText(String text) {
        mSearchAutoComplete.setText(text);
    }

}