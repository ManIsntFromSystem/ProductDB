package com.example.productsdb.data;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.productsdb.data.HealthyProductsContract.MemberEntry;

import com.example.productsdb.R;

public class ProductsCursorAdapter extends CursorAdapter {
    public ProductsCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }


        @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_cursor_prod, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvName = (TextView) view.findViewById(R.id.textNameCursor);
        TextView tvDesc = (TextView) view.findViewById(R.id.textDescCursor);
        TextView tvCategory = (TextView) view.findViewById(R.id.textCategoryCursor);
        TextView tvCalories = (TextView) view.findViewById(R.id.textCaloriesCursor);
        TextView tvPrice = (TextView) view.findViewById(R.id.textPriceCursor);

        String name = cursor.getString(cursor.getColumnIndex(MemberEntry.COLUMN_NAME));
        String description = cursor.getString(cursor.getColumnIndex(MemberEntry.COLUMN_DESCRIPTION));
        int category = cursor.getInt(cursor.getColumnIndex(MemberEntry.COLUMN_CATEGORY));
        double calories = cursor.getDouble(cursor.getColumnIndex(MemberEntry.COLUMN_CALORIES));
        double price = cursor.getDouble(cursor.getColumnIndex(MemberEntry.COLUMN_PRICE));

        tvName.setText(name);
        tvDesc.setText(description);
        tvCategory.setText(String.valueOf(category));
        tvCalories.setText(String.valueOf(calories));
        tvPrice.setText(String.valueOf(price));
    }
}
