package com.ortho.medicare.medicareortho.adapters;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.ortho.medicare.medicareortho.models.ProductDetailsModel;

import java.util.ArrayList;
import java.util.List;

public class SearchViewAdapter extends ArrayAdapter implements Filterable {

    List<ProductDetailsModel> allCodes;
    List<ProductDetailsModel> originalCodes;
    StringFilter filter;

    public SearchViewAdapter(@NonNull Context context, @LayoutRes int resource
            , @IdRes int textViewResourceId, @NonNull List<ProductDetailsModel> objects) {
        super(context, resource, textViewResourceId, objects);
        this.allCodes = objects;
        this.originalCodes = objects;
    }

    public SearchViewAdapter(Context context, int resource, List<ProductDetailsModel> keys) {
        super(context, resource, keys);
        allCodes = keys;
        originalCodes = keys;
    }

    public int getCount() {
        return allCodes != null ? allCodes.size() : 0;
    }

    public Object getItem(int position) {
        return allCodes != null ? allCodes.get(position).getProductDetails().get(0).getName() : null;
    }

    public long getItemId(int position) {
        return Long.parseLong(allCodes.get(position).getProductId());
    }

    public ProductDetailsModel getCurrentItem(int position){
        return  allCodes.get(position);
    }

    private class StringFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();
            final List<ProductDetailsModel> list = originalCodes;

            int count = list.size();
            final ArrayList<ProductDetailsModel> nlist = new ArrayList<>(count);
            String filterableString = "";

            if (constraint != null) {
                String filterString = constraint.toString().toLowerCase();

                for (int i = 0; i < count; i++) {
                    if (list.get(i).getProductDetails().size() > 0) {
                        filterableString = list.get(i).getProductDetails()
                                .get(0).getName();
                    }
                    if (filterableString.toLowerCase().contains(filterString)) {
                        nlist.add(list.get(i));
                    }
                }
            }

            results.values = nlist;
            results.count = nlist.size();
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            allCodes = (ArrayList<ProductDetailsModel>) results.values;
            notifyDataSetChanged();
        }

    }


    @Override
    public Filter getFilter() {
        return new StringFilter();
    }
}