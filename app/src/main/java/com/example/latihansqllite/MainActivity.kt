package com.example.latihansqllite

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    val film_id:    ArrayList<String> = arrayListOf()
    val film_judul: ArrayList<String> = arrayListOf()
    val film_genre: ArrayList<String> = arrayListOf()
    val film_tahun: ArrayList<String> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnTambah = findViewById<FloatingActionButton>(R.id.fab_add)
        btnTambah.setOnClickListener {
            val intenKita = Intent(this, TambahActivity::class.java)
            startActivity(intenKita)
        }

        simpanDataDiArray()

        val rv_film = findViewById<RecyclerView>(R.id.rv_films)
        val filmAdapter = FilmAdapter(this, film_id, film_judul, film_genre, film_tahun)
        rv_film.adapter = filmAdapter
        rv_film.layoutManager = LinearLayoutManager(this)

    }

    fun simpanDataDiArray(){
        val dbKita           = MyDBHelper(this)
        val dataKita: Cursor = dbKita.bacaSemuaData()

        if (dataKita.count == 0) {
            Toast .makeText(this, "Data Tidak Ada !", Toast.LENGTH_SHORT).show()
        } else {
            while (dataKita.moveToNext()){
                film_id.add(dataKita.getString(0))
                film_judul.add(dataKita.getString(1))
                film_genre.add(dataKita.getString(2))
                film_tahun.add(dataKita.getString(3))
            }

        }

    }
}