package com.tiara.restorancrudkotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.tiara.restorancrudkotlin.adapter.MakananAdapter
import com.tiara.restorancrudkotlin.response.ResponseInsertGet
import com.tiara.restorancrudkotlin.server.ConfigServer
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ambil data
        ambilData()
    }

    private fun ambilData() {
        ConfigServer().service.requestGetMakanan("01418").enqueue(object : Callback<ResponseInsertGet>{

            override fun onFailure(call: Call<ResponseInsertGet>?, t: Throwable?) {
                toast(t.toString())
            }

            override fun onResponse(call: Call<ResponseInsertGet>?, response: Response<ResponseInsertGet>?) {
                if (response?.isSuccessful!!){
                    val status = response.body()?.status
                    val data = response.body()?.data

                    val adapter = MakananAdapter(data!!) // !! = data lebih dari satu

                    //ngeset recyecler View buat adapter
                    recycler.adapter = adapter
                    recycler.layoutManager = LinearLayoutManager(this@MainActivity)

                    Log.d("data Json Makan",data.toString())

                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.add -> startActivity(Intent(this,TambahActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}
