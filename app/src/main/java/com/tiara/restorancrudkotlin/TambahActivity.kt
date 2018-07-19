package com.tiara.restorancrudkotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.tiara.restorancrudkotlin.response.ResponseInsert
import com.tiara.restorancrudkotlin.server.ConfigServer
import kotlinx.android.synthetic.main.activity_tambah.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TambahActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId){
            R.id.insert -> insertServer()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertServer() {
        //ambil inutan user
        val et_name = edtSatu.text.toString()
        val et_harga = edtDua.text.toString()
        val et_restoran = edtTiga.text.toString()

        if (et_name == "" && et_harga == "" && et_restoran == ""){
            toast("tidak boleh kosong")
        }else{
            //insert Server

            //ambil Configurasinya
            val config = ConfigServer().service
             //ambil requestnya
            val request = config.requestInsert(et_name,et_harga,et_restoran)
            request.enqueue(object : Callback<ResponseInsert>{
                override fun onFailure(call: Call<ResponseInsert>?, t: Throwable?) {
                    toast(t.toString())
                }

                override fun onResponse(call: Call<ResponseInsert>?, response: Response<ResponseInsert>?) {
                    //eksekusi respon
                    if (response?.isSuccessful!!){
                        val status = response.body()?.status
                        val pesan = response.body()?.pesan

                        if (status == 1){
                            startActivity(Intent(this@TambahActivity,MainActivity::class.java))
                        }else{

                            toast(pesan!!)

                        }
                    }
                }
            })
        }
    }
}
