package com.sunafil.mitienda.data.remote

import com.google.gson.annotations.SerializedName


/**
 * DogResponse
 *
 * @author Bryam Soto
 * @since 19/12/22
 */
data class DogResponse(
    @SerializedName("status") val status: String,
    @SerializedName("message") val images: List<String>
)