package com.shoppingcart.netUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.shoppingcart.GlobalElements;
import com.shoppingcart.model.OrderHistoryModel;
import com.shoppingcart.model.ProductModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;
    public static final String database_Name = "cartExample.db";
    public static final int database_Version = 1;
    public static final String TAG = "DATABASE OPERATIONS";

    public static final String CART_TABLE = "cart";
    public static final String id = "id";
    public static final String uid = "uid";
    public static final String orderstatus = "orderstatus";
    public static final String total = "total";
    public static final String qty = "qty";
    public static final String order_date = "order_date";
    public static final String delivery_date = "delivery_date";

    public static final String create_Table = "CREATE TABLE " + CART_TABLE +
            "(" + id + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            uid + " INTEGER," +
            orderstatus + " INTEGER," +
            total + " INTEGER," +
            order_date + " DATE," +
            delivery_date + " DATE);";

    public static final String CARTITEM_TABLE = "cart_item";
    public static final String cid = "cid";
    public static final String pid = "pid";
    public static final String name = "name";
    public static final String image_path = "image_path";
    public static final String price = "price";

    public static final String create_cartitem_Table = "CREATE TABLE " + CARTITEM_TABLE +
            "(" + id + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            cid + " INTEGER," +
            pid + " INTEGER," +
            name + " TEXT," +
            image_path + " TEXT," +
            qty + " INTEGER," +
            price + " INTEGER);";

    public DBHelper(Context context) {
        super(context, database_Name, null, database_Version);
        Log.i(TAG, "Database Created / Opened");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_Table);
        db.execSQL(create_cartitem_Table);
    }

    public void createDatabase() {
        db = getReadableDatabase();
    }

    public Cursor getData(String Query) {
        db = getReadableDatabase();
        try {
            Cursor c = db.rawQuery(Query, null);
            return c;
        } catch (Exception e) {
            return null;
        }
    }

    public int getCartCount() {
        int count = 0;
        Cursor c = getData("SELECT * FROM " + CART_TABLE + " INNER JOIN " + CARTITEM_TABLE + "  ON cart.ID = cart_item.cid where orderstatus=1");
        if (c.getCount() > 0) {
            return c.getCount();
        }
        return count;
    }

    public boolean deleteTable(String table_name) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            if (db.delete(table_name, "1", null) > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int deleteRecord(String id, String Table) {
        db = getWritableDatabase();
        String value[] = {id};
        int i = db.delete(Table, "id=?", value);
        Log.e("", "" + i);
        db.close();
        return i;
    }

    public String rp_getvalue(String table_name, String column_name, String where) {
        try {
            Cursor c2 = getData("select * from " + table_name + " where " + where + "");
            if (c2.getCount() > 0) {
                if (c2.moveToFirst()) {
                    String value = "" + c2.getString(c2.getColumnIndex("" + column_name));
                    c2.close();
                    return value;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
        return "0";
    }

    public void addToCart(String uid, String pid, String name, String image_path, String price, String qty) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            Cursor c = getData("select * from " + CART_TABLE + " where orderstatus=1");
            if (c.getCount() > 0) {
                while (c.moveToNext()) {
                    String cartId = c.getString(c.getColumnIndex("id"));
                    Cursor p = getData("select * from " + CARTITEM_TABLE + " where pid=" + pid + " AND cid=" + cartId + " ");
                    if (p.getCount() > 0) {
                        values = new ContentValues();
                        values.put(this.cid, "" + cartId);
                        values.put(this.pid, "" + pid);
                        values.put(this.name, "" + name);
                        values.put(this.image_path, "" + image_path);
                        values.put(this.qty, "" + qty);
                        values.put(this.price, "" + price);
                        db.update(CARTITEM_TABLE, values, "pid=" + pid + " AND " + this.cid + "=" + cartId, new String[]{});
                    } else {
                        values = new ContentValues();
                        values.put(this.cid, "" + cartId);
                        values.put(this.pid, "" + pid);
                        values.put(this.name, "" + name);
                        values.put(this.image_path, "" + image_path);
                        values.put(this.qty, "" + qty);
                        values.put(this.price, "" + price);
                        long id = db.insert(CARTITEM_TABLE, null, values);
                    }
                }
            } else {
                /* add to cart */
                values = new ContentValues();
                values.put(this.uid, "" + uid);
                values.put(this.orderstatus, "1");
                values.put(this.total, 0);
                values.put(this.order_date, "" + GlobalElements.getDate());
                values.put(this.delivery_date, "" + GlobalElements.getDate());
                long id = db.insert(CART_TABLE, null, values);

                if (id != -1) {
                    /* todo add cartitem*/
                    values = new ContentValues();
                    values.put(this.cid, "" + id);
                    values.put(this.pid, "" + pid);
                    values.put(this.name, "" + name);
                    values.put(this.image_path, "" + image_path);
                    values.put(this.qty, "" + qty);
                    values.put(this.price, "" + price);
                    id = db.insert(CARTITEM_TABLE, null, values);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int placeOrder(String cartId, String total) {
        int id = -1;
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(orderstatus, "2");
            values.put(delivery_date, "" + GlobalElements.getDate());
            values.put(this.total, "" + total);
            id = db.update(CART_TABLE, values, this.id + "=" + cartId, new String[]{});
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return id;
    }

    public ArrayList<ProductModel> getCartItem() {
        ArrayList<ProductModel> data = new ArrayList<>();
        Cursor c = getData("SELECT cart_item.* FROM " + CART_TABLE + " INNER JOIN " + CARTITEM_TABLE + "  ON cart.ID = cart_item.cid where orderstatus=1");
        if (c.getCount() > 0) {
            while (c.moveToNext()) {
                ProductModel da = new ProductModel();
                da.setCart_item_id(c.getString(c.getColumnIndex("id")));
                da.setId(c.getString(c.getColumnIndex("pid")));
                da.setCid(c.getString(c.getColumnIndex("cid")));
                da.setName(c.getString(c.getColumnIndex("name")));
                da.setImage_path(c.getString(c.getColumnIndex("image_path")));
                da.setQty(c.getInt(c.getColumnIndex("qty")));
                da.setPrice(c.getInt(c.getColumnIndex("price")));
                data.add(da);
            }
        }
        return data;
    }

    public ArrayList<OrderHistoryModel> getOrderHistory() {
        ArrayList<OrderHistoryModel> data = new ArrayList<>();
        Cursor c = getData("SELECT * FROM " + CART_TABLE + " where orderstatus!=1");
        if (c.getCount() > 0) {
            while (c.moveToNext()) {
                OrderHistoryModel da = new OrderHistoryModel();
                da.setId(c.getString(c.getColumnIndex("id")));
                da.setOrderstatus(c.getString(c.getColumnIndex("orderstatus")));
                da.setTotal(c.getString(c.getColumnIndex("total")));
                da.setOrder_date(c.getString(c.getColumnIndex("order_date")));
                da.setDelivery_date(c.getString(c.getColumnIndex("delivery_date")));
                data.add(da);
            }
        }
        return data;
    }

    public ArrayList<ProductModel> getCartItemDetail(String cartID) {
        ArrayList<ProductModel> data = new ArrayList<>();
        Cursor c = getData("SELECT * FROM " + CARTITEM_TABLE + " where cid=" + cartID + "");
        if (c.getCount() > 0) {
            while (c.moveToNext()) {
                ProductModel da = new ProductModel();
                da.setCart_item_id(c.getString(c.getColumnIndex("id")));
                da.setId(c.getString(c.getColumnIndex("pid")));
                da.setCid(c.getString(c.getColumnIndex("cid")));
                da.setName(c.getString(c.getColumnIndex("name")));
                da.setImage_path(c.getString(c.getColumnIndex("image_path")));
                da.setQty(c.getInt(c.getColumnIndex("qty")));
                da.setPrice(c.getInt(c.getColumnIndex("price")));
                data.add(da);
            }
        }
        return data;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*try {
            String DATABASE_ALTER_TEAM_1 = "ALTER TABLE " + COLOR_TABLE + " ADD COLUMN " + COLUMN_COLOR_desc;
            if (oldVersion < newVersion) {
                Log.e("", "");
                db.execSQL(DATABASE_ALTER_TEAM_1);
            } else {
                Log.e("", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

}
