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
                KEY_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_CATEGORY_NAME + " TEXT, " +
                KEY_CATEGORY_IMAGE + " INTEGER " +
                " )";
        sqLiteDatabase.execSQL(createCategory);

        String createAdmin = "CREATE TABLE " + TABLE_NAME_ADMIN +
                "(" +
                KEY_ADMIN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_ADMIN_USERNAME + " TEXT, " +
                KEY_ADMIN_PASSWORD + " TEXT, " +
                KEY_ADMIN_ROLE + " INTEGER " +
                " )";
        sqLiteDatabase.execSQL(createAdmin);

        sqLiteDatabase.execSQL("CREATE UNIQUE INDEX " + KEY_ADMIN_USERNAME + " ON " + TABLE_NAME_ADMIN + "(" + KEY_ADMIN_USERNAME + ")");
        String createUser = "CREATE TABLE " + TABLE_NAME_USER +
                "(" + KEY_USER_ID + " TEXT, " + KEY_USER_name + " TEXT )";
        sqLiteDatabase.execSQL(createUser);


        String createBook = "CREATE TABLE " + TABLE_NAME_BOOKS +
                "(" +
                KEY_BOOKS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_CATEGORY_ID + " INTEGER,  " +
                KEY_BOOKS_NAME + " TEXT, " +
                KEY_BOOKS_AUTHOR + " TEXT, " +
                KEY_BOOKS_IMAGE + " INTEGER, " +
                KEY_BOOKS_DESCRIPTION + " TEXT, " +
                KEY_BOOKS_RATING + " FLOAT, " +
                "FOREIGN  KEY (" + KEY_CATEGORY_ID + ") REFERENCES " + TABLE_NAME_CATEGORY + " (" + KEY_CATEGORY_ID + ")  ON DELETE CASCADE" +
                " )";
        sqLiteDatabase.execSQL(createBook);


        String createBill = "CREATE TABLE " + TABLE_NAME_BILL +
                "(" +
                KEY_BILL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_BOOKS_ID + " INTEGER, " +
                KEY_ADMIN_ID + " INTEGER, " +
                KEY_USER_ID + " TEXT, " +
                KEY_BILL_DATECREATE + " TEXT, " +
                KEY_BILL_DATERETURN + " TEXT, " +
                KEY_BILL_checked + " INTEGER, " +
                "FOREIGN  KEY (" + KEY_BOOKS_ID + ") REFERENCES " + TABLE_NAME_BOOKS + " (" + KEY_BOOKS_ID + "), " +
                "FOREIGN  KEY (" + KEY_ADMIN_ID + ") REFERENCES " + TABLE_NAME_ADMIN + " (" + KEY_ADMIN_ID + "), " +
                "FOREIGN  KEY (" + KEY_USER_ID + ") REFERENCES " + TABLE_NAME_USER + " (" + KEY_USER_ID + ") " +
                " )";
        sqLiteDatabase.execSQL(createBill);

        String initUser = "INSERT INTO " + TABLE_NAME_USER + " VALUES" +
                "('ps01','Cong')," +
                "('ps02','Mai')," +
                "('ps03','Ha')," +
                "('ps04','Vy')," +
                "('ps05','Dung')";
        sqLiteDatabase.execSQL(initUser);

        String initAdmin = "INSERT INTO " + TABLE_NAME_ADMIN + " VALUES" +
                "(1,'Admin','admin',0)," +
                "(2,'Mod1','admin',1)," +
                "(3,'Mod2','admin',1)," +
                "(4,'Mod3','admin',1)," +
                "(5,'Mod4','admin',1)," +
                "(6,'Mod5','admin',1)";
        sqLiteDatabase.execSQL(initAdmin);

        String initCategory = "INSERT INTO " + TABLE_NAME_CATEGORY + " VALUES" +
                "(1,'Sach Cong Nghe1',0)," +
                "(2,'Sach Cong Nghe2',0)," +
                "(3,'Sach Cong Nghe3',0)," +
                "(4,'Sach Cong Nghe4',0)," +
                "(5,'Sach Cong Nghe5',0)";
        sqLiteDatabase.execSQL(initCategory);

        String initBook = "INSERT INTO " + TABLE_NAME_BOOKS + " VALUES" +
                "(1,1,'E','Edward',0,'Des nay cua sach',3.1)," +
                "(2,2,'Ed','Edward',0,'Des nay cua sach',3.1)," +
                "(3,3,'Edw','Edward',0,'Des nay cua sach',3.1)," +
                "(4,4,'Edwa','Edward',0,'Des nay cua sach',3.1)," +
                "(5,5,'Edward','Edward',0,'Des nay cua sach',3.1)";
        sqLiteDatabase.execSQL(initBook);

        String initBill = "INSERT INTO " + TABLE_NAME_BILL + " VALUES" +
                "(1,1,2,'ps01','22/2/2022','',0)," +
                "(2,2,3,'ps02','22/2/2022','22/2/2022',1)," +
                "(3,3,4,'ps03','22/2/2022','',0)," +
                "(4,4,5,'ps04','22/2/2022','22/2/2022',1)," +
                "(5,5,6,'ps05','22/2/2022','',0)";
        sqLiteDatabase.execSQL(initBill);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
