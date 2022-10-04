package com.edward.assigment.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.edward.assigment.helper.Database;
import com.edward.assigment.modal.Admin;
import com.edward.assigment.modal.Book;
import com.edward.assigment.modal.Category;
import com.edward.assigment.modal.Order;

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
                    list.add(new Admin(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3)));
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
                    list.add(new Book(cursor.getInt(0), cursor.getInt(1), cursor.getString(2),
                            cursor.getString(3), cursor.getInt(4), cursor.getString(5), cursor.getFloat(6)));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            Log.e(e.toString(), "getAllBook: ");
        }
        return list;
    }

    public ArrayList<Order> getOrder() {
        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();
        ArrayList<Order> list = new ArrayList<>();
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME_BILL, null);
            if (cursor.getCount() != 0) {
                cursor.moveToFirst();
                do {
                    list.add(new Order(cursor.getInt(0), cursor.getInt(1),
                            cursor.getInt(2), cursor.getString(3), cursor.getString(4),
                            cursor.getString(5), cursor.getInt(6)));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            Log.e(e.toString(), "getAllOrder: ");
        }
        return list;
    }

    public ArrayList<Admin> getAllAdmin(int role) {
        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();
        ArrayList<Admin> list = new ArrayList<>();
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME_ADMIN + " where " + KEY_ADMIN_ROLE + "=?", new String[]{String.valueOf(role)});
            if (cursor.getCount() != 0) {
                cursor.moveToFirst();
                do {
                    list.add(new Admin(cursor.getInt(0), cursor.getString(1), null, cursor.getInt(3)));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Admin HandleLoginForAdmin(int id, String passWord) {
        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();
        Admin admin = null;
        try {
            Cursor cursor = sqLiteDatabase.rawQuery(
                    "select * from " + TABLE_NAME_ADMIN + " where " + KEY_ADMIN_ID + "=? AND " + KEY_ADMIN_PASSWORD + "=?", new String[]{String.valueOf(id),passWord});

            cursor.moveToFirst();
            if (cursor.getCount() != 0) {
                admin = new Admin(cursor.getInt(0), cursor.getString(1), null, cursor.getInt(3));
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }

    public boolean handleAddBook(Book book) {
        long result = -1;
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_BOOKS_NAME, book.getTitle());
        contentValues.put(KEY_BOOKS_AUTHOR, book.getAuthor());
        contentValues.put(KEY_CATEGORY_ID, book.getIdCategory());
        contentValues.put(KEY_BOOKS_DESCRIPTION, book.getDescription());
        contentValues.put(KEY_BOOKS_RATING,book.getRating());
        try {
            result = sqLiteDatabase.insert(TABLE_NAME_BOOKS, null, contentValues);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result != -1;
    }

    public ArrayList<String> getAllCategoryName() {
        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();
        ArrayList<String> list = new ArrayList<>();
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("select " + KEY_CATEGORY_NAME + " from " + TABLE_NAME_CATEGORY, null);
            if (cursor.getCount() != 0) {
                cursor.moveToFirst();
                do {
                    list.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getCategoryFromName(String name) {
        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();
        int id = -1;
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("select " + KEY_CATEGORY_ID + " from " + TABLE_NAME_CATEGORY + " where " + KEY_CATEGORY_NAME + "=?", new String[]{name});
            if (cursor.getCount() != 0) {
                cursor.moveToFirst();
                id = cursor.getInt(0);
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public boolean handleRemoveBook(int id) {
        long result = -1;
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        try {
            result = sqLiteDatabase.delete(TABLE_NAME_BOOKS, KEY_BOOKS_ID + "=?", new String[]{String.valueOf(id)});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result != -1;
    }

    public boolean handleUpdateBook(int id, String name, String author, String des) {
        long result = -1;
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_BOOKS_NAME, name);
        contentValues.put(KEY_BOOKS_AUTHOR, author);
        contentValues.put(KEY_BOOKS_DESCRIPTION, des);
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        try {
            result = sqLiteDatabase.update(TABLE_NAME_BOOKS, contentValues, KEY_BOOKS_ID + "=?", new String[]{String.valueOf(id)});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result != -1;
    }

    public boolean handleAddCategory(Category category) {
        long result = -1;
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_CATEGORY_NAME, category.get_name());
        try {
            result = sqLiteDatabase.insert(TABLE_NAME_CATEGORY, null, contentValues);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result != -1;
    }

    public boolean handleAddMod(Admin admin) {
        long result = -1;
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ADMIN_ID, admin.get_id());
        contentValues.put(KEY_ADMIN_USERNAME, admin.get_username());
        contentValues.put(KEY_ADMIN_PASSWORD, admin.get_password());
        contentValues.put(KEY_ADMIN_ROLE, admin.get_role());
        try {
            result = sqLiteDatabase.insert(TABLE_NAME_ADMIN, null, contentValues);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result != -1;
    }

    public boolean handleRemoveMod(int id) {
        long result = -1;
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        try {
            result = sqLiteDatabase.delete(TABLE_NAME_ADMIN, KEY_ADMIN_ID + "=?", new String[]{String.valueOf(id)});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result != -1;
    }

    public String GetBookFromId(String id) {
        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();
        String name = null;
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("select " + KEY_BOOKS_NAME + " from " + TABLE_NAME_BOOKS + " where " + KEY_BOOKS_ID + "=?", new String[]{id});
            if (cursor.getCount() != 0) {
                cursor.moveToFirst();
                name = cursor.getString(0);
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    public String GetNameAdminFromId(int id) {
        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();
        String name = null;
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("select " + KEY_ADMIN_USERNAME + " from " + TABLE_NAME_ADMIN + " where " + KEY_ADMIN_ID + "=?", new String[]{String.valueOf(id)});
            if (cursor.getCount() != 0) {
                cursor.moveToFirst();
                name = cursor.getString(0);
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    public String GetUserNameFromId(String id) {
        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();
        String name = null;
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("select " + KEY_USER_name + " from " + TABLE_NAME_USER + " where " + KEY_USER_ID + "=?", new String[]{id});
            if (cursor.getCount() != 0) {
                cursor.moveToFirst();
                name = cursor.getString(0);
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    public int GetIdBookFromName(String name){
        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();
        int id = -1;
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("select " + KEY_BOOKS_ID + " from " + TABLE_NAME_BOOKS + " where " + KEY_BOOKS_NAME + "=?", new String[]{name});
            if (cursor.getCount() != 0) {
                cursor.moveToFirst();
                id = cursor.getInt(0);
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public boolean handleMarkDoneOrder(int id, String date) {
        long result = -1;
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_BILL_DATERETURN, date);
        contentValues.put(KEY_BILL_checked, 1);
        try {
            result = sqLiteDatabase.update(TABLE_NAME_BILL, contentValues, KEY_BILL_ID + "=?", new String[]{String.valueOf(id)});

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result != -1;
    }

    public ArrayList<String> getAllBookName() {
        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();
        ArrayList<String> list = new ArrayList<>();
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("select "+ KEY_BOOKS_NAME+" from " + TABLE_NAME_BOOKS, null);
            if (cursor.getCount() != 0) {
                cursor.moveToFirst();
                do {
                    list.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            Log.e(e.toString(), "getAllBook: ");
        }
        return list;
    }

    public boolean handleAddOrder(Order order){
        long result = -1;
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_BOOKS_ID,order.get_bookId());
        contentValues.put(KEY_ADMIN_ID,order.get_adminId());
        contentValues.put(KEY_USER_ID,order.get_userId());
        contentValues.put(KEY_BILL_DATECREATE,order.getDateCreate());
        contentValues.put(KEY_BILL_DATERETURN,order.getDateReturn());
        contentValues.put(KEY_BILL_checked,order.get_status());
        try {
            result = sqLiteDatabase.insert(TABLE_NAME_BILL, null, contentValues);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result != -1;
    }
}