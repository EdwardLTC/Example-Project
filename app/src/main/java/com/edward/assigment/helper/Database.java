package com.edward.assigment.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "PNApp";
    public static final int DATABASE_VERSION = 1;

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

    public static final String KEY_ADMIN_ID = "admin_id";
    public static final String KEY_ADMIN_USERNAME = "admin_username";
    public static final String KEY_ADMIN_PASSWORD = "admin_password";
    public static final String KEY_ADMIN_ROLE = "admin_role";

    public static final String KEY_USER_ID = "user_id";

    public static final String KEY_BILL_ID = "bill_id";
    public static final String KEY_BILL_checked = "status";
    public static final String KEY_BILL_DATECREATE = "bill_date_create";
    public static final String KEY_BILL_DATERETURN = "bill_date_return";


    private static Database instance;

    public static synchronized Database getInstance(Context context) {
        if (instance == null) instance = new Database(context);
        return instance;

    }

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createCategory = "CREATE TABLE " + TABLE_NAME_CATEGORY +
                "(" +
                KEY_CATEGORY_ID + " TEXT PRIMARY KEY, " +
                KEY_CATEGORY_NAME + " TEXT, " +
                KEY_CATEGORY_IMAGE + " INTEGER " +
                " )";
        sqLiteDatabase.execSQL(createCategory);

        String createBook = "CREATE TABLE " + TABLE_NAME_BOOKS +
                "(" +
                KEY_BOOKS_ID + " TEXT PRIMARY KEY, " +
                KEY_CATEGORY_ID + " TEXT,  " +
                KEY_BOOKS_NAME + " TEXT, " +
                KEY_BOOKS_AUTHOR + " TEXT, " +
                KEY_BOOKS_IMAGE + " INTEGER, " +
                "FOREIGN  KEY (" + KEY_CATEGORY_ID + ") REFERENCES " + TABLE_NAME_CATEGORY + " (" + KEY_CATEGORY_ID + ")  ON DELETE CASCADE"+
                " )";
        sqLiteDatabase.execSQL(createBook);

        String createAdmin = "CREATE TABLE " + TABLE_NAME_ADMIN +
                "(" +
                KEY_ADMIN_ID + " TEXT PRIMARY KEY, " +
                KEY_ADMIN_USERNAME + " TEXT, " +
                KEY_ADMIN_PASSWORD + " TEXT, " +
                KEY_ADMIN_ROLE + " INTEGER " +
                " )";
        sqLiteDatabase.execSQL(createAdmin);

        String createUser = "CREATE TABLE " + TABLE_NAME_USER +
                "(" + KEY_USER_ID + " TEXT " + " )";
        sqLiteDatabase.execSQL(createUser);

        String createBill = "CREATE TABLE " + TABLE_NAME_BILL +
                "(" +
                KEY_BILL_ID + " TEXT PRIMARY KEY AUTOINCREMENT, " +
                KEY_BOOKS_ID + " TEXT, " +
                KEY_ADMIN_ID + " TEXT, " +
                KEY_USER_ID + " TEXT, " +
                KEY_BILL_checked + " INTEGER, " +
                KEY_BILL_DATECREATE + " TEXT, " +
                KEY_BILL_DATERETURN + " TEXT, " +
                "FOREIGN  KEY (" + KEY_BOOKS_ID + ") REFERENCES " + TABLE_NAME_BOOKS + " (" + KEY_BOOKS_ID + "), "+
                "FOREIGN  KEY (" + KEY_ADMIN_ID + ") REFERENCES " + TABLE_NAME_ADMIN + " (" + KEY_ADMIN_ID + "), "+
                "FOREIGN  KEY (" + KEY_USER_ID + ") REFERENCES " + TABLE_NAME_USER + " (" + KEY_USER_ID + ") "+
                " )";
        sqLiteDatabase.execSQL(createBill);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
