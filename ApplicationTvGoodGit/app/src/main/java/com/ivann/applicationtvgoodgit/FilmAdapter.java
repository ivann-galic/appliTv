
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

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolder> {


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

 Picasso.get().load(filmList.get(position).filmImage);

        Picasso.get().load(filmList.get(position).filmImage).into(holder.filmImage);
        holder.titre.setText(film.titre);
        holder.date.setText(film.dateSortie);
        holder.popularite.setText(holder.popularite.toString());
        holder.genre.setText(film.Genre);
        //holder.like.setBottom(film.);

        }

@Override
public int getItemCount() {

    return filmList.size();
        }

/*@Override
public void onClick(View v) {
        ArrayList<Film> list;
        Film a = (Film) v.getTag();
        list= new ArrayList<>();
        list.add(a);
       // Log.i("AnswerAdapter", "onClick: " + a.filmImage);

        Context context = v.getContext();
        Intent intent = new Intent(context,MainActivity.class);
        intent.putExtra("quizz",list);
        context.startActivity(intent);
        }*/

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
}
