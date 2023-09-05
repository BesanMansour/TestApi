package com.project.testapi.Activity

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.testapi.Adapter.NasaAdapter
import com.project.testapi.Model.Nasa
import com.project.testapi.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    val api_key :String?="DEMO_KEY"
    val URL = "https://api.nasa.gov/planetary/apod?api_key=$api_key&count=10"
    lateinit var nasaAdapter : NasaAdapter
    val nasaList = ArrayList<Nasa>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        MyAsyncTask().execute(URL)

    }
    inner class MyAsyncTask: AsyncTask<String, String, String>(){

        override fun doInBackground(vararg p0: String?): String {
            try {
                val url = URL(p0[0])
                val urlConnect = url.openConnection() as HttpURLConnection
                urlConnect.connectTimeout = 700

                val dataJsonAsString = convertStreamToString(urlConnect.inputStream)

                publishProgress(dataJsonAsString)
            }catch (ex:java.lang.Exception){
                Log.e("ex",ex.toString())
            }
            return ""
        }

        override fun onProgressUpdate(vararg values: String?) {

            try {
                val jsonArray = JSONArray(values[0])
                Log.e("jsonArray",jsonArray.toString())
                Log.e("jsonlength",jsonArray.length().toString())

                for (i in 0 until jsonArray.length()) {
                    val json = jsonArray.getJSONObject(i)
                    val name = json.optString("copyright","")
                    val des = json.optString("explanation","")
                    val date = json.optString("date","")
                    val title = json.optString("title","")
                    val image = json.optString("url","")

                    val nasa = Nasa(name, date, des, title, image)
                    nasaList.add(nasa)
                    Log.e("nasa", nasa.toString())
                }

                nasaAdapter = NasaAdapter(nasaList, applicationContext)
                binding.MainRecycler.layoutManager = LinearLayoutManager(applicationContext)
                binding.MainRecycler.adapter = nasaAdapter
            }catch (ex:java.lang.Exception){
                Log.e("exhfdj",ex.toString())
            }

        }
        
//        override fun onProgressUpdate(vararg values: String?) {
//            val json = JSONObject(values[0])
//            val name = json.getString("copyright")
//            val des = json.getString("explanation")
//            val date = json.getString("date")
//            val title = json.getString("title")
//            val image = json.getString("url")
//
//            val nasa = Nasa(name,date,des,title,image)
//            nasaList.add(nasa)
//            Log.e("nasa",nasa.toString())
//
//            nasaAdapter = NasaAdapter(nasaList,applicationContext)
//            binding.MainRecycler.layoutManager = LinearLayoutManager(applicationContext)
//            binding.MainRecycler.adapter = nasaAdapter
//        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
        }

    }

    fun convertStreamToString(inputStream: InputStream):String{

        val bufferReader = BufferedReader(InputStreamReader(inputStream))
        var line : String
        var allString:String =""

        try {
            do {
                line = bufferReader.readLine()
                if (line!=null) {
                    allString += line
                }
            }while (line!=null)
            bufferReader.close()
        }catch (ex:java.lang.Exception){

        }

        return allString
    }
}