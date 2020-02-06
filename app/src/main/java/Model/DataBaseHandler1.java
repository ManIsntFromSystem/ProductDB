package Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import Model.Product;
import com.example.productsdb.data.HealthyProductsContract;

public class DataBaseHandler1 {
    //extends SQLiteOpenHelper
    /*public DataBaseHandler(Context context) {
        super(context, HealthyProductsContract.DATABASE_NAME, null, HealthyProductsContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQL = structured query language
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + HealthyProductsContract.TABLE_NAME + "(" +
                HealthyProductsContract.KEY_ID + " INTEGER PRIMARY KEY, " +
                HealthyProductsContract.KEY_NAME + " TEXT, " +
                HealthyProductsContract.KEY_PRICE + " TEXT" + ")";

        db.execSQL(CREATE_CARS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + HealthyProductsContract.TABLE_NAME);
        onCreate(db);
    }

    //CRUD
    //Create, Read, Update, Delete

    public void addProduct(Product product){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(HealthyProductsContract.KEY_NAME, product.getName());
        contentValues.put(HealthyProductsContract.KEY_PRICE, product.getPrice());

        db.insert(HealthyProductsContract.TABLE_NAME, null, contentValues);
        db.close();
    }

    public Product getProduct(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(HealthyProductsContract.TABLE_NAME, new String[] {HealthyProductsContract.KEY_ID,
                HealthyProductsContract.KEY_NAME, HealthyProductsContract.KEY_PRICE}, HealthyProductsContract.KEY_ID + "=?", new String[] {String.valueOf(id)},
                null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        Product product = new Product(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        return product;
    }

    public List<Product> getAllProducts(){
        SQLiteDatabase db = this.getReadableDatabase();

        List<Product> productList = new ArrayList<>();

        String selectAllProducts = "SELECT * FROM " + HealthyProductsContract.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAllProducts, null);

        //cursor.moveToFirst();  - return 'true' if cursor includes some notes
        if (cursor.moveToFirst()){
            do{
                Product product = new Product();
                product.setId(Integer.parseInt(cursor.getString(0)));
                product.setName(cursor.getString(1));
                product.setPrice(cursor.getString(2));

                productList.add(product);
            } while (cursor.moveToNext());
        }
        return productList;
    }

    public int updateProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(HealthyProductsContract.KEY_NAME, product.getName());
        contentValues.put(HealthyProductsContract.KEY_PRICE, product.getPrice());

        return db.update(HealthyProductsContract.TABLE_NAME, contentValues, HealthyProductsContract.KEY_ID + "=?",
                new String[] {String.valueOf(product.getId())});
    }

    public void deleteProduct(Product product){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(HealthyProductsContract.TABLE_NAME, HealthyProductsContract.KEY_ID + "=?",
                new String[] {String.valueOf(product.getId())});
        db.close();
    }

    public int getProductCount() {
        SQLiteDatabase db = this.getReadableDatabase();

        String countQuery = "SELECT * FROM " + HealthyProductsContract.TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);
        return cursor.getCount();
    }*/
}
