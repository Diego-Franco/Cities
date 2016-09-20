package com.defch.cities.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.defch.cities.R;
import com.defch.cities.model.Day;
import com.defch.cities.utils.ColorUtils;
import com.defch.cities.utils.DateUtils;
import com.defch.cities.utils.TempUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by DiegoFranco on 9/11/16.
 */

public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.ViewHolder>
{
    private  Context context;
    private final ArrayList<Day> mValues;

    public DaysAdapter(Context context, ArrayList<Day> mValues)
    {
        this.context = context;
        this.mValues = mValues;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        final Day day = mValues.get(position);

        if(day != null)
        {
            holder.background.setBackgroundColor(ColorUtils.getBackgroundColor(context, TempUtils.getFahrenheit(day.main.temp)));

            holder.textDay.setText(DateUtils.getDay(DateUtils.convertDateStringToMilliseconds(day.date)));

            holder.textTemp.setText(new DecimalFormat("##.##").format(TempUtils.getFahrenheit(day.main.temp)));
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size() > 0 ? mValues.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final LinearLayout background;
        public final TextView textDay;
        public final TextView textTemp;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            background = (LinearLayout) view.findViewById(R.id.background);
            textDay = (TextView) view.findViewById(R.id.text_day);
            textTemp = (TextView) view.findViewById(R.id.text_temp);
        }
    }
}
