package com.tiara.restorancrudkotlin.server

import com.tiara.restorancrudkotlin.response.ResponseInsert
import com.tiara.restorancrudkotlin.response.ResponseInsertGet
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    //@ -? annotation

    //in harus ada kalau seandainya method url adalah post
    @FormUrlEncoded // fungsinya karena kita pakai post dia yg nyembunyiinnya
    @POST("insertMakanan") // jadi ini dari mana kita mau post
    fun requestInsert (@Field("name")nama : String,
                       @Field("price")harga :String,
                       @Field("restoran")restoran : String) : Call<ResponseInsert>

    @GET("suratjalandua/?key=54b031e20b682fb14ae1e136bd7736c9")
    fun requestGetMakanan(
            @Query("keyword")key :String
    ):Call<ResponseInsertGet>
}

