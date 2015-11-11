package com.walkingdevs.immigrate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.thewalkingdevs.api.myApi.model.ItemPriceSkinny;

import java.util.List;

/**
 * Created by Phil on 11/11/2015.
 */
public class ItemPriceAdapter extends ArrayAdapter<ItemPriceSkinny> {

    private ViewHolder views;

    public ItemPriceAdapter(Context c, List<ItemPriceSkinny> items) {
        super(c, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemPriceSkinny price = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_city_prce, parent, false);
            views = new ViewHolder();
            views.priceName = (TextView)convertView.findViewById(R.id.item_price_name);
            views.priceMin = (TextView)convertView.findViewById(R.id.item_price_min);
            views.priceMax = (TextView)convertView.findViewById(R.id.item_price_max);
        }
        views.priceName.setText(price.getItemName());
        views.priceMin.setText("$" + price.getLowestPrice() + "");
        views.priceMax.setText("$" + price.getHighestPrice() + "");

        // Return the completed view to render on screen
        return convertView;
    }

    public class ViewHolder {
        public TextView priceName;
        public TextView priceMax;
        public TextView priceMin;
    }
}
