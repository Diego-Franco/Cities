package com.defch.cities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.defch.cities.adapter.DaysAdapter;
import com.defch.cities.model.City;
import com.defch.cities.model.Day;
import com.defch.cities.model.Weather;
import com.defch.cities.network.request.listener.BaseRetrofitRequestListener;
import com.defch.cities.network.request.request.DailyRequest;
import com.defch.cities.network.request.result.DailyResult;
import com.defch.cities.utils.ColorUtils;
import com.defch.cities.utils.DateUtils;
import com.defch.cities.utils.TempUtils;
import com.defch.cities.utils.UiUtils;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import retrofit2.Response;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    private Activity activity;
    private City city;

    // i re-used this variable for check if is running in a device or tablet
    private boolean mTwoPane;

    // i created this 2 LayoutManager for show the different variations for the screen (phone or tablet)
    private LinearLayoutManager llm;
    private GridLayoutManager glm;

    private LinearLayout background;
    private ImageView imgTemp;
    private TextView currentTemp;
    private RecyclerView recycler_week;

    private CollapsingToolbarLayout appBarLayout;
    private Toolbar toolbar;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            city = (City) getArguments().getSerializable(ARG_ITEM_ID);

            activity = this.getActivity();
            appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            toolbar = (Toolbar) activity.findViewById(R.id.detail_toolbar);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.item_detail, container, false);
        background = (LinearLayout) v.findViewById(R.id.background);
        imgTemp = (ImageView) v.findViewById(R.id.img_temp);
        currentTemp = (TextView) v.findViewById(R.id.currently_temp);
        recycler_week = (RecyclerView) v.findViewById(R.id.recycler_week);
        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(city != null)
        {
            if (appBarLayout != null)
            {
                /**
                 * here i use the temperature value for get the color from my ColorUtils.class and use for the statusBar
                 * the toolBar and the BarLayout for the detail screen
                 */
                UiUtils.setStatusBarColor(ColorUtils.getBackgroundColor(activity, TempUtils.getFahrenheit(city.main.temp)), getActivity());
                toolbar.setBackgroundColor(ColorUtils.getBackgroundColor(activity, TempUtils.getFahrenheit(city.main.temp)));
                appBarLayout.setBackgroundColor(ColorUtils.getBackgroundColor(activity, TempUtils.getFahrenheit(city.main.temp)));
                appBarLayout.setTitle(city.name);
            }

            background.setBackgroundColor(ColorUtils.getBackgroundColor(activity, TempUtils.getFahrenheit(city.main.temp)));

            //convert the kelvin value to F and show for the currently temperature
            currentTemp.setText(new DecimalFormat("##.##").format(TempUtils.getFahrenheit(city.main.temp)));

            //using Picasso library, i got the icon value and use with the url from the API
             if(city.weather != null)
             {
                 Weather weather = city.weather.get(0);
                 if(weather.icon != null && !weather.icon.isEmpty())
                 {
                     Picasso.with(activity).load(Const.ICON_URL + weather.icon + ".png").fit().into(imgTemp);
                 }
             }

            // here i checked if the value is different to null for check if is a phone or tablet
            if (activity.findViewById(R.id.item_list) != null) {
                // The detail container view will be present only in the
                // large-screen layouts (res/values-w900dp).
                // If this view is present, then the
                // activity should be in two-pane mode.
                mTwoPane = true;
            }

            //after knows if is a tablet, create the LayoutManager and set in the RecyclerView
            recycler_week.setHasFixedSize(true);
            if(mTwoPane)
            {
                glm = new GridLayoutManager(activity, 3);
                recycler_week.setLayoutManager(glm);
            }
            else
            {
                llm = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
                //llm.setOrientation(LinearLayoutManager.VERTICAL);
                recycler_week.setLayoutManager(llm);
            }

            //Send the request and wait for the response
            DailyRequest dailyRequest = new DailyRequest(activity, String.valueOf(city.id));
            dailyRequest.execute(new DailyRequestListener(activity));
        }
    }

    private class DailyRequestListener extends BaseRetrofitRequestListener<DailyResult>
    {

        public DailyRequestListener(Context context) {
            super(context);
        }

        @Override
        public void onRequestFailed(int code, String errorMessage)
        {
            if(!errorMessage.isEmpty())
            {
                Log.e("ERROR", "message: " + errorMessage);
            }
        }

        @Override
        public void onRequestSuccessful(Response<DailyResult> response)
        {
            if(response != null && response.body() != null)
            {
                /**
                 * create a HashMap, because the api return 40 items and try to get only the 7 days
                 * (for one reason if i use the cnt=7 parameter in the request, the response is not valuable
                 * */
                HashMap<String, Day> mapDay = new HashMap<>();
                for(Day day : response.body().days)
                {
                    if(mapDay.get(DateUtils.formatDate(day.date)) == null)
                    {
                        mapDay.put(DateUtils.formatDate(day.date), day);
                    }
                }
                if(mapDay.size() > 0)
                {
                    /**
                    * Implements sort for dates, and show in correct order
                    */
                    ArrayList<Day> days = new ArrayList<>();
                    days.addAll(mapDay.values());
                    Collections.sort(days);
                    recycler_week.setAdapter(new DaysAdapter(activity, days));
                }
            }
        }
    }
}
