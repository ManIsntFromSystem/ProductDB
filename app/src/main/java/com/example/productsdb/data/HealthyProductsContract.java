package com.example.productsdb.data;

import android.provider.BaseColumns;

public final class HealthyProductsContract {

    private HealthyProductsContract() {
    }

    public static final class MemberEntry implements BaseColumns {
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "productsDB";

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
    }
}
