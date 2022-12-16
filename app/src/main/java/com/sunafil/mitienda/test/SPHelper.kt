package com.sunafil.mitienda.test

import android.content.Context


/**
 * SPHelper
 *
 * @author Bryam Soto - Interbank
 * @email bsoto@intercorp.com.pe
 * @since 16/12/22
 */
class SPHelper(private val context: Context) {

    fun saveInt(key: String, value: Int) {
        context.getSharedPreferences(MY_PREF, Context.MODE_PRIVATE)
            .edit()
            .putInt(key, value)
            .commit()
    }

    fun readInt(key: String): Int {
        return context.getSharedPreferences(MY_PREF, Context.MODE_PRIVATE)
            .getInt(key, 0)
    }

    companion object {

        const val MY_PREF = "my_preferences"

    }

}