package com.example.productsdb.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.productsdb.data.HealthyProductsContract.MemberEntry;

import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

public class ProductsContentProvider extends ContentProvider {

    ProductsDBHelper productsDBHelper;

    private static final int MATCHER_WHOLE_TABLE = 333;
    private static final int MATCHER_SPECIFIC_PRODUCT = 777;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(HealthyProductsContract.AUTHORITY,
                HealthyProductsContract.PATH_PRODUCTS, MATCHER_WHOLE_TABLE);

        uriMatcher.addURI(HealthyProductsContract.AUTHORITY,
                HealthyProductsContract.PATH_PRODUCTS + "/#",
                MATCHER_SPECIFIC_PRODUCT);
    }

    @Override
    public boolean onCreate() {
        productsDBHelper = new ProductsDBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query( Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = productsDBHelper.getReadableDatabase();
        Cursor cursor;

        int match = uriMatcher.match(uri);

        switch (match){
            case MATCHER_WHOLE_TABLE:
                cursor = db.query(MemberEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                //projection - columns's name projection = {"name","description"}
                //selection(otbor) = "_id=?";
                //selectionArgs (arguments otbora) = {34,21,121211121} - long type
                //

                break;
            case MATCHER_SPECIFIC_PRODUCT:
                selection = MemberEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                cursor = db.query(MemberEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Can't query incorrect URI: " + uri);

        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri); //for defined which uri we need to use

        return cursor;
    }

    @Override
    public Uri insert( Uri uri, ContentValues values) {

        String nameProduct = values.getAsString(MemberEntry.COLUMN_NAME);
        if (nameProduct == null){
            throw new IllegalArgumentException("Input product name");
        }
        String descriptionProduct = values.getAsString(MemberEntry.COLUMN_DESCRIPTION);
        if (descriptionProduct == null){
            throw new IllegalArgumentException("Input product description");
        }
        Integer categoryProduct = values.getAsInteger(MemberEntry.COLUMN_CATEGORY);
        if (categoryProduct == null || !(categoryProduct == MemberEntry.CATEGORY_VEGETABLE || categoryProduct == MemberEntry.CATEGORY_FRUIT ||
                categoryProduct == MemberEntry.CATEGORY_GRAINS_BEANS_AND_NUTS || categoryProduct == MemberEntry.CATEGORY_FISH_AND_SEAFOOD ||
                categoryProduct == MemberEntry.CATEGORY_DAIRY || categoryProduct == MemberEntry.CATEGORY_MEAT_AND_POULTRY ||
                categoryProduct == MemberEntry.CATEGORY_OTHER)){
            throw new IllegalArgumentException("You have to input correct value");
        }
        Double caloriesProduct = values.getAsDouble(MemberEntry.COLUMN_CALORIES);
        if (caloriesProduct == null){
            throw new IllegalArgumentException("Input product calories");
        }
        Double priceProduct = values.getAsDouble(MemberEntry.COLUMN_PRICE);
        if (priceProduct == null){
            throw new IllegalArgumentException("Input product price");
        }

        SQLiteDatabase db = productsDBHelper.getWritableDatabase();
        int match = uriMatcher.match(uri);

        switch (match){
            case MATCHER_WHOLE_TABLE:
                long id = db.insert(MemberEntry.TABLE_NAME, null, values);
                if (id == -1){
                    Log.e("InsertMethod", "Insertion of data in the table failed for " + uri);
                    return null;
                }

                getContext().getContentResolver().notifyChange(uri, null);

                return ContentUris.withAppendedId(uri, id);

            default:
                throw new IllegalArgumentException("Insertion of data in the table failed for " + uri);

        }
    }

    @Override
    public int delete( Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = productsDBHelper.getWritableDatabase();

        int match = uriMatcher.match(uri);

        int rowsDeleted;

        switch (match){
            case MATCHER_WHOLE_TABLE:
                rowsDeleted = db.delete(MemberEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case MATCHER_SPECIFIC_PRODUCT:
                selection = MemberEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = db.delete(MemberEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Can't delete incorrect URI: " + uri);
        }

        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (values.containsKey(MemberEntry.COLUMN_NAME)){
            String nameProduct = values.getAsString(MemberEntry.COLUMN_NAME);
            if (nameProduct == null){
                throw new IllegalArgumentException("Input product name");
            }
        }
        if (values.containsKey(MemberEntry.COLUMN_DESCRIPTION)) {
            String descriptionProduct = values.getAsString(MemberEntry.COLUMN_DESCRIPTION);
            if (descriptionProduct == null){
                throw new IllegalArgumentException("Input product description");
            }
        }

        if (values.containsKey(MemberEntry.COLUMN_DESCRIPTION)) {
            Integer categoryProduct = values.getAsInteger(MemberEntry.COLUMN_CATEGORY);
            if (categoryProduct == null || !(categoryProduct == MemberEntry.CATEGORY_VEGETABLE || categoryProduct == MemberEntry.CATEGORY_FRUIT ||
                    categoryProduct == MemberEntry.CATEGORY_GRAINS_BEANS_AND_NUTS || categoryProduct == MemberEntry.CATEGORY_FISH_AND_SEAFOOD ||
                    categoryProduct == MemberEntry.CATEGORY_DAIRY || categoryProduct == MemberEntry.CATEGORY_MEAT_AND_POULTRY ||
                    categoryProduct == MemberEntry.CATEGORY_OTHER)){
                throw new IllegalArgumentException("You have to input correct value");
            }
        }


        if (values.containsKey(MemberEntry.COLUMN_DESCRIPTION)) {
            Double caloriesProduct = values.getAsDouble(MemberEntry.COLUMN_CALORIES);
            if (caloriesProduct == null){
                throw new IllegalArgumentException("Input product calories");
            }
        }

        if (values.containsKey(MemberEntry.COLUMN_DESCRIPTION)) {
            Double priceProduct = values.getAsDouble(MemberEntry.COLUMN_PRICE);
            if (priceProduct == null) {
                throw new IllegalArgumentException("Input product price");
            }
        }

        SQLiteDatabase db = productsDBHelper.getWritableDatabase();

        int match = uriMatcher.match(uri);
        int rowsUpdated;
        switch (match){
            case MATCHER_WHOLE_TABLE:
                //return db.update(MemberEntry.TABLE_NAME, values, selection, selectionArgs);
                rowsUpdated = db.update(MemberEntry.TABLE_NAME, values, selection, selectionArgs);
                break;

            case MATCHER_SPECIFIC_PRODUCT:
                selection = MemberEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};

                rowsUpdated = db.update(MemberEntry.TABLE_NAME, values, selection, selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Can't update incorrect URI: " + uri);
        }
        if (rowsUpdated != 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }

    @Override
    public String getType( Uri uri) {
        int match = uriMatcher.match(uri);

        switch (match){
            case MATCHER_WHOLE_TABLE:
                return MemberEntry.CONTENT_MULTIPLE_ITEMS;
            case MATCHER_SPECIFIC_PRODUCT:
                return MemberEntry.CONTENT_SINGLE_ITEM;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }
}



//URI - Unified Recourse Identifier
//content://com.android.uraall.productsdb/products

//URL - Unified Recourse Locator
//http://google.com/

//content://com.android.contacts/contacts
//content://com.android.calendar/events
//content://user_dictionary/words

//content:// - scheme
//com.android.contacts - content authority
//contacts - type of data
