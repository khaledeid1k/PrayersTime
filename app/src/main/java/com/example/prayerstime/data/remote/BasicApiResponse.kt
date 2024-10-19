package com.example.prayerstime.data.remote

import com.google.gson.annotations.SerializedName

 class BasicApiResponse<T>{
     @SerializedName("data")
     var data: T? = null

     @SerializedName("status")
     var status: String? = null

     @SerializedName("code")
     var code: Int? = null
 }