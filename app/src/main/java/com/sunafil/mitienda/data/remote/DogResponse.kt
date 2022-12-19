package com.sunafil.mitienda.data.remote

import com.google.gson.annotations.SerializedName


/**
 * DogResponse
 *
 * @author Bryam Soto - Interbank
 * @email bsoto@intercorp.com.pe
 * @since 19/12/22
 */
data class DogResponse(
    @SerializedName("status") val status: String,
    @SerializedName("message") val images: List<String>
)