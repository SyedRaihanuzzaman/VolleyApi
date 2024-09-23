package com.example.yourname.volleyapi


import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request

import com.android.volley.Response
import com.android.volley.VolleyLog.TAG
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.yourname.volleyapi.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    val url:String = "https://meme-api.com/gimme"
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        getMemeData()

        binding.buttonNewMeme.setOnClickListener {
            getMemeData()
        }





    }

     fun getMemeData() {

         var ProgressDialog = ProgressDialog(this)
         ProgressDialog.setMessage("Please wait a moment...")
         ProgressDialog.setCancelable(false)
         ProgressDialog.show()



         val queue = Volley.newRequestQueue(this)



         val stringRequest = StringRequest(
             Request.Method.GET, url,
             { response ->
                 Log.e("Response", "getMemeData: "+response, )


                 var responseObjects = JSONObject(response)

                 responseObjects.get("postLink")

                 binding.memeTitle.text =  responseObjects.getString("title")
                 binding.memeAuthor.text =  responseObjects.getString("author")
                 Glide.with(this@MainActivity).load(responseObjects.get("url")).into(binding.memeImage)
                 ProgressDialog.dismiss()


             },
             { error->
                 ProgressDialog.dismiss()
                 Toast.makeText(this@MainActivity, error.localizedMessage, Toast.LENGTH_SHORT).show()



             })


         queue.add(stringRequest)




    }
}