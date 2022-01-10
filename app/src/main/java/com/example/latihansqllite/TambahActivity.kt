package com.example.latihansqllite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class TambahActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah)

        val txtJudul = findViewById<EditText>(R.id.txt_judul)
        val txtGenre = findViewById<EditText>(R.id.txt_genre)
        val txtTahun = findViewById<EditText>(R.id.txt_tahun)
        val btnSimpan = findViewById<Button>(R.id.btn_simpan)

        btnSimpan.setOnClickListener {
            val dbSaya = MyDBHelper(this)
            dbSaya.tambahFilm(
                txtJudul.text.toString().trim(),
                txtGenre.text.toString().trim(),
                Integer.valueOf(txtTahun.text.toString().trim())

            )
        }
    }
}