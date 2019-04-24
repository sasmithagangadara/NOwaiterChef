package com.colombopizza.sasmitha.colombopizzachef;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.colombopizza.sasmitha.colombopizzachef.database.Database;
import com.colombopizza.sasmitha.colombopizzachef.model.Order;
import com.onesignal.OneSignal;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView textView_OrderId,textView_Order,textView_notes;

    String orderID,order,notes ;

    List<Order> orderItemList = new ArrayList<>();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

        OneSignal.sendTag("User_id", "admin");

        init();

    }

    private void init(){

        orderItemList = new Database(this).getCarts();

        System.out.println("Main -------->   "+orderItemList.size());

        textView_OrderId = (TextView) findViewById(R.id.tv_OrderId);
        textView_Order = (TextView) findViewById(R.id.tv_Order);
        textView_notes = (TextView) findViewById(R.id.tv_notes);

        if(orderItemList.size() > 0){
            Intent intent = new Intent(MainActivity.this, OrdersActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
