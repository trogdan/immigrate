package com.walkingdevs.immigrate;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * List adapter for the comparison view.
 */
public class CityComparisonListAdapter extends ArrayAdapter<CityComparisonViewInfo> {

    public CityComparisonListAdapter(Activity context, List<CityComparisonViewInfo> viewInfos) {
        super(context, 0, viewInfos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CityComparisonViewInfo viewInfo = getItem(position);


        return null;
    }

    private View getComparisonView(CityComparisonViewInfo viewInfo, View convertView, ViewGroup parent) {
        CityComparisonViewInfoHolder holder;

        if (convertView != null) {
            holder = (CityComparisonViewInfoHolder) convertView.getTag();
        } else {
            //convertView = LayoutInflater.from(getContext()).inflate(R.layout.comparison_view_item, parent, false);

            holder = new CityComparisonViewInfoHolder();

            // Populate holder here!!!!!

            convertView.setTag(holder);
        }

        return convertView;
    }

    // ViewHolder pattern
    static class CityComparisonViewInfoHolder {
        // Populate holder members here !!!!
    }

}
