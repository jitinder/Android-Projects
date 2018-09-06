package com.example.android.inventoryappproject;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventoryappproject.data.StockContract;

/**
 * Created by Sidak Pasricha on 12-Aug-18.
 */

public class StockCursorAdapter extends CursorAdapter {

    public StockCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        String name = cursor.getString(cursor.getColumnIndex(StockContract.StockEntry.COLUMN_ITEM_NAME));
        String price = context.getString(R.string.price_text) + " " + cursor.getString(cursor.getColumnIndex(StockContract.StockEntry.COLUMN_ITEM_PRICE));
        final int quantity = cursor.getInt(cursor.getColumnIndex(StockContract.StockEntry.COLUMN_ITEM_QUANTITY));
        String quantityText = context.getString(R.string.quantity_text) + " " + quantity;

        TextView nameTv = view.findViewById(R.id.item_name_tv);
        TextView priceTv = view.findViewById(R.id.item_price_tv);
        TextView quantityTv = view.findViewById(R.id.item_quantity_tv);
        Button saleButton = view.findViewById(R.id.sale_button);

        final int position = cursor.getPosition() +1;

        saleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantity > 0){
                    Uri uri = ContentUris.withAppendedId(StockContract.StockEntry.CONTENT_URI, position);
                    System.out.println(uri.toString());
                    ContentValues contentValues = new ContentValues();
                    int newQuantity = quantity -1;
                    contentValues.put(StockContract.StockEntry.COLUMN_ITEM_QUANTITY, newQuantity);
                    int updated = v.getContext().getContentResolver().update(uri, contentValues, null, null);
                    if(updated > 0){
                        Toast.makeText(v.getContext(), R.string.item_updated, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(v.getContext(), R.string.error_updating, Toast.LENGTH_SHORT).show();
                    }
                    notifyDataSetChanged();
                }
            }
        });

        nameTv.setText(name);
        priceTv.setText(price);
        quantityTv.setText(quantityText);
    }
}
