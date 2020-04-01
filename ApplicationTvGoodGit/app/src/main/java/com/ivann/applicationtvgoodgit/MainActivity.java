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

        final TextView textViewCollections = findViewById(R.id.textViewCollections);
        textViewCollections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCollections();
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

        final TextView textViewTest = findViewById(R.id.textViewTest);
        textViewTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToTest();
            }
        });
    }

    private void goToCollections() {
        final Intent intentCollections = new Intent(this, CollectionsActivity.class);
        startActivity(intentCollections);
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

    private void goToTest() {
        final Intent intentTest = new Intent(this, FocusFilmActivity.class);
        startActivity(intentTest);
    }
}
