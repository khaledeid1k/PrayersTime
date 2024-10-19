package com.example.prayerstime.data.remote.model


import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("latitude")
    val latitude: Double? = 0.0,
    @SerializedName("longitude")
    val longitude: Double? = 0.0,
    @SerializedName("method")
    val method: Method = Method(),
    @SerializedName("timezone")
    val timezone: String = "",

    )
data class Method(
    @SerializedName("id")
    val id: Int = 0,
)