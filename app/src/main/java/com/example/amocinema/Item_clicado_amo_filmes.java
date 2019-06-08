package com.example.amocinema;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Item_clicado_amo_filmes extends AppCompatActivity {

    ImageView imgPoster;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_clicado_amo_filmes);


                textView = findViewById(R.id.tv_item_clicado);
                imgPoster = findViewById(R.id.iv_item_clicado);
                Intent intent = getIntent();

                if (intent.hasExtra("filme")){

                    Filme filme = (Filme) getIntent().getSerializableExtra("filme");

                    Log.e("mensagem", filme.getTitulo());

                    String ola = filme.getPoster();

                    Picasso.with(this).load(getPoster(filme)).into(imgPoster);

                    textView.setText( filme.getTitulo());


                }
            }

    private String getPoster(Filme filme) {
        return NetworkUtils.URL_POSTER + NetworkUtils.TAMANHO + filme.getPoster();
    }
    }


