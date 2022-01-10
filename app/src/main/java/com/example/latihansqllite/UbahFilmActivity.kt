package com.example.latihansqllite

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import org.w3c.dom.Text

class UbahFilmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubah_film)


        val actionBar = supportActionBar
        if (intent.hasExtra("judul")){
            actionBar?.title = intent.getStringExtra("judul")
        }



        val btnUbah = findViewById<Button>(R.id.btn_ubah)

        getIntentData()

        btnUbah.setOnClickListener {
            val dbKita = MyDBHelper(this)
            val idFilm = intent.getStringExtra("id")
            val judulFilm = findViewById<EditText>(R.id.txt_edit_judul).text.toString()
            val genreFilm = findViewById<EditText>(R.id.txt_edit_genre).text.toString()
            val tahunFilm = findViewById<EditText>(R.id.txt_edit_tahun).text.toString()

            dbKita.ubahFilm(idFilm, judulFilm, genreFilm, tahunFilm)

        }

        val btnDelete = findViewById<Button>(R.id.btn_hapus)
        btnDelete.setOnClickListener {
            dialogKonfirmasi()
        }
    }


    fun getIntentData(){
        if (
            intent.hasExtra("id") && intent.hasExtra("judul") &&
            intent.hasExtra("genre") && intent.hasExtra("tahun")

        ) {
            val idFilm = intent.getStringExtra("id")
            val judulFilm = intent.getStringExtra("judul")
            val genreFilm = intent.getStringExtra("genre")
            val tahunFilm = intent.getStringExtra("tahun")

            val txtJudul = findViewById<EditText>(R.id.txt_edit_judul)
            val txtGenre = findViewById<EditText>(R.id.txt_edit_genre)
            val txtTahun = findViewById<EditText>(R.id.txt_edit_tahun)

            txtJudul.setText(judulFilm)
            txtGenre.setText(genreFilm)
            txtTahun.setText(tahunFilm)

        } else{
            Toast.makeText(this, "Tidak ada Data !!", Toast.LENGTH_SHORT).show()

        }
    }

    fun dialogKonfirmasi() {
        val idFilm = intent.getStringExtra("id")
        val judulFilm = intent.getStringExtra("judul")

        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Delete "+ judulFilm + " ?")
        alertDialog.setMessage("Apakah anda yakin menghapus "+ judulFilm + " ?")

        alertDialog.setPositiveButton("Iya", DialogInterface.OnClickListener { dialog, which ->
            val dbKita = MyDBHelper(this)
            dbKita.hapusFilm(idFilm)
            startActivity(Intent(this, MainActivity::class.java))
        })

        alertDialog.setNegativeButton("Tidak", DialogInterface.OnClickListener { dialog, which ->  })
        alertDialog.create().show()

    }
}