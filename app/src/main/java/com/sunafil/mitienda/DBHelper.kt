package com.sunafil.mitienda

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns


/**
 * DBHelper
 *
 * @author Bryam Soto - Interbank
 * @email bsoto@intercorp.com.pe
 * @since 16/12/22
 */
class DBHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "CREATE TABLE $TABLE_PRODUCTOS (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "$COLUM_PRODUCT_IMAGE TEXT," +
                    "$COLUM_PRODUCT_NAME TEXT," +
                    "$COLUM_PRODUCT_PRICE TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_PRODUCTOS")
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    companion object {

        const val DB_NAME = "my_db"
        const val DB_VERSION = 1
        const val TABLE_PRODUCTOS = "product_table"
        const val COLUM_PRODUCT_IMAGE = "product_image"
        const val COLUM_PRODUCT_NAME = "product_name"
        const val COLUM_PRODUCT_PRICE = "product_price"

    }

}