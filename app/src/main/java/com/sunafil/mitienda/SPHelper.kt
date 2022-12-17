package com.sunafil.mitienda

import android.content.Context


/**
 * SPHelper
 *
 * @author Bryam Soto - Interbank
 * @email bsoto@intercorp.com.pe
 * @since 16/12/22
 */
class SPHelper(val context: Context) {

    fun saveString(key: String, value: String){
        context.getSharedPreferences(MY_PREF, Context.MODE_PRIVATE)
            .edit()
            .putString(key, value)
            .commit()
    }

    fun readString(key: String): String {
        return context.getSharedPreferences(MY_PREF, Context.MODE_PRIVATE)
            .getString(key, "") ?: ""
    }

    fun saveBoolean(key: String, value: Boolean){
        context.getSharedPreferences(MY_PREF, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(key, value)
            .commit()
    }

    fun readBoolean(key: String): Boolean{
        return context.getSharedPreferences(MY_PREF, Context.MODE_PRIVATE)
            .getBoolean(key, false)
    }

    companion object {

        const val MY_PREF = "my_pref"

    }

}