package com.colombopizza.sasmitha.colombopizzachef.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.colombopizza.sasmitha.colombopizzachef.model.Order;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {

    private static final String DB_NAME = "sasmithaDB.db";
    private static final int DB_VER = 1;

    public Database(Context context){
        super(context, DB_NAME,null, DB_VER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);

    }

    public List<Order> getCarts(){

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"OrderId", "OrderItem", "Notes"};
        String sqlTable = "Orders";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);

        final List<Order> result = new ArrayList<>();
        if(c.moveToFirst()){
            do{
                result.add(new Order(c.getString(c.getColumnIndex("OrderId")),
                        c.getString(c.getColumnIndex("OrderItem")),
                        c.getString(c.getColumnIndex("Notes"))
                ));
            }while (c.moveToNext());
        }
        return result;
    }


    public void addToCart(Order order){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO Orders(OrderId, OrderItem, Notes) VALUES('%s','%s','%s');",
                order.getOrderID(),
                order.getOrderItems(),
                order.getNotes());

        db.execSQL(query);
    }
}
