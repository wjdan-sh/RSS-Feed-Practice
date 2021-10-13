package com.example.rssfeedpractice

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    lateinit var listView: ListView
    var reQuestions = mutableListOf<Question>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)

   /*     RC = findViewById(R.id.View)
        rvAdapter = RVaddapter(topSongs)
        RC.adapter = rvAdapter
        RC.layoutManager = LinearLayoutManager(this)*/

        FetchTopSongs().execute()

    }

    private inner class FetchTopSongs : AsyncTask<Void, Void, MutableList<Question>>() {
        val parser = XMLParser()
        override fun doInBackground(vararg params: Void?): MutableList<Question> {
            val url = URL("https://stackoverflow.com/feeds")
            val urlConnection = url.openConnection() as HttpURLConnection
            reQuestions = urlConnection.getInputStream()?.let {
                    parser.parse(it)
                } as MutableList<Question>
            return reQuestions
        }
        override fun onPostExecute(result: MutableList<Question>?) {
            super.onPostExecute(result)
            val adapter =
                ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, reQuestions)
            listView.adapter = adapter
        }

    }
}