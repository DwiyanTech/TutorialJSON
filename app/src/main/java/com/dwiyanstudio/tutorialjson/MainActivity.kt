package com.dwiyanstudio.tutorialjson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.loopj.android.http.*
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    private val listData = ArrayList<JsonData>()
    private val url = "https://www.breakingbadapi.com/api/characters?limit=10"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val client = AsyncHttpClient()
        client.get(url,object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray
            ) {
                val response = String(responseBody)
                val arrayData = JSONArray(response)
                for(i in 0 until arrayData.length()){
                    val objectData = arrayData.getJSONObject(i)
                    val title = objectData.getString("name")
                    val urlThumbnail = objectData.getString("img")
                    val allData = JsonData(title,urlThumbnail)
                    listData.add(allData)
                }
                item_recycler.layoutManager = GridLayoutManager(applicationContext,2)
                val adapter = AdapterData(listData)
                item_recycler.adapter = adapter
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Toast.makeText(applicationContext,headers.toString(), Toast.LENGTH_LONG).show()
            }

        })
    }
}