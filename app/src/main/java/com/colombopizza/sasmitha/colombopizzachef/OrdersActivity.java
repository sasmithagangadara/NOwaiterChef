package com.colombopizza.sasmitha.colombopizzachef;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.colombopizza.sasmitha.colombopizzachef.adapters.OrderAdapter;
import com.colombopizza.sasmitha.colombopizzachef.database.Database;
import com.colombopizza.sasmitha.colombopizzachef.model.Order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrdersActivity extends AppCompatActivity {


    ListView orderList;
    String orderID,orderItems,notes = "xxxx" ;

    ArrayList<Order> orderArrayList = new ArrayList<>();

    static List<List<Order>> orderLists = new ArrayList<>();
    List<Order> orderItemList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        init();

        checkPastOrders();


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                orderID= " N/A";
                orderItems = " N/A";
                notes = " N/A";
            } else {
                orderID= extras.getString("id");
                orderItems= extras.getString("order");
                notes= extras.getString("notes");


                 addToDB();

                 orderList.deferNotifyDataSetChanged();

            }
        } else {
            orderID= (String) savedInstanceState.getSerializable("id");
            orderItems= (String) savedInstanceState.getSerializable("order");
            notes= (String) savedInstanceState.getSerializable("notes");

            addToDB();

            orderList.deferNotifyDataSetChanged();

        }

    }

    private void checkPastOrders() {

        orderItemList = new Database(this).getCarts();
        orderLists.add(orderItemList);

        Collections.reverse(orderItemList);

        System.out.println("items ------------->  "+orderItemList.get(0).getOrderItems());

        OrderAdapter adapter = new OrderAdapter(OrdersActivity.this, orderItemList);
        orderList.setAdapter(adapter);
    }

    private void addToDB() {

        System.out.println("------------> "+orderItems);

        new Database(getBaseContext()).addToCart(new Order(
                orderID,
                orderItems.trim(),
                notes
        ));

        checkPastOrders();

    }

    private void init(){

        orderList = findViewById(R.id.list_Order);

        OrderAdapter adapter = new OrderAdapter(OrdersActivity.this, orderItemList);
        orderList.setAdapter(adapter);

    }


    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
