
package com.ivann.applicationtvgoodgit;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolder> implements View.OnClickListener{


private List<Film> filmList;

public FilmAdapter(List<Film> filmList) {
        this.filmList = filmList;
        }

@NonNull
@Override
public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_film, parent,false);

        return new ViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Film film = filmList.get(position);

 Picasso.get().load("https://image.tmdb.org/t/p/original"+filmList.get(position).filmImage);

        Picasso.get().load("https://image.tmdb.org/t/p/original"+filmList.get(position).filmImage).into(holder.filmImage);
        holder.titre.setText(film.titre);
        holder.date.setText(film.dateSortie);
        holder.popularite.setText(holder.popularite.toString());
    holder.itemView.setTag(film);
    holder.itemView.setOnClickListener(this);
        //holder.genre.setText(film.Genre);
        //holder.like.setBottom(film.);

        }

@Override
public int getItemCount() {

    return filmList.size();
        }

public class ViewHolder extends RecyclerView.ViewHolder{
    final ImageView filmImage;
   // final Button like;
    final TextView titre;
    final TextView date;
    final TextView popularite;
    final TextView genre;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        filmImage = itemView.findViewById(R.id.imageViewFilmImage);
        titre = itemView.findViewById(R.id.textViewNomFilm);
        date = itemView.findViewById(R.id.textViewDate);
        popularite = itemView.findViewById(R.id.textViewPopularite);
        genre = itemView.findViewById(R.id.textViewGenre);
//        like = itemView.findViewById(R.id.imageButtonLike);
    }

}
    @Override
    public void onClick(View v) {
        Film film = (Film) v.getTag();
        Context context = v.getContext();
        Intent intent = new Intent(context, FocusFilmActivity.class);
        intent.putExtra("film", film);
        context.startActivity(intent);
    }
}
