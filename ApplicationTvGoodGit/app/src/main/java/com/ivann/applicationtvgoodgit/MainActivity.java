package com.ivann.applicationtvgoodgit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textViewFavoris = findViewById(R.id.textViewFavoris);
        textViewFavoris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFavoris();
            }
        });

        final TextView textViewPlusTard = findViewById(R.id.textViewPlusTard);
        textViewPlusTard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLater();
            }
        });

        final TextView textViewRecherche = findViewById(R.id.textViewRecherche);
        textViewRecherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSearch();
            }
        });

        final TextView textViewNouveautés = findViewById(R.id.textViewNouveautés);
        textViewNouveautés.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewFilms();
            }
        });

        final TextView textViewAPropos = findViewById(R.id.textViewAPropos);
        textViewAPropos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAbout();
            }
        });

    }

    private void goToFavoris() {
        final Intent intentFavoris = new Intent(this, FavorisActivity.class);
        startActivity(intentFavoris);
    }

    private void goToLater() {
        final Intent intentLater = new Intent(this, LaterActivity.class);
        startActivity(intentLater);
    }

    private void goToSearch() {
        final Intent intentSearch = new Intent(this, SearchActivity.class);
        startActivity(intentSearch);
    }

    private void goToNewFilms() {
        final Intent intentNewFilms = new Intent(this, NewFilmsActivity.class);
        startActivity(intentNewFilms);
    }

    private void goToAbout() {
        final Intent intentAbout = new Intent(this, AboutActivity.class);
        startActivity(intentAbout);
    }
}
