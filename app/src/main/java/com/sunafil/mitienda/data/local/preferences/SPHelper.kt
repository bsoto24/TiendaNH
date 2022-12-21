package com.sunafil.mitienda.data.local.preferences

import android.content.SharedPreferences


/**
 * SPHelper
 *
 * @author Bryam Soto
 * @since 16/12/22
 */
class SPHelper(private val sp: SharedPreferences) {

    fun saveInt(key: String, value: Int) {
        sp.edit().putInt(key, value).commit()
    }

    fun readInt(key: String): Int {
        return sp.getInt(key, 0)
    }

    companion object {

        const val MY_PREF = "my_preferences"
        const val PREF_CONTADOR = "pref_contador"

    }

}