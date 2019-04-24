package com.colombopizza.sasmitha.colombopizzachef.adapters;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.colombopizza.sasmitha.colombopizzachef.R;
import com.colombopizza.sasmitha.colombopizzachef.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends BaseAdapter {

    Context context;
//    ArrayList<Order> orderArrayList = new ArrayList<>();
    private List<Order> listData = new ArrayList<>();

    private TextView textView_OrderId,textView_Order,textView_notes;


    public OrderAdapter(Context context, List<Order> listData) {
        this.context = context;
        this.listData = listData;

        System.out.println("Adapter ----->"+listData.size());
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.activity_order_adapter, parent, false);

        findViews(itemView);

        textView_OrderId.setText(listData.get(position).getOrderID());
        textView_Order.setText(listData.get(position).getOrderItems());
        textView_notes.setText(listData.get(position).getNotes());

        return itemView;
    }

    private void findViews(View itemView) {

        textView_OrderId = (TextView) itemView.findViewById(R.id.tv_OrderId);
        textView_Order = (TextView) itemView.findViewById(R.id.tv_Order);
        textView_notes = (TextView) itemView.findViewById(R.id.tv_notes);

        textView_Order.setMovementMethod(new ScrollingMovementMethod());
        textView_Order.setMaxLines(100);
        textView_Order.setVerticalScrollBarEnabled(true);
        View.OnTouchListener listener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                boolean isLarger;

                isLarger = ((TextView) v).getLineCount()
                        * ((TextView) v).getLineHeight() > v.getHeight();
                if (event.getAction() == MotionEvent.ACTION_MOVE
                        && isLarger) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);

                } else {
                    v.getParent().requestDisallowInterceptTouchEvent(false);

                }
                return false;
            }
        };

        textView_Order.setOnTouchListener(listener);

    }
}
