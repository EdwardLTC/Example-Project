package com.edward.assigment.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.edward.assigment.helper.Database;
import com.edward.assigment.modal.Admin;
import com.edward.assigment.modal.Book;

import java.util.ArrayList;

public class DataAccesObject extends Database {

    public static final String TABLE_NAME_CATEGORY = "category";
    public static final String TABLE_NAME_BOOKS = "books";
    public static final String TABLE_NAME_ADMIN = "admin";
    public static final String TABLE_NAME_BILL = "bills";
    public static final String TABLE_NAME_USER = "user";

    public static final String KEY_CATEGORY_ID = "category_id";
    public static final String KEY_CATEGORY_NAME = "category_name";
    public static final String KEY_CATEGORY_IMAGE = "category_img";

    public static final String KEY_BOOKS_ID = "book_id";
    public static final String KEY_BOOKS_NAME = "book_name";
    public static final String KEY_BOOKS_IMAGE = "book_img";
    public static final String KEY_BOOKS_AUTHOR = "book_author";
    public static final String KEY_BOOKS_DESCRIPTION = "book_des";
    public static final String KEY_BOOKS_RATING = "book_rating";

    public static final String KEY_ADMIN_ID = "admin_id";
    public static final String KEY_ADMIN_USERNAME = "admin_username";
    public static final String KEY_ADMIN_PASSWORD = "admin_password";
    public static final String KEY_ADMIN_ROLE = "admin_role";

    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_USER_name = "user_name";

    public static final String KEY_BILL_ID = "bill_id";
    public static final String KEY_BILL_checked = "status";
    public static final String KEY_BILL_DATECREATE = "bill_date_create";
    public static final String KEY_BILL_DATERETURN = "bill_date_return";


    private static Database database;

    public DataAccesObject(Context context) {
        super(context);
        database = new Database(context);
    }

    public ArrayList<Admin> getAllAdmin() {
        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();
        ArrayList<Admin> list = new ArrayList<>();
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME_ADMIN, null);
            if (cursor.getCount() != 0) {
                cursor.moveToFirst();
                do {
                    list.add(new Admin(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3)));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            Log.e(e.toString(), "getAllAdmin: ");

            return list;
        }
        return list;
    }

    public ArrayList<Book> getAllBook() {
        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();
        ArrayList<Book> list = new ArrayList<>();
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME_BOOKS, null);
            if (cursor.getCount() != 0) {
                cursor.moveToFirst();
                do {
                    list.add(new Book(cursor.getString(0), cursor.getString(1), cursor.getString(2),
                            cursor.getString(3),cursor.getInt(4),cursor.getString(5),cursor.getFloat(6)));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            Log.e(e.toString(), "getAllBook: ");
            return list;
        }
        return list;
    }

    public ArrayList<Book> getAllBook(String bookname) {
        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();
        ArrayList<Book> list = new ArrayList<>();
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME_BOOKS + " where " +KEY_BOOKS_NAME + " LIKE '?%'" , new String[]{bookname});
            if (cursor.getCount() != 0) {
                cursor.moveToFirst();
                do {
                    list.add(new Book(cursor.getString(0), cursor.getString(1), cursor.getString(2),
                            cursor.getString(3),cursor.getInt(4),cursor.getString(5),cursor.getFloat(6)));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            Log.e(e.toString(), "getAllBook: ");
            return list;
        }
        return list;
    }
}