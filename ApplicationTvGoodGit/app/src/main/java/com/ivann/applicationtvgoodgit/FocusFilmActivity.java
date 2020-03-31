package com.ivann.applicationtvgoodgit;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Parcel;
import android.os.Parcelable;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FocusFilmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus_film);

        Intent srcIntent = getIntent();
        final ArrayList <Film> filmList = srcIntent.getParcelableArrayListExtra("FilmList");

        Log.i("FocusActivity", "la list des films " + filmList);

        final TextView textViewTitle = findViewById(R.id.textViewTitle);
        textViewTitle.setText(filmList.get(0).titre);
        final TextView textViewParution = findViewById(R.id.textViewParution);
         textViewParution.setText(filmList.get(0).dateSortie);
        final TextView textViewResume = findViewById(R.id.textViewResume);
      textViewResume.setText(filmList.get(0).resume);
        ImageView imageViewPoster = findViewById(R.id.imageViewPoster);
        Picasso.get().load(filmList.get(0).filmImage).into(imageViewPoster);// utilisation de la lib Picasso qui permet de choper une image via url ultra simplement
    }
}
