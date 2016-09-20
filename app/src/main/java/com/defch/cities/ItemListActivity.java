package com.defch.cities;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.defch.cities.adapter.CitiesAdapter;
import com.defch.cities.network.request.listener.BaseRetrofitRequestListener;
import com.defch.cities.network.request.request.CityRequest;
import com.defch.cities.network.request.result.CityResult;

import io.fabric.sdk.android.Fabric;
import retrofit2.Response;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity {

    public static final String TAG = "CityAPP";

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_item_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        recyclerView = (RecyclerView) findViewById(R.id.item_list);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        //set the orientation (for tablets only landscape, for phones only portrait)
        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        else
        {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        //create the request for the 5 cities, and get the id's from the resources
        CityRequest cityRequest = new CityRequest(ItemListActivity.this, ItemListActivity.this.getResources().getString(R.string.list_cities));
        cityRequest.execute(new CityRequestListener(ItemListActivity.this));
    }

    private class CityRequestListener extends BaseRetrofitRequestListener<CityResult>
    {

        public CityRequestListener(Context context) {
            super(context);
        }

        @Override
        public void onRequestFailed(int code, String errorMessage)
        {
            Log.e(TAG, "ERROR FETCHING CITIES : " + errorMessage);
        }

        @Override
        public void onRequestSuccessful(Response<CityResult> response)
        {
            if(response != null && response.body() != null)
            {
                //check if the response is not null, create the adapter and set in the recycler, pass the value mTwoPane if is working in tablet or phone
                recyclerView.setAdapter(new CitiesAdapter(ItemListActivity.this, response.body().list, mTwoPane, ItemListActivity.this.getSupportFragmentManager()));
            }
        }
    }

}
