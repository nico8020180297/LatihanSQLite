package com.example.latihansqllite;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolderSaya> {

    private Context context;
    private ArrayList film_id, film_judul, film_genre, film_tahun;

    FilmAdapter(
            Context context,
            ArrayList film_id,
            ArrayList film_judul,
            ArrayList film_genre,
            ArrayList film_tahun

    ) {
        this.context = context;
        this.film_id = film_id;
        this.film_judul = film_judul;
        this.film_genre = film_genre;
        this.film_tahun = film_tahun;

    }

    @NonNull
    @NotNull
    @Override
    public ViewHolderSaya onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflaterKita = LayoutInflater.from(context);
        View viewKita = inflaterKita.inflate(R.layout.row_saya, parent, false);
        return new ViewHolderSaya(viewKita);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolderSaya holder, int position) {
        holder.txt_id_film.setText(String.valueOf(film_id.get(position)));
        holder.txt_judul_film.setText(String.valueOf(film_judul.get(position)));
        holder.txt_genre_film.setText(String.valueOf(film_genre.get(position)));
        holder.txt_tahun_film.setText(String.valueOf(film_tahun.get(position)));
        holder.layoutUtama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentKita = new Intent(context, UbahFilmActivity.class);
                intentKita.putExtra("id", String.valueOf(film_id.get(position)));
                intentKita.putExtra("judul", String.valueOf(film_judul.get(position)));
                intentKita.putExtra("genre", String.valueOf(film_genre.get(position)));
                intentKita.putExtra("tahun", String.valueOf(film_tahun.get(position)));

                context.startActivity(intentKita);
            }
        });

    }

    @Override
    public int getItemCount() {
        return film_id.size();
    }

    public class ViewHolderSaya extends RecyclerView.ViewHolder {

        TextView txt_id_film, txt_judul_film, txt_genre_film, txt_tahun_film;
        LinearLayout layoutUtama;


        public ViewHolderSaya(@NonNull @NotNull View itemView) {
            super(itemView);

            txt_id_film    = itemView.findViewById(R.id.txt_film_id);
            txt_judul_film = itemView.findViewById(R.id.txt_film_judul);
            txt_genre_film = itemView.findViewById(R.id.txt_film_genre);
            txt_tahun_film = itemView.findViewById(R.id.txt_film_tahun);
            layoutUtama    = itemView.findViewById(R.id.layout_utama);


        }
    }
}
