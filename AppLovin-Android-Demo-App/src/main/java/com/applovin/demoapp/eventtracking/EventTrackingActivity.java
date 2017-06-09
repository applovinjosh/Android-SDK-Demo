package com.applovin.demoapp.eventtracking;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.applovin.apps.demoapp.R;
import com.applovin.demoapp.InterstitialListViewActivity;
import com.applovin.demoapp.ListItem;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinEventService;
import com.applovin.sdk.AppLovinEventParameters;
import com.applovin.sdk.AppLovinEventTypes;

import java.util.Map;
import java.util.HashMap;
/**
 * Created by Monica Ong on 6/8/17.
 */

public class EventTrackingActivity extends InterstitialListViewActivity {

    private ListItem[] events = {
            new ListItem("Began Checkout Event", "Track when user begins checkout procedure"),
            new ListItem("Cart Event", "Track when user adds an item to cart"),
            new ListItem("Completed Achievement Event", "Track when user completed an achievement"),
            new ListItem("Completed Checkout Event", "Track when user completed checkout"),
            new ListItem("Completed Level Event", "Track when user completed level"),
            new ListItem("Created Reservation Event", "Track when user created a reservation"),
            new ListItem("In-App Purchase Event", "Track when user makes an in-app purchase"),
            new ListItem("Login Event", "Track when user logs in"),
            new ListItem("Payment Info Event", "Tracks when user inputs their payment information"),
            new ListItem("Registration Event", "Track when user registers"),
            new ListItem("Search Event", "Track when user makes a search"),
            new ListItem("Sent Invitation Event", "Track when user sends invitation"),
            new ListItem("Shared Link Event", "Track when user shares a link"),
            new ListItem("Spent Virtual Currency Event", "Track when users spends virtual currency"),
            new ListItem("Tutorial Event", "Track when users does a tutorial"),
            new ListItem("Viewed Content Event", "Track when user views content"),
            new ListItem("Viewed Product Event", "Track when user views product"),
            new ListItem("Wishlist Event", "Track when user adds an item to their wishlist")
    };
    private AppLovinEventService eventService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_tracking);
        setTitle("Event Tracking");

        eventService = AppLovinSdk.getInstance(this).getEventService();

        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<ListItem> listAdapter = new ArrayAdapter<ListItem>(this, android.R.layout.simple_list_item_2, events)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View row = convertView;
                if ( row == null )
                {
                    LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
                    row = inflater.inflate( android.R.layout.simple_list_item_2, parent, false );
                }

                ListItem item = events[position];

                TextView title = (TextView) row.findViewById( android.R.id.text1 );
                title.setText( item.getTitle() );
                TextView subtitle = (TextView) row.findViewById( android.R.id.text2 );
                subtitle.setText( item.getSubtitle() );

                return row;
            }
        };
        listView.setAdapter(listAdapter);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String eventTitle = events[ position ].getTitle();
                setTitle( eventTitle );
                Map<String, String> parameters;
                switch ( position )
                {
                    case 0:
                        parameters = new HashMap<String, String>();
                        parameters.put(AppLovinEventParameters.PRODUCT_IDENTIFIER, "PRODUCT SKU OR ID");
                        parameters.put(AppLovinEventParameters.REVENUE_AMOUNT, "PRICE OF ITEM");
                        parameters.put(AppLovinEventParameters.REVENUE_CURRENCY, "3-LETTER CURRENCY CODE");

                        eventService.trackEvent(AppLovinEventTypes.USER_BEGAN_CHECKOUT, parameters);

                        break;
                    case 1:
                        parameters = new HashMap<String, String>();
                        parameters.put(AppLovinEventParameters.PRODUCT_IDENTIFIER, "PRODUCT SKU OR ID");

                        eventService.trackEvent(AppLovinEventTypes.USER_ADDED_ITEM_TO_CART, parameters);

                        break;
                    case 2:
                        parameters = new HashMap<String, String>();
                        parameters.put(AppLovinEventParameters.COMPLETED_ACHIEVEMENT_IDENTIFIER, "ACHIEVEMENT NAME OR ID");

                        eventService.trackEvent(AppLovinEventTypes.USER_COMPLETED_ACHIEVEMENT, parameters);
                        break;
                    case 3:
                        parameters = new HashMap<String, String>();
                        parameters.put(AppLovinEventParameters.CHECKOUT_TRANSACTION_IDENTIFIER, "UNIQUE TRANSACTION ID");
                        parameters.put(AppLovinEventParameters.PRODUCT_IDENTIFIER, "PRODUCT SKU OR ID");
                        parameters.put(AppLovinEventParameters.REVENUE_AMOUNT, "AMOUNT OF MONEY SPENT");
                        parameters.put(AppLovinEventParameters.REVENUE_CURRENCY, "3-LETTER CURRENCY CODE");

                        eventService.trackEvent(AppLovinEventTypes.USER_COMPLETED_CHECKOUT, parameters);

                        break;
                    case 4:
                        parameters = new HashMap<String, String>();
                        parameters.put(AppLovinEventParameters.COMPLETED_LEVEL_IDENTIFIER, "LEVEL NAME OR NUMBER");

                        eventService.trackEvent(AppLovinEventTypes.USER_COMPLETED_LEVEL, parameters);

                        break;
                    case 5:
                        parameters = new HashMap<String, String>();
                        parameters.put(AppLovinEventParameters.PRODUCT_IDENTIFIER, "PRODUCT SKU OR ID");
                        long unixTimeInMilliseconds = System.currentTimeMillis() / 1000L;
                        parameters.put(AppLovinEventParameters.RESERVATION_START_TIMESTAMP, Long.toString(unixTimeInMilliseconds));
                        parameters.put(AppLovinEventParameters.RESERVATION_END_TIMESTAMP, Long.toString(unixTimeInMilliseconds));

                        eventService.trackEvent(AppLovinEventTypes.USER_CREATED_RESERVATION, parameters);

                        break;
                    case 6:
                        parameters = new HashMap<String, String>();
                        parameters.put(AppLovinEventParameters.REVENUE_AMOUNT, "AMOUNT OF MONEY SPENT");
                        parameters.put(AppLovinEventParameters.REVENUE_CURRENCY, "3-LETTER CURRENCY CODE");

                        //eventService.trackInAppPurchase(responseIntentFromOnActivityResult, parameters);
                        // responseIntentFromOnActivityResult is the Intent returned to you by Google Play upon a purchase within the onActivityResult method, as described in the Android Developer Portal.

                        break;
                    case 7:
                        parameters = new HashMap<String, String>();
                        parameters.put(AppLovinEventParameters.USER_ACCOUNT_IDENTIFIER, "USERNAME");

                        eventService.trackEvent(AppLovinEventTypes.USER_LOGGED_IN, parameters);

                        break;
                    case 8:
                        eventService.trackEvent(AppLovinEventTypes.USER_PROVIDED_PAYMENT_INFORMATION);

                        break;
                    case 9:
                        parameters = new HashMap<String, String>();
                        parameters.put(AppLovinEventParameters.USER_ACCOUNT_IDENTIFIER, "USERNAME");

                        eventService.trackEvent(AppLovinEventTypes.USER_CREATED_ACCOUNT, parameters);

                        break;
                    case 10:
                        parameters = new HashMap<String, String>();
                        parameters.put(AppLovinEventParameters.SEARCH_QUERY, "USER'S SEARCH STRING");

                        eventService.trackEvent(AppLovinEventTypes.USER_EXECUTED_SEARCH, parameters);

                        break;
                    case 11:
                        eventService.trackEvent(AppLovinEventTypes.USER_SENT_INVITATION);

                        break;
                    case 12:
                        eventService.trackEvent(AppLovinEventTypes.USER_SHARED_LINK);

                        break;
                    case 13:
                        parameters = new HashMap<String, String>();
                        parameters.put(AppLovinEventParameters.VIRTUAL_CURRENCY_AMOUNT, "NUMBER OF COINS SPENT");
                        parameters.put(AppLovinEventParameters.VIRTUAL_CURRENCY_NAME, "CURRENCY NAME");

                        eventService.trackEvent(AppLovinEventTypes.USER_SPENT_VIRTUAL_CURRENCY, parameters);

                        break;
                    case 14:
                        eventService.trackEvent(AppLovinEventTypes.USER_COMPLETED_TUTORIAL);

                        break;
                    case 15:
                        parameters = new HashMap<String, String>();
                        parameters.put(AppLovinEventParameters.CONTENT_IDENTIFIER, "SOME ID DESCRIBING CONTENT");

                        eventService.trackEvent(AppLovinEventTypes.USER_VIEWED_CONTENT, parameters);

                        break;
                    case 16:
                        parameters = new HashMap<String, String>();
                        parameters.put(AppLovinEventParameters.PRODUCT_IDENTIFIER, "PRODUCT SKU OR ID");

                        eventService.trackEvent(AppLovinEventTypes.USER_VIEWED_PRODUCT, parameters);

                        break;
                    case 17:
                        parameters = new HashMap<String, String>();
                        parameters.put(AppLovinEventParameters.PRODUCT_IDENTIFIER, "PRODUCT SKU OR ID");

                        eventService.trackEvent(AppLovinEventTypes.USER_ADDED_ITEM_TO_WISHLIST, parameters);

                        break;
                    default:
                        setTitle("Default event tracking initiated");

                        break;
                }
            }
        };
        listView.setOnItemClickListener( itemClickListener );

    }
}
