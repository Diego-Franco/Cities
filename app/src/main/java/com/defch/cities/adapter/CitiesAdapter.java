package com.defch.cities.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.defch.cities.Const;
import com.defch.cities.ItemDetailActivity;
import com.defch.cities.ItemDetailFragment;
import com.defch.cities.R;
import com.defch.cities.model.City;
import com.defch.cities.model.Weather;
import com.defch.cities.utils.ColorUtils;
import com.defch.cities.utils.TempUtils;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by DiegoFranco on 9/9/16.
 */

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.ViewHolder> {

    private Context context;
    private boolean mTwoPane;
    private final ArrayList<City> mValues;
    private FragmentManager fragmentManager;

    public CitiesAdapter(Context context, ArrayList<City> items, boolean mTwoPane, FragmentManager fragmentManager)
    {
        this.context = context;
        this.mTwoPane = mTwoPane;
        this.fragmentManager = fragmentManager;
        this.mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position)
    {
        final City city = mValues.get(position);
        //holder.mItem = mValues.get(position);
        //holder.icon.setImageDrawable(ContextCompat.getDrawable(context, mValues.get(position).icon));

        if(city.main.temp > Double.MIN_VALUE)
        {
            holder.background.setBackgroundColor(ColorUtils.getBackgroundColor(context, TempUtils.getFahrenheit(city.main.temp)));
            holder.temp.setText(new DecimalFormat("##.##").format(TempUtils.getFahrenheit(city.main.temp)));
        }

        if(city.name != null && !city.name.isEmpty())
        {
            holder.name.setText(city.name);
        }

        if(city.weather != null && city.weather.size() > 0)
        {
            Weather weather = city.weather.get(0);
            if(weather.icon != null && !weather.icon.isEmpty())
            {
                Picasso.with(context).load(Const.ICON_URL + weather.icon + ".png").fit().into(holder.icon);
            }
        }


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putSerializable(ItemDetailFragment.ARG_ITEM_ID, city);
                    ItemDetailFragment fragment = new ItemDetailFragment();
                    fragment.setArguments(arguments);
                    fragmentManager.beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit();
                } else {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, ItemDetailActivity.class);
                    intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, city);

                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() { return mValues.size() > 0 ? mValues.size() : 0; }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final LinearLayout background;
        public final ImageView icon;
        public final TextView name;
        public final TextView temp;
        //public City mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            background = (LinearLayout) view.findViewById(R.id.background);
            icon = (ImageView) view.findViewById(R.id.icon);
            name = (TextView) view.findViewById(R.id.name);
            temp = (TextView) view.findViewById(R.id.temp);
        }
    }
}
