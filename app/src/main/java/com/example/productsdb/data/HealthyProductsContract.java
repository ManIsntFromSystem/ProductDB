package com.example.productsdb.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class HealthyProductsContract {

    private HealthyProductsContract() {
    }

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "products_data";

    public static final String SCHEME = "content://";
    public static final String AUTHORITY = "com.example.productsdb";
    public static final String PATH_PRODUCTS = "products";

    public static final Uri BASE_CONTENT_URI =
            Uri.parse(SCHEME + AUTHORITY);

    public static final class MemberEntry implements BaseColumns {

        public static final String TABLE_NAME = "products";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_CATEGORY = "category";
        public static final String COLUMN_CALORIES = "calories";
        public static final String COLUMN_PRICE = "price";

        public static final int CATEGORY_VEGETABLE = 0;
        public static final int CATEGORY_FRUIT = 1;
        public static final int CATEGORY_GRAINS_BEANS_AND_NUTS = 2;
        public static final int CATEGORY_FISH_AND_SEAFOOD = 3;
        public static final int CATEGORY_DAIRY = 4;
        public static final int CATEGORY_MEAT_AND_POULTRY = 5;
        public static final int CATEGORY_OTHER = 6;

        public static final Uri CONTENT_URI =
                Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PRODUCTS);

        public static final String CONTENT_MULTIPLE_ITEMS = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY + "/" + PATH_PRODUCTS;
        public static final String CONTENT_SINGLE_ITEM = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + AUTHORITY + "/" + PATH_PRODUCTS;

    }
}
