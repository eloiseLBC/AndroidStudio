package fr.projectone.salade

import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.view.textclassifier.TextLinks
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import javax.security.auth.callback.Callback

class ProduitsActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produits)
        showBack()
        setHeaderTxt("Rayons")

        val rayons = arrayListOf<Rayon>()

        val recyclerviewRayons = findViewById<RecyclerView>(R.id.recyclerviewRayons)
        recyclerviewRayons.layoutManager = LinearLayoutManager(this)
        val rayonAdapter = ProduitAdapter(rayons)
        recyclerviewRayons.adapter = rayonAdapter

        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
        val mRequestUrl = "https://www.ugarit.online/epsi/categories.json"
        val request =
            Request.Builder().url(mRequestUrl).cacheControl(CacheControl.FORCE_NETWORK).build()

        okHttpClient.newCall(request).enqueue(object : Callback {
             fun onResponse(call: Call, response: Response) {
                val data = response.body?.string()
                if (data != null) {
                    val jsRayons = JSONObject(data)
                    val jsArrayRayon = jsRayons.getJSONArray("items")

                    for (i in 0 until jsArrayRayon.length()) {
                        val jsRayon = jsArrayRayon.getJSONObject(i)
                        val rayon = Rayon(
                            jsRayon.optString("category_id", "Not found"),
                            jsRayon.optString("title", "Not found"),
                            jsRayon.optString("products_url", "Not found")
                        )
                        rayons.add(rayon)
                    }
                    runOnUiThread(Runnable {
                        rayonAdapter.notifyDataSetChanged()
                    })

                    Log.e("WS", data)
                }
            }

            fun onFailure(call: Call, e: IOException) {
                runOnUiThread(Runnable {
                    Toast.makeText(application, e.message, Toast.LENGTH_SHORT).show()
                })

            }
        })
    }
}

private fun Any.enqueue(responseCallback: Callback) {

}


